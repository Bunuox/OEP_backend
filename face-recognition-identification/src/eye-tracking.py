import cv2
import dlib
import numpy as np
import os


def shape_to_np(shape, dtype="int"):#dat dosyasi sayeisnde x,y koordinatlari numpy array'e cevirip return eder.
    #print('shape_to_np')
    # initialize the list of (x, y)-coordinates
    coords = np.zeros((68, 2), dtype=dtype)
    # loop over the 68 facial landmarks and convert them
    # to a 2-tuple of (x, y)-coordinates
    for i in range(0, 68):
        coords[i] = (shape.part(i).x, shape.part(i).y)
    # return the list of (x, y)-coordinates
    return coords


def eye_on_mask(mask, side):  # takes the image and the side which it will draw the eye in
    #print('eye_on_mask')
    points = [face_objects[i] for i in side]
    points = np.array(points, dtype=np.int32)
    mask = cv2.fillConvexPoly(mask, points, 255)
    # cv2.fillConvexPoly fun. takes an image, points as a NumPy array with data type = np.int32
    # and color as arguments and returns an image with the area between those points filled with that color.
    return mask


def contouring(thresh, mid, img, right=False):
    #print('contorin')
    """
    Used for finding the eye's pupil contour and the center of it
    :param thresh: the img after using threshold, dilation, erosion and median blur
    :param mid: the mid point between the two eyes
                which can be added to the left eye center to get the right eye position before the cropping
    :param img: the main img
    :param right: flag to indicate that this is the right eye
    :return: cx,cy the coord. of the pupil's center
    """
    cnts, _ = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    try:
        cnt = max(cnts, key=cv2.contourArea)  # finding contour with maximum area (eyeball)
        M = cv2.moments(cnt)  # to find the centers of the eyeballs.
        cx = int(M['m10'] / M['m00'])
        cy = int(M['m01'] / M['m00'])
        if right:
            cx += mid  # Adding value of mid to x coordinate of centre of
            # right eye to adjust for dividing into two parts
        cv2.circle(img, (cx, cy), 1, (0, 255, 0), 3)  # drawing over eyeball with green
        return cy, cx
    except Exception as e:
        print(e)  # occurs when eyes are not detected


def compare_eyeballs(y_left, x_left, y_right, x_right, y_center, x_center, img):
    print('compare_eyeballs')
    """
    Detect if center of the eyes' pupil is looking to the far left or right by calculating the diff. between the left most and right most eye points
    and then checks if the center with the diff. is close to anyone of those points
    """
    try:
        diff_x = abs(x_left - x_right) / 4
        # print("diff ", diff_x)
        # print('left point coord: ' + str(x_left) + ',' + str(y_left))
        # print('right point coord: ' + str(x_right) + ',' + str(y_right))
        # print('center point coord: ' + str(x_center) + ',' + str(y_center))
        cv2.putText(img, 'left : ' + str(x_left) + "  center : " + str(x_center) + "  right : " + str(x_right),
                    (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 255, 0), 1)
        img1 = cv2.imread(os.path.join('src', 'cheater.jpg'))
        cv2.imshow("eyes", img)
        if x_center - diff_x <= x_left:
            print('sola bakiliyor')
            #cv2.imshow('cheater image looking left', img1)
            cv2.waitKey(0)
        elif x_center + diff_x >= x_right:
            print('saga bakiliyor')
            #cv2.imshow('cheater image looking right', img1)
            cv2.waitKey(0)
        else:
            cv2.destroyWindow('cheater image')
    except:
        pass


face_detector = dlib.get_frontal_face_detector()
predictor = dlib.shape_predictor(os.path.join('src', 'shape_68.dat'))#acaba bu dat dosyasi göze özel olmamali.

left = [36, 37, 38, 39, 40, 41]  # keypoint indices for left eye
right = [42, 43, 44, 45, 46, 47]  # keypoint indices for right eye

cap = cv2.VideoCapture(0)

cv2.namedWindow('image')
kernel = np.ones((20, 20), np.uint8)  # used for dilation of eyes in the mask
process_frame = False # bunun islevini daha anlayamadim.

def nothing(x):
   pass
cv2.createTrackbar('threshold', 'image', 0, 255, nothing)


while (True):
    #ret, img_frame = cap.read()
    img_frame = cv2.imread(os.path.join('src' ,'eyes', 'img28.png'))
    thresh = img_frame.copy()
    if not process_frame:
        process_frame = True
        gray_frame = cv2.cvtColor(img_frame, cv2.COLOR_BGR2GRAY)
        faces = face_detector(gray_frame, 1)  # contains all the faces detected
        # so we need to save only the person that i must track his eyes istead of saving all the faces
        # if the previous step done we will never need for-loop as we will have one face

        for face in faces:
            face_objects = shape_to_np(predictor(gray_frame, face))  # to convert all face objects' keypoints to (x, y)-coordinates
            # print('shape points: '+ str(shape))
            #yani son asamada face_objects numpy_array olmus
            mask = np.zeros(img_frame.shape[:2], dtype=np.uint8)
            mask = eye_on_mask(mask, left)  # takes the left eye points to fill the space between them with white color
            mask = eye_on_mask(mask, right)
            mask = cv2.dilate(mask, kernel, 5)  # to expand the created white area
            eyes = cv2.bitwise_and(img_frame, img_frame, mask=mask)  # Using cv2.bitwise_and with our mask as the mask on our image,
            # we can segment out the eyes.
            # Convert all the (0, 0, 0) pixels to (255, 255, 255) so that only the eyeball is the only dark part left
            mask = (eyes == [0, 0, 0]).all(axis=2)
            eyes[mask] = [255, 255, 255]
            mid = (face_objects[42][0] + face_objects[39][0]) // 2
            eyes_gray = cv2.cvtColor(eyes, cv2.COLOR_BGR2GRAY)
            threshold = 67
            #threshold = cv2.getTrackbarPos('threshold', 'image')
            _, thresh = cv2.threshold(eyes_gray, threshold, 255, cv2.THRESH_BINARY)
            thresh = cv2.erode(thresh, None, iterations=2)  # 1
            thresh = cv2.dilate(thresh, None, iterations=4)  # 2
            thresh = cv2.medianBlur(thresh, 3)  # 3 smoothing the image
            thresh = cv2.bitwise_not(thresh)  # to find the eyeballs it need to be white and its background black
            eyeLeft = contouring(thresh[:, 0:mid], mid, img_frame)
            eyeRight = contouring(thresh[:, mid:], mid, img_frame, True)
            print(eyeLeft)
            print(eyeRight)
            try:
                compare_eyeballs(face_objects[36][1], face_objects[36][0], face_objects[39][1], face_objects[39][0], eyeLeft[0], eyeLeft[1],
                                 img_frame)
            except Exception as e:
                print(e)
                pass

            try:
                compare_eyeballs(face_objects[42][1], face_objects[42][0], face_objects[45][1], face_objects[45][0], eyeRight[0], eyeRight[1],
                                 img_frame)
            except Exception as e:
                print(e)
                pass

        process_frame = False

    cv2.imshow('eyes', img_frame)
    #cv2.imshow("image", thresh)
    # cv2.destroyWindow('image')
    cv2.destroyWindow('eyePoints')

    if cv2.waitKey(1) & 0xFF == 27:
        break

cap.release()
cv2.destroyAllWindows()
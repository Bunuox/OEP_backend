import cv2
import dlib
import numpy as np
import os
import argparse

parser = argparse.ArgumentParser(description="Process images in a folder.")
parser.add_argument('IMAGE_FOLDER', type=str, help="IMAGE_FOLDER to process.")
args = parser.parse_args()

def shape_to_np(shape, dtype="int"):#dat dosyasi sayeisnde x,y koordinatlari numpy array'e cevirip return eder.
    coords = np.zeros((68, 2), dtype=dtype)
    # loop over the 68 facial landmarks and convert them
    # to a 2-tuple of (x, y)-coordinates
    for i in range(0, 68):
        coords[i] = (shape.part(i).x, shape.part(i).y)
    # return the list of (x, y)-coordinates
    return coords


def eye_on_mask(mask, side):
    points = [face_objects[i] for i in side]
    points = np.array(points, dtype=np.int32)
    mask = cv2.fillConvexPoly(mask, points, 255)
    return mask


def contouring(thresh, mid, img, right=False):
    cnts, _ = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    try:
        cnt = max(cnts, key=cv2.contourArea)
        M = cv2.moments(cnt)
        cx = int(M['m10'] / M['m00'])
        cy = int(M['m01'] / M['m00'])
        if right:
            cx += mid
        return cy, cx
    except Exception as e:
        pass


def compare_eyeballs(y_left, x_left, y_right, x_right, y_center, x_center, img):
    try:
        diff_x = abs(x_left - x_right) / 3.5
        if x_center - diff_x <= x_left:
            return True
        elif x_center + diff_x >= x_right:
            return True
        else:
            return False
    except:
        pass


ABS_PATH = os.path.abspath(__file__)
DIR_PATH = os.path.dirname(ABS_PATH)
IMGS_PATH = os.path.join(DIR_PATH, 'eyes', args.IMAGE_FOLDER)

face_detector = dlib.get_frontal_face_detector()
predictor = dlib.shape_predictor(os.path.join('python', 'src', 'models', 'shape_68.dat'))

left = [36, 37, 38, 39, 40, 41]  # keypoint indices for left eye
right = [42, 43, 44, 45, 46, 47]  # keypoint indices for right eye



for root, dirs, files in os.walk(IMGS_PATH):
    for file in files:
        if not file.endswith('.jpeg'):
            continue

        img_path = os.path.join(root, file)
        id = os.path.basename(root)

        if id !=args.IMAGE_FOLDER:
            continue

        img_frame = cv2.imread(img_path)
        kernel = np.ones((20, 20), np.uint8) #20 x 20 array fills with 1. unsigned int -> 0-255
        thresh = img_frame.copy() #copy ile resmin kaynagi thres degiskenine aktarilmis oldu.
        gray_frame = cv2.cvtColor(img_frame, cv2.COLOR_BGR2GRAY)
        faces = face_detector(gray_frame, 1)
        #---------
        for face in faces:
            face_objects = shape_to_np(predictor(gray_frame, face))  # to convert all face objects' keypoints to (x, y)-coordinates

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

            threshold = 67 #goruntuyu binary hale getirmek icin kullanilir.
            _, thresh = cv2.threshold(eyes_gray, threshold, 255, cv2.THRESH_BINARY)#thresh hold degere gore 0 veya 255 atar.
            thresh = cv2.erode(thresh, None, iterations=2)  #goruntudeki beyaz alanlar kuculur ve resmin gurultusu azalmıs olur.
            thresh = cv2.dilate(thresh, None, iterations=4)  #gurultuleri sildikten sonra beyaz alanlari geri getirmek icin.
            thresh = cv2.medianBlur(thresh, 3)  #pikselin degeri cevresindeki degerlerin medyani ile degistirilerek smoothlasma saglanmis olur.
            thresh = cv2.bitwise_not(thresh)  # iris beyaz arka plan ise siyah olur.

            eyeLeft = contouring(thresh[:, 0:mid], mid, img_frame)
            eyeRight = contouring(thresh[:, mid:], mid, img_frame, True)
            try:
                if (compare_eyeballs(face_objects[36][1], face_objects[36][0], face_objects[39][1], face_objects[39][0], eyeLeft[0], eyeLeft[1],
                                     img_frame) or compare_eyeballs(face_objects[42][1], face_objects[42][0], face_objects[45][1], face_objects[45][0], eyeRight[0], eyeRight[1],
                                                                    img_frame)):
                    print("CHEATING")
                else:
                    print("NOT-CHEATING")
            except:
                print("CHEATING")

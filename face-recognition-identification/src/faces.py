import cv2
import os

print('-----DEBUG BASLADI ------')
path_cascade = os.path.join('src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

face_recognizer = cv2.face.LBPHFaceRecognizer_create() 


ABS_PATH = os.path.abspath(__file__)
DIR_PATH = os.path.dirname(ABS_PATH)
IMGS_PATH = os.path.join(DIR_PATH, 'uploads') 


for root, dirs, files in os.walk(IMGS_PATH):
    for file in files:  #file name doesnt include path.
        img_path = os.path.join(root, file)

        id = os.path.basename(root)
        face_recognizer.read(os.path.join('src', 'models', 'face_trainner-' + id + '.yml'))
        frame = cv2.imread(img_path)
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.15, minNeighbors=10)

        for (x, y, w, h) in faces:

            roi_img = frame[y:y+h, x:x+w] 
            roi_gray = gray[y:y+h, x:x+w]
        
            label_id, confidence = face_recognizer.predict(cv2.resize(roi_gray, (500, 500))) 
            print(id, confidence)

            if confidence <=25 and confidence >=0:
                print('return true')#daha sonra sil.
    

 
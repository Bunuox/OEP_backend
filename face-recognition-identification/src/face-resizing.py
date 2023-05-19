#pre-processing for already pictures which not resizing under images folder.
import os
import cv2
from datetime import datetime

path_cascade = os.path.join('src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

ABS_FILE_PATH = os.path.abspath(__file__)
BASE_DIR = os.path.dirname(ABS_FILE_PATH)
IMGS_DIR = os.path.join(BASE_DIR, 'images')

for root, dirs, files in os.walk(IMGS_DIR):
    for file in files:
        if  (not file.startswith('train')) and (file.endswith('png') or file.endswith('jpeg') or file.endswith('jpg')):            
            img_path = os.path.join(root, file) # img_path = > ../abc.jpeg 
            #print(img_path)
            img_frame = cv2.imread(img_path)
            img_gray = cv2.cvtColor(img_frame, cv2.COLOR_BGR2GRAY)

            faces = face_cascade.detectMultiScale(img_gray, scaleFactor=1.05, minNeighbors=10)
            for (x, y, w, h) in faces:
                roi_img = img_frame[y:y+h, x:x+w]
                file_dir_path = os.path.dirname(img_path)
                file_name = 'train-' + str(datetime.today()) + '.jpeg'
                print(os.path.join(file_dir_path, file_name))
                cv2.imwrite(os.path.join(file_dir_path, file_name), cv2.resize(roi_img,(500, 500)))


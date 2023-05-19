import os
import cv2
import numpy as np

ABS_FILE_PATH = os.path.abspath(__file__)
BASE_DIR = os.path.dirname(ABS_FILE_PATH)
IMGS_DIR = os.path.join(BASE_DIR, 'images')

#to find region of interest for training-data

face_recognizer = cv2.face.LBPHFaceRecognizer_create()



for root, dirs, files in os.walk(IMGS_DIR):
    y_labels = []
    x_train = []
    dir_name = os.path.basename(root)
    for file in files:
        if file.endswith('jpeg'):
            #alacagimiz resmin 500, 500 pixsel olacagini assume ediyorum.
            img_path = os.path.join(root, file)
            img_gray = cv2.cvtColor(cv2.imread(img_path), cv2.COLOR_BGR2GRAY)

            #burada label_id default 1 olacak cunku her bir id icin model egitecegi icin label_idnin onemi yok.
            y_labels.append(1)
            x_train.append(img_gray)
    if  x_train:
        face_recognizer.train(x_train, np.array(y_labels))
        face_recognizer.save(os.path.join('src', 'models', 'face_trainner' + '-' + dir_name + '.yml'))
        print(os.path.join('src', 'models', 'face_trainner' + '-' + dir_name + '.yml'))
import cv2
import os
import argparse

parser = argparse.ArgumentParser(description="Process images in a folder.")
parser.add_argument('FOLDER_ID', type=str, help="Folder ID to process.")
args = parser.parse_args()

path_cascade = os.path.join('python', 'src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

face_recognizer = cv2.face.LBPHFaceRecognizer_create() 


ABS_PATH = os.path.abspath(__file__)
DIR_PATH = os.path.dirname(ABS_PATH)
IMGS_PATH = os.path.join(DIR_PATH, 'uploads') 


for root, dirs, files in os.walk(IMGS_PATH):
    for file in files:  #file name doesnt include path.
        if not file.endswith('.jpeg'): #yapma sebebimiz .DS_store gibi os kendi dosyasini koyabiliyor.
            continue
        img_path = os.path.join(root, file)
        id = os.path.basename(root)

        if id!=args.FOLDER_ID:
            continue

        face_recognizer.read(os.path.join('python', 'src', 'models', 'face_trainner-' + id + '.yml'))
        frame = cv2.imread(img_path)
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = face_cascade.detectMultiScale(gray, scaleFactor=1.15, minNeighbors=10)
        for (x, y, w, h) in faces:

            roi_img = frame[y:y+h, x:x+w] 
            roi_gray = gray[y:y+h, x:x+w]
        
            label_id, confidence = face_recognizer.predict(cv2.resize(roi_gray, (500, 500)))

            if confidence <=25 and confidence >=0:
                print(id, "TRUE")
            else:
                print(id, "FALSE")
    

 
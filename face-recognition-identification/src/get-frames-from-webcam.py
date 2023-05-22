import numpy as np
import cv2
import os
from datetime import datetime

ABS_DIR = os.path.abspath(__file__)
BASE_DIR = os.path.dirname(ABS_DIR)
WEBCAM_DIR = os.path.join(BASE_DIR, 'webcam')

path_cascade = os.path.join('src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

cap = cv2.VideoCapture(0)

while(True):
    ret, img_frame = cap.read()
    cv2.imwrite(os.path.join(WEBCAM_DIR,str(datetime.today()) + '.jpeg'), img_frame)
    cv2.imshow('frame', img_frame)
    
    k = cv2.waitKey(1)
    if k%256 == 27:
        # ESC pressed
        print("Escape hit, closing...")
        break

cap.release()
cv2.destroyAllWindows()
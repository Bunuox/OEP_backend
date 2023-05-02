import numpy as np
import cv2
import os
import pickle

#print(os.getcwd())
#farklı os'lerde dosya dizini belirtmek icin dikkat et
path_cascade = os.path.join('src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

face_recognizer = cv2.face.LBPHFaceRecognizer_create() # OpenCV recognizer ille byna simdi olusturdugumuz yaml uzantılı model'i verecegiz.
face_recognizer.read(os.path.join('src', 'face_trainner.yml'))

original_label_ids = {}
label_ids = {}

with open(os.path.join('src', 'labels.pickle'), 'rb') as file :
    original_label_ids = pickle.load(file)
    label_ids = {value: key for key, value in original_label_ids.items()}


cap = cv2.VideoCapture(0) #varsayılan kamerayı secer normalde input olarak front-end'ten resim alacagiz.

i = 0

while(True):
    i = i+1
    #cap.read methodu sayesinde olusturdugumuz VideCapture nesnesi ile görüntü alırız ret- true ya da false olurken / frame ise alinan goruntunun numpy dizi karsigilidir.
    #ret -> true demek goruntu basarili sekilde alabildi// false ise goruntu basarili bir sekilde alamadi anlamina gelir.
    ret, frame = cap.read()
    #frame = cv2.imread(os.path.join('src', 'images', 'yilmaz alperen', 'img49.png'))
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.15, minNeighbors=10)

    for (x, y, w, h) in faces:
        #roi islemi ile veri_seti iyilestirmesi yapcagiz.
        roi_img = frame[y:y+h, x:x+w] #ustteki gray ile ayni
        img_name = "img{}.png".format(i)
        #print(img_name)

        #face-identify-recognizer ? deep learning stuff

        #gray resmin region of interest'i
        roi_gray = gray[y:y+h, x:x+w]
        label_id, confidence = face_recognizer.predict(cv2.resize(roi_gray, (500, 500))) # daha sonra bu conf sayesinde authentication yapabilecegiz.
        print(confidence, label_ids[label_id])
        if confidence <=20 and confidence >=0:
            print(label_ids[label_id])

            label_name = label_ids[label_id]
            text_org = (x, y)
            text_font = cv2.FONT_HERSHEY_COMPLEX
            text_font_scale = 1
            text_color = (255, 255, 255)
            text_stroke = 2

            cv2.putText(frame, label_name, text_org, text_font, text_font_scale, text_color, text_stroke, cv2.LINE_AA )

        #print(label_id, confidence)

        cv2.imwrite(img_name, cv2.resize(roi_img,(500, 500)))
        #print(roi_img)

        color = (255, 0 , 0) #BGR 
        stroke = 2 #thickness 
        end_coordinate_x = x + w
        end_coordinate_y = y + h
         
        cv2.rectangle(frame, (x, y), (end_coordinate_x, end_coordinate_y), color, stroke)
    

    #cv2.imshow('face-cam', frame) # image-show frame bir RGB renk uzayinda bir numpy dizisi
    cv2.imshow('face-camera', frame) # gray ise artik farklı bir renk uzayinda numpy dizisi
    
    if cv2.waitKey(20) & 0xFF == 27 : # bu kod bloguna geldigin 20 milisaniye tusa basacak mısın diye bekler /// 0xFF ise built-in bir python yapısı olup dusuk oncelikli 32 bite bakarak stdinputtan degeri asciiye cevirir 
         ## 27 ise klavyede esc tusuna karsilik gelmektedir.
        break

cap.release()
cv2.destroyAllWindows()
print('FaceCamden goruntu alma islemi kullanici tarafindan durduruldu.')    

 
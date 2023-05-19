import numpy as np
import cv2
import os #haarcascade algoritmasinin pathini vermek icin.


face_cascade = cv2.CascadeClassifier('src\\cascades\\data\\haarcascade_frontalface_default.xml') # frame'den frontal yüzü cikarmak icin egitilmis olan haarcascade modeli / machine learning algoritmalari ile egitildi.
#kendi use-case'ine göre egitilmis hazir modellerden birini secebilirsin.



cap = cv2.VideoCapture(0) #varsayılan kamerayı secer

while(True):
    #cap.read methodu sayesinde olusturdugumuz VideCapture nesnesi ile görüntü alırız ret- true ya da false olurken / frame ise alinan goruntunun numpy dizi karsigilidir.
    #ret -> true demek goruntu basarili sekilde alabildi// false ise goruntu basarili bir sekilde alamadi anlamina gelir.
    ret, frame = cap.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.05, minNeighbors=5)

    for (x, y, w, h) in faces:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
    

    #cv2.imshow('face-cam', frame) # image-show frame bir RGB renk uzayinda bir numpy dizisi
    cv2.imshow('face-com', frame) # gray ise artik farklı bir renk uzayinda numpy dizisi
    
    if cv2.waitKey(20) & 0xFF == 27 : # bu kod bloguna geldigin 20 milisaniye tusa basacak mısın diye bekler /// 0xFF ise built-in bir python yapısı olup dusuk oncelikli 32 bite bakarak stdinputtan degeri asciiye cevirir 
         ## 27 ise klavyede esc tusuna karsilik gelmektedir.
        break

cap.release()
cv2.destroyAllWindows()
print('FaceCamden goruntu alma islemi kullanici tarafindan durduruldu.')    

 
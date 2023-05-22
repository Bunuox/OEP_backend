#eger frame'de yüz yoksa siler.
import glob
import cv2
import os
import shutil
import datetime

path_cascade = os.path.join('src', 'cascades', 'data', 'haarcascade_frontalface_default.xml')
face_cascade = cv2.CascadeClassifier(path_cascade)

path_frames = os.path.join('*.png') #frameleri alacagimiz dizini burada tutacagiz. daha sonra buraya tekrar bak.

class ImageData:
    def __init__(self, im, file_name) :
        self.im = im
        self.file_name = file_name
        
        

image_numpy_array_list =[]

for file_name in glob.glob(path_frames):
    image_path = os.path.join(file_name) # daha sonra dosya yolu ici bunu yazıyorym suan mantıksız gozukse bile eger folder'lar altında olursa buna eklencek.
    im = cv2.imread(image_path)
    imageData = ImageData(im, file_name)
    image_numpy_array_list.append(imageData)


for image_numpy_array in image_numpy_array_list: #yani suan image_numpy_array istedigim numpy formatında.
    gray = cv2.cvtColor(image_numpy_array.im, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, scaleFactor =1.01, minNeighbors=5)
    if len(faces) == 0:
        print(image_numpy_array.file_name + " ->" + "yüz yok")
        shutil.move(image_numpy_array.file_name, os.path.join("garbage", str(datetime.datetime.now()) + ".png"))
        print(image_numpy_array.file_name + " ->" + "bu nedenle garbage altina kondu resim")
    else :
        print(image_numpy_array.file_name + " ->" + "yüz var") 
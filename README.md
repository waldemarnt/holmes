# Holmes

Holmes is a software to detect images in another images using JavaCV and OpenCV.

  - Send the images to holmes and the url to save the new image
  - Holmes will crop for you based in the area of needed image
  - A new image will be created based in the needed image


### Installation
Usign eclipse with maven: download from git and export as runnable jar file.
After the creation of jar file, you can make the image match passing parameter for the holmes.jar like this.
```sh
$ holmes.jar "C:\\Users\\Waldema\\Desktop\\imagem1.jpg" "C:\\Users\\Waldema\\Desktop\\image-to-find.jpg" "C:\\Users\\Waldema\\Desktop\\new-image.jpg" 300 500 true
```
The first parameter is the big image.

Second parameter is the  image to search in this big image

Third parameter is the new image created after find

Fourth parameter is the width of new image

Fifth parameter is the height of new image

Last parameter is a boolean to show window with new image preview

### Libs

We use 2 libs to make it happens

* bytedeco/javacv
* OpenCv



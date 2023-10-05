package controller;

import model.ShapePhotoAlbum;
import model.ShapePhotoAlbumModel;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * PhotoAlbumBuilderImp class implements {@code PhotoAlbumBuilder<T>} where the type T is set as the
 * ShapePhotoAlbum interface. It is used to be passed in the FileParser class.
 */
public class PhotoAlbumBuilderImp implements PhotoAlbumBuilder<ShapePhotoAlbum> {
  private ShapePhotoAlbum model = new ShapePhotoAlbumModel();

  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> addShape(String shapeName, String shapeType,
                 double xPoint, double yPoint, double firstParameter, double secondParameter,
                 double color1, double color2, double color3) {
    this.model.addShape(shapeName, shapeType, xPoint, yPoint, firstParameter, secondParameter,
                        color1, color2, color3);
    return this;
  }


  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> moveShape(String shapeName, double newXPoint,
                                                           double newYPoint) {
    this.model.moveShape(shapeName, newXPoint, newYPoint);
    return this;
  }

  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> removeShape(String shapeName) {
    this.model.removeShape(shapeName);
    return this;
  }

  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> resizeShape(String shapeName, double newFirstValue,
                                                             double newSecondValue) {
    this.model.resizeShape(shapeName, newFirstValue,newSecondValue);
    return this;
  }

  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> changeColor(String shapeName, double newColor1,
                                                             double newColor2, double newColor3) {
    this.model.changeColor(shapeName, newColor1, newColor2, newColor3);
    return this;
  }

  @Override
  public PhotoAlbumBuilder<ShapePhotoAlbum> takeSnapshot(String description) {
    if (description == null) {
      description = "";
    }
    this.model.takeSnapshot(description);
    return this;
  }

  /**
   * Build up the photo album model.
   * @return A ShapePhotoAlbum model.
   */
  public ShapePhotoAlbum build() {
    return model;
  }

}

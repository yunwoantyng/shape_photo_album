package controller;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * PhotoAlbumBuilder interface serves as a bridge between the file commands and the photo album
 * model application. It is used to for PhotoAlbumBuilderImp class to implement.
 */
public interface PhotoAlbumBuilder<T> {

  /**
   * Add a shape to the model based on the file commands.
   * @param shapeType A String representing the type of the shape.
   * @param shapeName A String representing the name of the shape.
   * @param firstParameter A double representing the value of the 1st length of the shape.
   * @param secondParameter A double representing the value of the 2nd length of the shape.
   * @param xPoint A double representing x-cor.
   * @param yPoint A double representing y-cor.
   * @param color1 A double representing the 1st number in the brackets.
   * @param color2 A double representing the 2nd number in the brackets.
   * @param color3 A double representing the 3rd number in the brackets.
   * @return this.
   */
  PhotoAlbumBuilder<T> addShape(String shapeName, String shapeType, double xPoint, double yPoint,
                                  double firstParameter, double secondParameter,
                                  double color1, double color2, double color3);

  /**
   * Remove a shape from the photo album based on the file commands.
   * @param shapeName A String representing the target name of the shape.
   * @return this.
   */
  PhotoAlbumBuilder<T> removeShape(String shapeName);

  /**
   * Move the current shape to a designated new position based on the file commands.
   * @param shapeName A String representing the target name of the shape.
   * @param newXPoint A double representing a new x-cor.
   * @param newYPoint A double representing a new y-cor.
   * @return this.
   */
  PhotoAlbumBuilder<T> moveShape(String shapeName, double newXPoint, double newYPoint);

  /**
   * Change the size of the shape based on the file commands.
   * @param shapeName A String representing the target name of the shape.
   * @param newFirstValue A double representing the new value of the 1st length of the shape.
   * @param newSecondValue A double representing the new value of the 2nd length of the shape.
   * @return this.
   */
  PhotoAlbumBuilder<T> resizeShape(String shapeName, double newFirstValue, double newSecondValue);

  /**
   * Change the color of the shape based on the file commands.
   * @param shapeName A String representing the target name of the shape.
   * @param newColor1 A double representing the new 1st number in the brackets.
   * @param newColor2 A double representing the new 2nd number in the brackets.
   * @param newColor3 A double representing the 3rd number in the brackets.
   * @return this.
   */
  PhotoAlbumBuilder<T> changeColor(String shapeName, double newColor1, double newColor2,
                                     double newColor3);

  /**
   * Take a snapshot that captures all the information at a moment based on the file commands.
   * @param description A String representing a comment that comes along with a snapshot.
   * @return this.
   */
  PhotoAlbumBuilder<T> takeSnapshot(String description);

  /**
   * Build up the photo album model.
   * @return A <T></T> representing the model.
   */
  T build();
}

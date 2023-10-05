package model;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * IShape interface is used for the implementation of shapes.
 */
public interface IShape {
  /**
   * Gets the name of the shape, e.g. "R". (as shown in the homework instruction)
   * @return A String representing the name of the shape.
   */
  String getShapeName();

  /**
   * Gets the type of the shape, e.g. "rectangle". (as shown in the homework instruction)
   * @return A String representing the type of the shape.
   */
  String getShapeType();

  /**
   * Gets the current position of the shape.
   * @return A Point2D object representing the position of the shape.
   */
  Point2D getPosition();

  /**
   * Gets the current color of the shape.
   * @return A Color object representing the color of the shape.
   */
  Color getColor();

  /**
   * Gets the array of sizes of the shape.
   * @return An array representing the size of the shape.
   */
  double[] getSize();

  /**
   * Moves the current shape to a designated new position.
   * @param newPoint A Point2D object representing the new position of the shape.
   */
  IShape move(Point2D newPoint);

  /**
   * Changes the size of the shape.
   * @param newFirstValue A double representing the new value of the 1st length of the shape.
   * @param newSecondValue A double representing the new value of the 2nd length of the shape.
   */
  IShape resize(double newFirstValue, double newSecondValue);

  /**
   * Changes the color of the shape.
   * @param newColor A Color object representing the new color of the shape.
   */
  IShape changeColor(Color newColor);


}

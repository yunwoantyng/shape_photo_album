package model;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * Rectangle concrete class extending AbstractShape abstract class is used to represent a rectangle.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;
  private String shapeType;

  /**
   * The constructor for Rectangle class.
   */
  public Rectangle(String shapeName, double width, double height,
                   Point2D position, Color color) throws IllegalArgumentException {
    super(shapeName, position, color);
    this.width = width;
    this.height = height;
    this.shapeType = "rectangle";
  }

  /**
   * Gets the type of the shape.
   * @return A String representing the type of the shape. In this class, the type is "rectangle".
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }


  @Override
  public double[] getSize() {
    return new double[]{this.width, this.height};
  }

  @Override
  public IShape move(Point2D newPoint) throws IllegalArgumentException {
    if (newPoint == null) {
      throw new IllegalArgumentException("The new position cannot be null.");
    }
    return new Rectangle(this.shapeName, this.width, this.height, newPoint, this.color);
  }

  @Override
  public IShape resize(double newFirstValue, double newSecondValue)
          throws IllegalArgumentException {
    if (newFirstValue <= 0 || newSecondValue <= 0) {
      throw new IllegalArgumentException("The new values cannot be zero or negative.");
    }
    return new Rectangle(this.shapeName, newFirstValue, newSecondValue, this.position, this.color);
  }

  @Override
  public IShape changeColor(Color newColor) throws IllegalArgumentException {
    if (newColor == null) {
      throw new IllegalArgumentException("The new color cannot be null.");
    }
    return new Rectangle(this.shapeName, this.width, this.height, this.position, newColor);
  }

  /**
   * Represents Rectangle class in a designated format.
   * e.g. Name: R
   *      Type: rectangle
   *      Min corner: (100.0,300.0), Width: 25.0, Height: 100.0, Color: (1.0,0.0,0,0)
   */
  @Override
  public String toString() {
    return String.format(
            "Name: %s\nType: %s\nMin corner: %s, Width: %.1f, Height: %.1f, Color: %s",
                    this.shapeName, this.shapeType, this.position.toString(),
                    this.width, this.height, this.color.toString());
  }

}

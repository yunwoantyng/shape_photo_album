package model;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * Oval concrete class extending AbstractShape abstract class is used to represent an oval.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;
  private String shapeType;

  /**
   * The constructor for Oval class.
   */
  public Oval(String shapeName, double xRadius, double yRadius,
              Point2D position, Color color) throws IllegalArgumentException {
    super(shapeName, position, color);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    this.shapeType = "oval";
  }

  /**
   * Gets the type of the shape.
   * @return A String representing the type of the shape. In this class, the type is "oval".
   */
  @Override
  public String getShapeType() {
    return this.shapeType;
  }

  @Override
  public double[] getSize() {
    return new double[]{this.xRadius, this.yRadius};
  }

  @Override
  public IShape move(Point2D newPoint) throws IllegalArgumentException {
    if (newPoint == null) {
      throw new IllegalArgumentException("The new position cannot be null.");
    }
    return new Oval(this.shapeName, this.xRadius, this.yRadius, newPoint, this.color);
  }

  @Override
  public IShape resize(double newFirstValue, double newSecondValue)
          throws IllegalArgumentException {
    if (newFirstValue <= 0 || newSecondValue <= 0) {
      throw new IllegalArgumentException("The new values cannot be zero or negative.");
    }
    return new Oval(this.shapeName, newFirstValue, newSecondValue, this.position, this.color);
  }

  @Override
  public IShape changeColor(Color newColor) throws IllegalArgumentException {
    if (newColor == null) {
      throw new IllegalArgumentException("The new color cannot be null.");
    }
    return new Oval(this.shapeName, this.xRadius, this.yRadius, this.position, newColor);
  }

  /**
   * Represents Oval class in a designated format.
   * e.g. Name: O
   *      Type: oval
   *      Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1,0)
   */
  @Override
  public String toString() {
    return String.format(
            "Name: %s\nType: %s\nCenter: %s, X radius: %.1f, Y radius: %.1f, Color: %s",
            this.shapeName, this.shapeType, this.position.toString(),
            this.xRadius, this.yRadius, this.color.toString());
  }
}

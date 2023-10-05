package model;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * AbstractShape abstract class implementing IShape interface is used for extensions of shapes.
 */
public abstract class AbstractShape implements IShape {
  protected String shapeName;
  protected Point2D position;
  protected Color color;

  /**
   * The constructor for AbstractShape class.
   * @param shapeName A String representing the name of the shape.
   * @param position A Point2D object representing the position of the shape.
   * @param color A Color object representing the color of the shape.
   * @throws IllegalArgumentException if any of the parameters is null, empty or negative.
   */
  public AbstractShape(String shapeName, Point2D position, Color color)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0 || position == null || color == null) {
      throw new IllegalArgumentException("The parameter cannot be null or empty.");
    }

    this.shapeName = shapeName;
    this.position = position;
    this.color = color;
  }

  @Override
  public String getShapeName() {
    return this.shapeName;
  }

  @Override
  public String getShapeType() {
    // the type of the shape will be defined in shape concrete classes.
    return null;
  }

  @Override
  public Point2D getPosition() {
    return this.position;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public double[] getSize() {
    return null;
  }

  @Override
  public IShape move(Point2D newPoint) throws IllegalArgumentException {
    if (newPoint == null) {
      throw new IllegalArgumentException("The new position cannot be null.");
    }
    return null;
  }

  @Override
  public IShape resize(double newFirstValue, double newSecondValue)
          throws IllegalArgumentException {
    if (newFirstValue <= 0 || newSecondValue <= 0) {
      throw new IllegalArgumentException("The new values cannot be zero or negative.");
    }
    return null;
  }

  @Override
  public IShape changeColor(Color newColor) throws IllegalArgumentException {
    if (newColor == null) {
      throw new IllegalArgumentException("The new color cannot be null.");
    }
    return null;
  }
}

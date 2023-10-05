package model;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * Color class is used to represent the color of the shape.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * The constructor for Color class.
   * @param red A double representing the 1st number in the brackets.
   * @param green A double representing the 2nd number in the brackets.
   * @param blue A double representing the 3rd number in the brackets.
   * @throws IllegalArgumentException if any of the three number in the brackets is not 0 or 1.
   */
  public Color(double red, double green, double blue) throws IllegalArgumentException {
    if (invalidColor(red, green, blue)) {
      throw new IllegalArgumentException("The value of the color should be between 0 to 255.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Check if any of the color value is out of bound.
   */
  private boolean invalidColor(double red, double green, double blue) {
    return red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255;
  }

  /**
   * Get the value of the color red.
   * @return A double representing the 1st number in the brackets.
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Get the value of the color green.
   * @return A double representing the 2nd number in the brackets.
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Get the value of the color blue.
   * @return A double representing the 3rd number in the brackets.
   */
  public double getBlue() {
    return this.blue;
  }

  /**
   * Represents the Color class in a designated format, e.g. (1.0,0.0,0,0).
   */
  @Override
  public String toString() {
    return String.format("(%.1f,%.1f,%.1f)", this.red, this.green, this.blue);
  }
}

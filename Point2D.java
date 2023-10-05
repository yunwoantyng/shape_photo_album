package model;

import java.util.Objects;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * Point2D class is used to represent the position in 2D dimension.
 */
public final class Point2D {
  private final double x;
  private final double y;

  /**
   * The constructor for Point2D class.
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets the value of x coordinate.
   * @return A double representing x-cor.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets the value of y coordinate.
   * @return A double representing y-cor.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Represents Point2D in a designated format, e.g. (200.0,200.0).
   */

  @Override
  public String toString() {
    return String.format("(%.1f,%.1f)", this.x, this.y);
  }

  /**
   * The two objects of Point2D class are equals if the values of their x-cor are the same and also
   * the values of their y-cor are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    var that = (Point2D) obj;
    return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x)
           && Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

}


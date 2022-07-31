package cs3500.model.shape;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents a mutable shape that has a color, size, form, and color. The form represents the
 * geometric or non-geometric form of the shape. The abstract class shares all the
 * common functionalities of all shapes.
 */
public abstract class AShape implements IShape {
  protected String name;
  protected double height;
  protected double width;
  protected Color color;
  protected final Point2D position;

  /**
   * Constructs a shape with a color, position. The shape is a mutable shape where the
   * position, color and size can all be changed according to the user needs.
   *
   * @param w is the width of the shape.
   * @param h is the height of the shape.
   * @param x is the x-coordinate of the shape.
   * @param y is the y-coordinate of the shape.
   * @param r is the red component in an sRGB color.
   * @param g is the green component in an sRGB color.
   * @param b is the blue component in an sRGB color.
   * @throws IllegalArgumentException if any RGB value is not in the range [0, 255].
   * @throws IllegalArgumentException if high or width are less than or equal to 0.
   */
  protected AShape(double w, double h, double x, double y, int r, int g, int b) {
    checkRGB(r, g, b);
    checkDimensions(w, h);
    this.color = new Color(r, g, b);
    this.position = new Point2D.Double(x, y);
    this.height = h;
    this.width = w;
    this.name = null;
  }

  /**
   * Copies the shape. The copy is a deep copy.
   *
   * @return a copy of the shape.
   */
  public abstract IShape copy();

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void move(double x, double y) {
    this.position.setLocation(x, y);
  }

  @Override
  public void setColor(int r, int g, int b) {
    boolean validate = Stream.of(r, g, b).allMatch((val -> val >= 0 && val <= 255));
    if (!validate) {
      throw new IllegalArgumentException("A parameter is not in the range [0, 255]");
    }
    this.color = new Color(r, g, b);
  }

  @Override
  public int getRed() {
    return color.getRed();
  }

  @Override
  public int getBlue() {
    return color.getBlue();
  }

  @Override
  public int getGreen() {
    return color.getGreen();
  }

  @Override
  public double getX() {
    return this.position.getX();
  }

  @Override
  public double getY() {
    return this.position.getY();
  }

  @Override
  public void reScale(double w, double h) {
    checkDimensions(w, h);
    height = h;
    width = w;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Checks that the RGB values are in the range [0, 255].
   * @param r is the red component of the RGB.
   * @param g is the green component of the RGB.
   * @param b is the blue component of the RGB.
   * @throws IllegalArgumentException if any value of the RGB is not in the range [0, 255].
   */
  protected void checkRGB(double r, double g, double b) {
    boolean validateColors = Stream.of(r, g, b).allMatch((val -> val >= 0 && val <= 255));
    if (!validateColors) {
      throw new IllegalArgumentException("A parameter is not in the range [0, 255]");
    }
  }

  /**
   * Checks that the dimensions are valid i.e. the dimensions are not negative.
   *
   * @param h is the height.
   * @param w is the width.
   * @throws IllegalArgumentException is the height or width are <= 0.
   */
  protected void checkDimensions(double w, double h) {
    if (w <= 0 || h <= 0) {
      throw new IllegalArgumentException("The parameters need to be > 0");
    }
  }

  /**
   * Check is any of the given values is null.
   *
   * @param o is an array of parameters which will be checked for null.
   * @throws IllegalArgumentException if any value is null in the array.
   */
  protected void checkForNulls(Object... o) {
    if (Arrays.stream(o).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Cannot have null values.");
    }
  }
}

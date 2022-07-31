package cs3500.model.shape;

/**
 * Create a circular/elliptical geometric shape.
 */
public class Ellipse extends AShape {

  /**
   * Creates an elliptical circular geometric shape with a color and position.
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
  public Ellipse(double w, double h, double x, double y, int r, int g, int b) {
    super(w, h, x, y, r, g, b);
  }

  @Override
  public IShape copy() {
    IShape e = new Ellipse(getWidth(), getHeight(), getX(), getY(), getRed(),
            getGreen(), getBlue());
    e.setName(this.getName());
    return e;
  }

  @Override
  public void visitor(ISVisitor visitor) {
    checkForNulls(visitor);
    visitor.visitEllipse(this);
  }
}

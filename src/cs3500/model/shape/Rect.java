package cs3500.model.shape;

/**
 * Represents a rectangular parallelogram with a color and a position.
 */
public class Rect extends AShape {

  /**
   * Creates a rectangular shape with a color and a shape.
   *
   * @param w is the width of the rectangle.
   * @param h is the height of the rectangle.
   * @param x is the x-coordinate of the rectangle.
   * @param y is the y-coordinate of the rectangle.
   * @param r is red-component for an RGB color.
   * @param g is the green-component for an RGB color.
   * @param b is the blue-component for an RGB color.
   * @throws IllegalArgumentException if any RGB value is not in the range [0, 255].
   * @throws IllegalArgumentException if high or width are less than or equal to 0.
   */
  public Rect(double w, double h, double x, double y, int r, int g, int b) {
    super(w, h, x, y, r, g, b);
  }

  @Override
  public IShape copy() {
    IShape r = new Rect(getWidth(), getHeight(), getX(), getY(), getRed(),
            getGreen(), getBlue());
    r.setName(this.getName());
    return r;
  }

  @Override
  public void visitor(ISVisitor visitor) {
    checkForNulls(visitor);
    visitor.visitRect(this);
  }
}

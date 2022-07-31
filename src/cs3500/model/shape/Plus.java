package cs3500.model.shape;

/**
 * Represents a plus sign shape in an Animation.
 */
public class Plus extends AShape {

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
  public Plus(double w, double h, double x, double y, int r, int g, int b) {
    super(w, h, x, y, r, g, b);
  }

  @Override
  public IShape copy() {
    IShape r = new Plus(getWidth(), getHeight(), getX(), getY(), getRed(),
            getGreen(), getBlue());
    r.setName(this.getName());
    return r;
  }

  @Override
  public void visitor(ISVisitor visitor) {
    visitor.visitPlus(this);
  }
}

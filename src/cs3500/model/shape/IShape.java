package cs3500.model.shape;

/**
 * Represents a mutable shape that has the ability to move, change size and set its color. Details
 * all the capabilities of the shape.
 */
public interface IShape {

  /**
   * Move the shape to the given x and y coordinates. The coordinates can be any real number.
   *
   * @param x is the new x-coordinate of the shape.
   * @param y is the new y-coordinate of the shape.
   */
  void move(double x, double y);

  /**
   * Sets the color to the given sRGB combination of values. The combination of RBG values must be
   * a valid combination. The range of values for each parameter is 0 to 255.
   *
   * @param r is intensity of the red color as an integer.
   * @param g is the intensity of the green color as an integer.
   * @param b is the intensity of the blue color as an integer.
   * @throws IllegalArgumentException is the any parameter is not in the range [0, 255].
   */
  void setColor(int r, int g, int b);

  /**
   * Scales the shape by the shape to the given dimensions. The parameters represent the value to
   * which one dimension will re scaled to.
   *
   * @param w is the dimension in the x-direction (width).
   * @param h is the dimension in the y-direction (height).
   * @throws IllegalArgumentException if the scalar value <= 0.
   */
  void reScale(double w, double h);

  /**
   * Gets the RED component of the shapes sRGB color.
   *
   * @return the integer value of the red intensity in the sRGB.
   */
  int getRed();

  /**
   * Gets the BLUE component of the shapes sRGB color.
   *
   * @return the integer value of the blue intensity in the sRGB.
   */
  int getBlue();

  /**
   * Gets the GREEN component of the shapes sRGB color.
   *
   * @return the integer value of the green intensity in the sRGB.
   */
  int getGreen();

  /**
   * Gets the x-coordinate of the shape.
   *
   * @return the x-coordinate of the shape.
   */
  double getX();

  /**
   * Gets the y-coordinate of the shape.
   *
   * @return the y-coordinate of the shape.
   */
  double getY();

  /**
   * Gets the width of the shape. The width is the largest possible length in the x-direction within
   * the shapes boundaries.
   *
   * @return the width of the shape.
   */
  double getWidth();

  /**
   * Gets the height of the shape. The height is the largest possible length in the y-direction
   * within the shapes boundaries.
   *
   * @return the height of the shape.
   */
  double getHeight();

  /**
   * Returns a copy of the shape. The copy is a deep copy.
   *
   * @return returns a copy of the object calling the method.
   */
  IShape copy();

  /**
   * Accepts a visitor to the shape.
   *
   * @param visitor is the visitor that is visiting the class.
   * @throws IllegalArgumentException if the visitor is null.
   */
  void visitor(ISVisitor visitor);

  /**
   * Gets the name of the shape.
   */
  String getName();

  /**
   * Sets the name of the shape. The shape has null for name as default.
   *
   * @param name is the name for the shape.
   */
  void setName(String name);
}

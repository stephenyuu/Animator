package cs3500.model.transformation;

/**
 * Represents a transformation that scales the given object to a new size. This object holds the
 * data of a size change, including its start and end times, beginning dimensions, and ending
 * dimensions.
 */
public class ScaleTransform extends ATransform {
  private final double oldW;
  private final double oldH;
  private final double width;
  private final double height;

  /**
   * Sets the values for the scale of the object.
   *
   * @param start is the start tick of the transformation.
   * @param end   is the end time of the transformation.
   * @param width is the width of the new scale.
   * @param height is the height of the new scale.
   */
  public ScaleTransform(int start, int end, double oldW, double oldH, double width, double height) {
    super(start, end);
    checkDimensions(width, height);
    checkDimensions(oldW, oldH);
    this.oldW = oldW;
    this.oldH = oldH;
    this.width = width;
    this.height = height;
  }

  @Override
  public TransformType getType() {
    return TransformType.SCALE;
  }

  @Override
  public double[] getData() {
    return new double[] {width, height};
  }

  @Override
  public double[] getOldData() {
    return new double[] {oldW, oldH};
  }

  @Override
  public ITransform copy() {
    return new ScaleTransform(start, end, oldW, oldH, width, height);
  }

  @Override
  public void visitor(ITVisitor visitor) {
    checkForNulls(visitor);
    visitor.visitScale(this);
  }

  @Override
  public void acceptShapeMutationVisitor(IShapeMutationVisitor shapeMutationVisitor) {
    checkForNulls(shapeMutationVisitor);
    shapeMutationVisitor.visitAndApplyScaleTransform(this);
  }

  /**
   * Checks that the dimensions are valid.
   *
   * @param width is the dimension that a shape take in the x-direction.
   * @param height is the dimension that a shape take in the y-direction.
   * @throws IllegalArgumentException if the width or height <= 0.
   */
  private void checkDimensions(double width, double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Dimensions cannot be <= 0");
    }
  }
}

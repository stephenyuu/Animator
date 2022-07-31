package cs3500.model.transformation;

/**
 * Represents a transformation that moves the given object to a new position. This object holds the
 * data of a move, including its start and end times, beginning position, and ending
 * position.
 */
public class PositionTransform extends ATransform {
  private final double oldX;
  private final double oldY;
  private final double x;
  private final double y;

  /**
   * Sets the position to which the transform translates the object to. Sets the time
   * interval for it as well.
   *
   * @param start is the start tick of the transformation.
   * @param end   is the end time of the transformation.
   * @param x is the x-coordinate.
   * @param y is the y-coordinate.
   *
   */
  public PositionTransform(int start, int end, double oldX, double oldY, double x, double y) {
    super(start, end);
    this.oldX = oldX;
    this.oldY = oldY;
    this.x = x;
    this.y = y;
  }

  @Override
  public TransformType getType() {
    return TransformType.POSITION;
  }

  @Override
  public double[] getData() {
    return new double[] {x, y};
  }

  @Override
  public double[] getOldData() {
    return new double[] {oldX, oldY};
  }

  @Override
  public ITransform copy() {
    return new PositionTransform(start, end, oldX, oldY, x, y);
  }

  @Override
  public void visitor(ITVisitor visitor) {
    checkForNulls(visitor);
    visitor.visitPosition(this);
  }

  @Override
  public void acceptShapeMutationVisitor(IShapeMutationVisitor shapeMutationVisitor) {
    checkForNulls(shapeMutationVisitor);
    shapeMutationVisitor.visitAndApplyPositionTransform(this);
  }
}

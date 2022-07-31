package cs3500.model.transformation;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a transformation for a given object. A transformation happens in a time interval,
 * and it refers to a change in the field of the object. This abstract class shares the common
 * functionalities of different transformations.
 */
public abstract class ATransform implements ITransform {
  protected final int start;
  protected final int end;

  /**
   * Sets the time interval for the transformation.
   *
   * @param start is the start tick of the transformation.
   * @param end is the end time of the transformation.
   */
  protected ATransform(int start, int end) {
    checkValidInterval(start, end);
    this.start = start;
    this.end = end;
  }

  @Override
  public abstract TransformType getType();

  @Override
  public abstract double[] getData();

  @Override
  public abstract double[] getOldData();

  @Override
  public int getEnd() {
    return end;
  }

  @Override
  public int getStart() {
    return start;
  }

  @Override
  public abstract ITransform copy();

  @Override
  public abstract void visitor(ITVisitor visitor);

  @Override
  public abstract void acceptShapeMutationVisitor(IShapeMutationVisitor shapeMutationVisitor);

  /**
   * Checks if the time interval is consistent. This means that the time interval is not negative
   * or if the start time is greater than the end time.
   *
   * @param start is the start of the time interval.
   * @param end is the end of the time interval.
   * @throws IllegalArgumentException if the interval is inconsistent.
   */
  private void checkValidInterval(int start, int end) {
    if (start > end || start < 0) {
      throw new IllegalArgumentException("Invalid tick interval.");
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

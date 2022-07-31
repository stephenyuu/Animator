package cs3500.model.transformation;

/**
 * Represents a transformation in an animation. A transformation refers to any type of change that
 * occurs to a geometric shape in an animation i.e. a color change, a position change, a scaling
 * change.
 */
public interface ITransform {

  /**
   * Gets the type of transformation the transformation is.
   *
   * @return the type of transformation for the transformation.
   */
  TransformType getType();

  /**
   * Returns the transformation values. Each transformation represents a change in the
   * values/fields of an object, the method returns the values which the object is changing into.
   *
   * @return the transformation values.
   */
  double[] getData();

  /**
   * Returns the original values. Each original value represents the state of the object before the
   * transformation.
   *
   * @return the original values prior to the transformation.
   */
  double[] getOldData();

  /**
   * Gets the interval in which the transformation ends.
   *
   * @return the tick at which the transformation ends.
   */
  int getEnd();

  /**
   * Gets the start time of the transform.
   *
   * @return the start time of the transformation.
   */
  int getStart();

  /**
   * Makes a copy of the transform. A deep copy.
   *
   * @return a copy of the transform.
   */
  ITransform copy();

  /**
   * Accepts a visitor. The visitor determines the functionality of the method.
   *
   * @param visitor is the given transformation visitor
   * @throws IllegalArgumentException if the visitor is null.
   */
  void visitor(ITVisitor visitor);

  /**
   * Accepts a visitor that mutates a shape based on the transformation.
   *
   * @param shapeMutationVisitor is the given shape mutation visitor
   */
  void acceptShapeMutationVisitor(IShapeMutationVisitor shapeMutationVisitor);
}
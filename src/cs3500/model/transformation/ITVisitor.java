package cs3500.model.transformation;

/**
 * Visitor which adds extra functionality to classes that use this visitor.
 * This visitor aims to visit the shape in an animation.
 */
public interface ITVisitor {

  /**
   * Visits the color transform and provides functionality based on the visitor type.
   * @param t is the transform that will be visited.
   */
  void visitColor(ColorTransform t);

  /**
   * Visits the position transform and provides functionality based on the visitor type.
   * @param t is the transform that will be visited.
   */
  void visitPosition(PositionTransform t);

  /**
   * Visits the scale transform and provides functionality based on the visitor type.
   * @param t is the transform that will be visited.
   */
  void visitScale(ScaleTransform t);
}

package cs3500.model.transformation;

import cs3500.model.shape.IShape;

/**
 * Visitor that adds extra functionality to the classes that uses this visitor.
 * This visitor aims to visit different transform objects in an animation and apply the mutation
 * to the shapes.
 */
public interface IShapeMutationVisitor {
  /**
   * Visits the color transform and applies the transformations to the shape object.
   *
   * @param t the transform that is visited
   * @return the updated state of the shape
   */
  IShape visitAndApplyColorTransform(ColorTransform t);

  /**
   * Visits the position transform and applies the transformation to the shape object.
   *
   * @param t the transform that is visited
   * @return the updated state of the shape
   */
  IShape visitAndApplyPositionTransform(PositionTransform t);

  /**
   * Visits the position transform and applies the transformation to the shape object.
   *
   * @param t the transform that is visited
   * @return the updated state of the shape
   */
  IShape visitAndApplyScaleTransform(ScaleTransform t);
}

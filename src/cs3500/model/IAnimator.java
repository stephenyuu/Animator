package cs3500.model;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.TransformType;

/**
 * This interface contains all the mutable operations of the simple animator. The simple animator
 * creates a simple animation for the motion of simple shapes. Measures the passing of time
 * using the notion of ticks. None of the motions will overlap and adjacent motions must agree on
 * their common endpoint (no teleportation).
 */
public interface IAnimator extends ReadAnimator {

  /**
   * Adds a shape to the animator. The shape resents a shape within the animation.
   *
   * @param name is the name/id of the shape
   * @param shape is the shape being added to the animator.
   * @param start the moment in time when the shape is being added.
   * @param end is the moment in time when the shape disappears from the animation.
   * @throws IllegalArgumentException if the shape or name is null.
   * @throws IllegalArgumentException if the shape has an id that already exists in the animation.
   * @throws IllegalArgumentException if the tick is negative or inconsistent (start > end).
   * @throws IllegalArgumentException if the shape object being added already exits under some name.
   */
  void add(String name, IShape shape, int start, int end);

  /**
   * Removes a shape to the animator. Removes all traces of the shape from the animator.
   *
   * @param name is the name/id of the shape.
   * @throws IllegalArgumentException if name is null or doesn't exist in the animation.
   */
  void remove(String name);

  /**
   * Moves the selected shape in the animation during the given time span. The shape can be moved to
   * any coordinate.
   *
   * @param name is the id.name of the shape in the model.
   * @param start is the start tick for the transformation.
   * @param end is the end tick for the animation.
   * @param xCoordinate is the x-coordinate to which the object moves to.
   * @param yCoordinate is the y-coordinate to which the object moves to.
   * @throws IllegalArgumentException if the name is null, or it doesn't exist in the model.
   * @throws IllegalArgumentException if the time interval is inconsistent i.e. start > end.
   */
  void move(String name, int start, int end, double xCoordinate, double yCoordinate);

  /**
   * Re-scales the shape into the given values in the given time frame.
   *
   * @param name is the id.name of the shape in the model.
   * @param start is the start tick for the transformation.
   * @param end is the end tick for the animation.
   * @param width is the new width of the shape.
   * @param height is the new height of the shape.
   * @throws IllegalArgumentException if the with or height are <= 0.
   */
  void reScale(String name, int start, int end, double width, double height);

  /**
   * Transforms the color of the objects in the given time frame. The object being changed
   * is identified by the name/id.
   *
   * @param name is the id.name of the shape in the model.
   * @param start is the start tick for the transformation.
   * @param end is the end tick for the animation.
   * @param r is the new red component of the RGB color to which the object changes to.
   * @param g is the new green component of the RGB color to which the object changes to.
   * @param b is the new blue component of the RGB color to which the object changes to.
   * @throws IllegalArgumentException if the name is null, or it doesn't exist in the model.
   * @throws IllegalArgumentException if the time interval is inconsistent i.e. start > end.
   * @throws IllegalArgumentException if the r, g, b are not in the interval [0, 255].
   */
  void setColor(String name, int start, int end, int r, int g, int b);

  /**
   * Deletes the most recent transformation of the given type from the animation for the given
   * shape.
   *
   * @param name is the id/name of the shape from which you want to remove the transform from.
   * @param type is the type pof transformation you want to delete.
   * @throws IllegalArgumentException if the name is not in the animation.
   * @throws IllegalArgumentException if you call the method on a shape that has not gone
   *     through any transformations.
   */
  void deleteTransform(String name, TransformType type);

  /**
   * Sets the boundary canvas for the animation.
   *
   * @param width of the canvas.
   * @param height of the canvas.
   * @throws IllegalArgumentException if the with or height <= 0;
   */
  void setBounds(int width, int height);

  /**
   * Sets the tick rate for the animation (in ticks per second).
   *
   * @param tick is the tick rate of the animation. (tick per second is the rate.)
   * @throws IllegalArgumentException if the tick rate is 0 or negative.
   */
  void setTickRate(int tick);

  /**
   * Resets all the shapes to their original state.
   * (Does not remove/clear the history of transform fo the shape, this means that the animator
   * is still keeping track of all the transforms the shape has gone through).
   */
  void resetShapes();

  /**
   * Toggles the draw mode of the animation. If true, meaning the animation is being drawn in FILL
   * MODE, toggle to false, meaning the animation is being drawn in OUTLINE MODE. And vice versa.
   */
  void toggleFill();

  /**
   * Sets the tempo for a user specified time interval.
   *
   * @param tickRate is the tick rate for the time interval.
   * @param start is the start time of the interval.
   * @param end is the end time of the interval.
   * @throws IllegalArgumentException if the interval is inconsistent or invalid (negative time).
   * @throws IllegalArgumentException if the tick rate is negative or zero.
   * @throws IllegalArgumentException if the tempo causes an overlap in already defined intervals.
   */
  void setTempo(int tickRate, int start, int end);
}

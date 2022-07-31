package cs3500.model;

import java.util.List;
import java.util.Map;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;

/**
 * This interface contains all the observable operations of the simple animator. The simple animator
 * creates a simple animation for the motion of simple shapes. Measures the passing of time
 * using the notion of ticks. None of the motions will overlap and adjacent motions must agree on
 * their common endpoint.
 */
public interface ReadAnimator {

  /**
   * Gets the shape with the given id.
   *
   * @param name is the name/id of the shape you want to get.
   * @return the shape associated with the id if the id exists in the animator or null if the shape
   *     does not exist in the animator.
   * @throws IllegalArgumentException if the given id/name is null.
   */
  IShape getShape(String name);

  /**
   * Returns the tick rate of the model. The tick rate at which the animation plays. The animation
   * will return 1 by default unless the user sets a custom tick rate.
   *
   * @return the tick rate of the animation.
   */
  int getTickRate();

  /**
   * Gets the current state of the model, where the keys are the name of the shape and the value
   * list is a list of all the transforms the shape goes through during the animation. The values
   * of the list are sorted by start. This means that the ITransforms in the list are sorted in
   * increasing order of start time.
   *
   * @return the state of the model or null if there have been no changes in the model.
   */
  Map<String, List<ITransform>> getState();

  /**
   * Gets the width of the canvas. If the dimensions have not been set yet the return will be 0.
   *
   * @return the width of the canvas.
   */
  int getCanvasWidth();

  /**
   * Gets the height of the canvas. If the dimensions have not been set yet the return will be 0.
   *
   * @return the height of the canvas.
   */
  int getCanvasHeight();

  /**
   * Gets the tick at which the given shape appears.
   *
   * @param name is the name oof the shape.
   * @throws IllegalArgumentException if the name is not in the animator.
   * @throws IllegalArgumentException if the name is null.
   */
  int getStart(String name);

  /**
   * Guest the tick at which the given shape disappears.
   *
   * @param name is the name of the shape.
   * @throws IllegalArgumentException if the name is not in the animator.
   * @throws IllegalArgumentException if the name is null.
   */
  int getEnd(String name);

  /**
   * Gets all the shapes in the animator.
   *
   * @return a list with all the shapes in the animation. If the list is empty, then there is no
   *     shapes in the animator.
   */
  List<IShape> getShapes();

  /**
   * Calculate the state of the shapes at the given tick. This method uses tweening to calculate
   * the intermediate state of shapes.
   *
   * @param tick the given tick
   * @return list of shapes in their respective state at given tick
   */
  List<IShape> calculateStatesAtTick(int tick);

  /**
   * Gets the tick at which the animation is completed.
   */
  int getEndingTick();

  /**
   * Gets the draw mode of the animation. If true, that means the animation is being drawn in FILL
   * MODE. If false, that means animation is being drawn in OUTLINE MODE.
   *
   * @return a boolean representing the draw mode of the animation.
   */
  boolean getFillHa();

  /**
   * Returns at list of all the starting and ending ticks where a transformation occurred.
   */
  List<Integer> getDiscretePlaying();

  /**
   * Gets all the intervals with modified tempos. (Slow-mo intervals). The list contains arrays,
   * where each array has the tempo info. The arrays are all size 3 and the first element is
   * the tickRate, the second the start time and the third the end time. The intervals are sorted by
   * start time.
   */
  List<int[]> getTempos();
}

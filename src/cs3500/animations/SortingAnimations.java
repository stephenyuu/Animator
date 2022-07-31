package cs3500.animations;

import java.util.List;
import cs3500.model.shape.IShape;

/**
 * Creates an animation that visualizes the behavior of an algorithm. The animation should be
 * compatible with the reader in this project.
 */
public interface SortingAnimations {

  /**
   * Exports the generated animation into the given location. The given string should be the name of
   * the file where the animation will be stored.
   */
  void export(String location);

  /**
   * Creates the animation. The animation will depend on the class type.
   */
  void create();

  /**
   * Gets the list of shapes in the original unsorted order. The list will be empty is the create
   * method is not called.
   *
   * @return the unsorted list of IShapes.
   */
  List<IShape> getUnSortedList();

  /**
   * Gets the sorted list of IShapes. The list will be empty if the creation methods os not called.
   *
   * @return the sorted list of IShapes.
   */
  List<IShape> getSortedList();

  /**
   * Sets the seed for the animation. If you want to set the seed, you need to set it before
   * creating the animation, or set the seed and then run another animation.
   *
   * @param seed is the seed for the animation.
   */
  void setSeed(int seed);
}

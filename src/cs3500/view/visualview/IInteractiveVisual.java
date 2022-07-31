package cs3500.view.visualview;

import cs3500.controller.IInteractiveFeatures;
import cs3500.view.visualview.IVisual;

/**
 * Represents an interactive view of an animation. Sets the functionality of window.
 */
public interface IInteractiveVisual extends IVisual {

  /**
   * Adds a controller to the view.
   *
   * @param feat is the controller for the view.
   */
  void addFeatures(IInteractiveFeatures feat);

  /**
   * Reset the view to the beginning of the animation.
   */
  void reset();

  /**
   * Sets the frame to the given tick.
   *
   * @param tick is the tick at which you want the frame at.
   * @throws IllegalArgumentException if the tick is negative.
   */
  void setFrame(int tick);
}

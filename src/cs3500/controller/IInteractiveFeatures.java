package cs3500.controller;

import cs3500.view.visualview.IInteractiveVisual;

/**
 * Represent a controller for a GUI for the  interactive view of an animation. The controller has
 * the ability to play, pause, restart, enable/disable looping, and increase/decrease tick rate
 * of the animation.
 */
public interface IInteractiveFeatures extends IBasicController {

  /**
   * Sets the view for the controller.
   *
   * @param view is the view for the controller.
   */
  void setView(IInteractiveVisual view);

  /**
   * Pause the animation if the animation is currently playing.
   * Play the animation if the animation is currently paused.
   */
  void pauseResume();

  /**
   * Restart the animation from the beginning.
   */
  void restart();

  /**
   * Enables looping if looping is currently off.
   * Disables looping if looping is currently on.
   */
  void looping();

  /**
   * Increase the tick rate of the animation by 1.
   */
  void increaseSpeed();

  /**
   * Decrease the tick rate of the animation by 1.
   */
  void decreaseSpeed();

  /**
   * Exits the animation by closing the window in which the animation was playing.
   */
  void exitProgram();

  /**
   * Toggles the drawing mode that the animation is in. The two modes are FILL MODE and
   * OUTLINE MODE.
   */
  void toggleFillOutline();

  /**
   * Toggle discrete playing mode for the animation. The mode will display the start tick and end
   * tick of each transformation occurring in the animation.
   */
  void toggleDiscretePlaying();
}

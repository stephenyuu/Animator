package cs3500.controller;

/**
 * Represents a controller for the visual view of an animation. Forwards information from the
 * model to the view components.
 */
public interface IBasicController {

  /**
   * Starts the program. The method will make the window visible and display the animation. The
   * window remains open even after the animation is done playing, it must be manually closed.
   */
  void start();
}

package cs3500.view.visualview;

import cs3500.view.IView;

/**
 * Represents a visual view of an animation. Sets the functionality of window.
 */
public interface IVisual extends IView {

  /**
   * Make the window visible.
   */
  void makeVisible();

  /**
   * Increment the clock after every delay period of the timer. This manually keeps track of the
   * current time and allows the view to know what the states of shapes at a given tick are.
   */
  void moveFrame();
}

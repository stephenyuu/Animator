package cs3500.controller;

import javax.swing.Timer;
import cs3500.model.IAnimator;
import cs3500.view.visualview.IVisual;

/**
 * Represents a controller for the visual view. This controller keeps track of time and
 * tells the view when to draw and repaint the animation. This controller also moves the clock in
 * the animation.
 */
public class Controller implements IBasicController {
  private final IVisual view;
  private final Timer timer;

  /**
   * Constructs a controller that initiates the visual view. Keeps track of time.
   *
   * @param view is the view for the animation. In this case it's a visual view.
   */
  public Controller(IVisual view, IAnimator model) {
    this.view = view;
    this.timer = new Timer(1000 / model.getTickRate(), e -> {
      view.render();
      view.moveFrame();
    });
  }

  @Override
  public void start() {
    view.makeVisible();
    timer.start();
  }
}

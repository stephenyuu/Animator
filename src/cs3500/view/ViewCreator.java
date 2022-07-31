package cs3500.view;

import cs3500.model.ReadAnimator;
import cs3500.view.textualviews.svg.SVGView;
import cs3500.view.textualviews.text.TextView;
import cs3500.view.visualview.InteractiveView;
import cs3500.view.visualview.VisualView;

/**
 * Factory of views for the animator.
 */
public class ViewCreator {
  private final ReadAnimator anim;
  private final Appendable ap;

  /**
   * Creates a creator that will use the given animator.
   * @param anim is the animator that the views will use.
   */
  public ViewCreator(ReadAnimator anim, Appendable ap) {
    this.anim = anim;
    this.ap = ap;
  }

  /**
   * Factory method to produce the different view.
   *
   * @param type is a string that represent the type of the view.
   */
  public IView factory(String type) {
    switch (type) {
      case "visual":
        return new VisualView(this.anim);
      case "text":
        return new TextView(this.anim, this.ap);
      case "svg":
        return new SVGView(this.anim, this.ap);
      case "interactive":
        return new InteractiveView(this.anim);
      default:
        throw new IllegalArgumentException("Invalid type !");
    }
  }
}

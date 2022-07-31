package cs3500.view.textualviews.text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import cs3500.model.ReadAnimator;
import cs3500.view.IView;

/**
 * Represents views that are textual or consists of strings.
 */
public abstract class TextualView implements IView {
  protected final ReadAnimator animator;
  protected final Appendable output;

  /**
   * Instantiates a text view for the animation.
   *
   * @param animator is the model for the animator.
   * @param output is the location to switch the view is output to.
   * @throws IllegalArgumentException if any parameters are null.
   */
  protected TextualView(ReadAnimator animator, Appendable output) {
    if (Objects.isNull(animator) || Objects.isNull(output)) {
      throw new IllegalArgumentException("The parameters cannot be null");
    }
    this.animator = animator;
    this.output = output;
  }

  @Override
  public abstract void render();

  /**
   * Adds the SVG tags to the output location.
   *
   * @param list is the svg tags in a list format.
   */
  protected void render(List<String> list) {
    try {
      output.append(String.join("\n", list));
    }
    catch (IOException e) {
      throw new IllegalStateException("Failed to render the svg.");
    }
  }
}

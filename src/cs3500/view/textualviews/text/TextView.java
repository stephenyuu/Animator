package cs3500.view.textualviews.text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import cs3500.model.ReadAnimator;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;

/**
 * Represents a textual view for the animation.
 */
public class TextView extends TextualView {

  /**
   * Instantiates a text view for the animation.
   *
   * @param animator is the model for the animator.
   * @param output   is the location to switch the view is output to.
   * @throws IllegalArgumentException if any parameters are null.
   */
  public TextView(ReadAnimator animator, Appendable output) {
    super(animator, output);
  }

  @Override
  public void render() {
    List<String> text = new ArrayList<>();
    Map<String, List<ITransform>> state = animator.getState();
    text.add(String.format("Canvas %d %d", animator.getCanvasWidth(), animator.getCanvasHeight()));

    if (Objects.isNull(state)) {
      // goes through all the shapes and adds them to the canvas.
      for (IShape s : animator.getShapes()) {
        text.add(addShape(s.getName()));
      }
      render(text);
      return;
    }

    // run throw all the transforms.
    for (Map.Entry<String, List<ITransform>> map : state.entrySet()) {
      ITVisitor visitor = new TextVisitor();
      text.add(addShape(map.getKey()));
      for (ITransform t : map.getValue()) {
        t.visitor(visitor);
        text.add(visitor.toString());
      }
    }
    render(text);
  }

  /**
   * Makes the entry for adding a shape.
   *
   * @param name is the name of the shape.
   * @return the string entry for adding a shape.
   */
  private String addShape(String name) {
    return String.format("Add shape %s at time %.3fs", name,
            animator.getStart(name) / (double) animator.getTickRate());
  }
}

package cs3500.view.textualviews.svg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import cs3500.model.ReadAnimator;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;
import cs3500.view.textualviews.text.TextualView;

/**
 * Renders a view in SVG style compliant text.
 */
public class SVGView extends TextualView {

  /**
   * Instantiates a text view for the animation.
   *
   * @param animator is the model for the animator.
   * @param output   is the location to switch the view is output to.
   * @throws IllegalArgumentException if any parameters are null.
   */
  public SVGView(ReadAnimator animator, Appendable output) {
    super(animator, output);
  }

  @Override
  public void render() {
    List<String> svg = new ArrayList<>();
    Map<String, List<ITransform>> states = animator.getState();
    svg.add(setCanvas(animator.getCanvasWidth(), animator.getCanvasHeight()));
    ISVisitor visitor = new SVGShapeVisitor();

    // if no transformations happened then just draw the shape at their initial location.
    if (Objects.isNull(states)) {

      // goes through all the shapes and adds them to the canvas.
      for (IShape s : animator.getShapes()) {
        animator.getShape(s.getName()).visitor(visitor);
        String[] shape = visitor.toString().split("\n");
        svg.addAll(Arrays.asList(shape[0], setVisibility(s.getName()), shape[1]));
      }
      // end the svg tag.
      svg.add("</svg>");
      render(svg);
      return;
    }

    // get all the animations from the list and convert to svg format.
    for (String key : states.keySet()) {
      Deque<String> animations = new LinkedList<>();

      // get the shape in a svg tag.
      IShape s = animator.getShape(key);
      s.visitor(visitor);

      // get all the animations in a svg tag.
      List<ITransform> transforms = states.get(key);
      String tags = renderAnimationTags(transforms, s);
      String[] shape = visitor.toString().split("\n");

      // add the shape and visibility tgs around the animation tags.
      animations.add(tags);
      animations.addFirst(setVisibility(s.getName()));
      animations.addFirst(shape[0]);
      animations.addLast(shape[1]);
      svg.addAll(animations);
    }

    svg.add("</svg>");
    render(svg);
  }

  /**
   * Renders all the animation tags for the given set of transforms.
   *
   * @param transforms is the list of all transforms for a given shape.
   * @param s is the shape being transformed.
   * @return all the SVG animation tags as a string.
   */
  private String renderAnimationTags(List<ITransform> transforms, IShape s) {
    ISVisitor visitor = new SVGShapeAnimation(transforms, animator.getTickRate());
    s.visitor(visitor);
    return visitor.toString();
  }

  /**
   * Initializes the svg tag with the canvas height and width.
   *
   * @return the SVG declaration tag.
   */
  private String setCanvas(int width, int height) {
    return String.format("<svg width=\"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\" " +
            "version=\"1.1\">", width, height);
  }

  /**
   * Sets the visibility of a shape in an animation.
   *
   * @param name is the name of the shape.
   * @return an SVG tag which sets the visibility attribute of a shape.
   * @throws IllegalArgumentException if the name is not in the animation or the name is null.
   */
  private String setVisibility(String name) {
    double start = (double)animator.getStart(name) / animator.getTickRate();
    double end = (double)animator.getEnd(name) / animator.getTickRate();
    double duration = end - start;
    return String.format("<set attributeName=\"visibility\" " +
            "attributeType=\"CSS\" to=\"visible\" begin=\"%.3fs\" dur=\"%.3fs\" " +
            "fill=\"freeze\"/>", start, duration);
  }
}

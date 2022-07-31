package cs3500.view.textualviews.svg;

import java.util.ArrayList;
import java.util.List;

import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;

/**
 * Abstract class that represents the animation tags of all shape SVG transforms. Refactors
 * the common functionalities to a single class.
 */
public abstract class ASVGShapeTransform implements ITVisitor {
  protected final int tickRate;
  protected String animate;

  /**
   * Creates the SVG tag according to the given tick rate.
   *
   * @param tickRate of the animations in ticks per second.
   */
  protected ASVGShapeTransform(int tickRate) {
    this.tickRate = tickRate;
  }

  @Override
  public void visitColor(ColorTransform t) {
    double duration = getDuration(t.getStart(), t.getEnd());
    double[] oldC = t.getOldData();
    double[] c = t.getData();
    animate = String.format("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
                    "from=\"rgb(%.3f,%.3f,%.3f)\" to=\"rgb(%.3f,%.3f,%.3f)\" begin=\"%.3fs\" " +
                    "dur=\"%.3fs\" fill=\"freeze\"/>", oldC[0], oldC[1], oldC[2], c[0], c[1], c[2],
            (double) t.getStart() / tickRate, duration);
  }

  @Override
  public String toString() {
    return this.animate;
  }

  /**
   * Gets the duration of the time tick interval.
   *
   * @param start is the starting tick.
   * @param end is the ending tick of the interval.
   * @return the duration of the interval in seconds.
   */
  protected double getDuration(int start, int end) {
    return ((double) end / tickRate) - ((double) start / tickRate);
  }

  /**
   * Makes the animation tag for the given transformation.
   *
   * @param t is the transform to be animated in an SVG tag.
   * @param attributes is the attributes for the animation tag.
   */
  protected void makeTags(ITransform t, String[] attributes) {
    String template = "<animate attributeType=\"xml\" begin=\"%.3fs\" " +
            "dur=\"%.3fs\" attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>";
    double start = (double) t.getStart() / tickRate;
    double duration = getDuration(t.getStart(), t.getEnd());
    double[] old = t.getOldData();
    double[] curr = t.getData();
    List<String> list = new ArrayList<>();
    for (int i = 0; i < attributes.length; i++) {
      list.add(String.format(template, start, duration, attributes[i], old[i], curr[i]));
    }
    animate = String.join("\n", list);
  }
}

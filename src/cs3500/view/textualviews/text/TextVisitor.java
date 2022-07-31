package cs3500.view.textualviews.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Makes a text view entry for a transformation. A string that represents the transformation.
 */
public class TextVisitor implements ITVisitor {
  private String entry;

  @Override
  public void visitColor(ColorTransform t) {
    String template = "Color changes from rgb=[%.3f,%.3f,%.3f] to rgb=[%.3f,%.3f,%.3f].";
    entry = makeEntry(template, t.getOldData(), t.getData());
  }

  @Override
  public void visitPosition(PositionTransform t) {
    String template = "Moves from (%.3f,%.3f) to (%.3f,%.3f).";
    entry = makeEntry(template, t.getOldData(), t.getData());
  }

  @Override
  public void visitScale(ScaleTransform t) {
    String template = "Re-scaled from (%.3f,%.3f) to (%.3f,%.3f).";
    entry = makeEntry(template, t.getOldData(), t.getData());
  }

  @Override
  public String toString() {
    return entry;
  }

  /**
   * Makes a string retry for the text view given the values and template.
   *
   * @param template is the format of the string.
   * @param values is the values for the template.
   * @return a string that follows the template and the values.
   */
  private String makeEntry(String template, double[]... values) {
    List<Double> list = new ArrayList<>();
    for (double[] d : values) {
      Arrays.stream(d).forEach(list::add);
    }
    return String.format(template, list.toArray());
  }
}

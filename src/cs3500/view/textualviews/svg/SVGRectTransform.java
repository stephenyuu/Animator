package cs3500.view.textualviews.svg;

import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Represents the animation tags for a rectangle in SVG format.
 */
public class SVGRectTransform extends ASVGShapeTransform {

  /**
   * Creates the SVG animation tag based on the given tick rate.
   *
   * @param tickRate is the tick rate in tick per second.
   */
  public SVGRectTransform(int tickRate) {
    super(tickRate);
  }

  @Override
  public void visitPosition(PositionTransform t) {
    makeTags(t, new String[] {"x", "y"});
  }

  @Override
  public void visitScale(ScaleTransform t) {
    makeTags(t, new String[] {"width", "height"});
  }
}

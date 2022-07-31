package cs3500.view.textualviews.svg;

import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Represents the SCG animation tags for an Ellipse shape.
 */
public class SVGEllipseTransform extends ASVGShapeTransform {

  /**
   * Creates the SVG tag according to the given tick rate.
   *
   * @param tickRate of the animations in ticks per second.
   */
  protected SVGEllipseTransform(int tickRate) {
    super(tickRate);
  }

  @Override
  public void visitPosition(PositionTransform t) {
    makeTags(t, new String[] {"cx", "cy"});
  }

  @Override
  public void visitScale(ScaleTransform t) {
    makeTags(t, new String[] {"rx", "ry"});
  }
}

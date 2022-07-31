package cs3500.view.textualviews.svg;

import java.util.ArrayList;
import java.util.List;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;

/**
 * Creates the animation tags for the Shape. The animation tags follow SVG format.
 */
public class SVGShapeAnimation implements ISVisitor {
  private final List<ITransform> transforms;
  private final int tickRate;
  private String animations;

  /**
   * Creates the animation tags for SVG given the transformations and the shape.
   *
   * @param transforms is the transformations that the shapes goes through.
   * @param tickRate is the tick rate of the animation.
   */
  public SVGShapeAnimation(List<ITransform> transforms, int tickRate) {
    this.transforms = transforms;
    this.tickRate = tickRate;
  }

  @Override
  public void visitRect(Rect rect) {
    ITVisitor visitor = new SVGRectTransform(tickRate);
    makeTags(visitor);
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    ITVisitor visitor = new SVGEllipseTransform(tickRate);
    makeTags(visitor);
  }

  @Override
  public void visitPlus(Plus plus) {
    IShape copy = plus.copy();
    ITVisitor visitor = new SVGPlusTransform(tickRate, copy);
    makeTags(visitor);
  }

  @Override
  public String toString() {
    return animations;
  }

  /**
   * Generates the animation tags for the corresponding shape.
   *
   * @param visitor the transformation visitor for the corresponding shape.
   */
  private void makeTags(ITVisitor visitor) {
    List<String> tags = new ArrayList<>();
    transforms.forEach(t -> {
      t.visitor(visitor);
      tags.add(visitor.toString());
    });
    this.animations = String.join("\n", tags);
  }
}

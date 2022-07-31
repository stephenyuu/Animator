package cs3500.view.textualviews.svg;

import java.awt.geom.Point2D;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Represent the SVG animation tags for a Plus shape.
 */
public class SVGPlusTransform extends ASVGShapeTransform {
  private final IShape shape;

  /**
   * Creates the SVG tag according to the given tick rate.
   *
   * @param tickRate of the animations in ticks per second.
   * @param shape is the plus shape being animated.
   */
  protected SVGPlusTransform(int tickRate, IShape shape) {
    super(tickRate);
    this.shape = shape;
  }

  @Override
  public void visitPosition(PositionTransform t) {
    double[] oldData = t.getOldData();
    double[] newData = t.getData();
    double w = shape.getWidth();
    double h = shape.getHeight();

    // get the before and after.
    Point2D[] oldPoints = getPoints(oldData[0], oldData[1], w, h);
    Point2D[] newPoints = getPoints(newData[0], newData[1], w, h);
    makePlusTag(t, oldPoints, newPoints);
    // mutate the shape.
    shape.move(newData[0], newData[1]);
  }

  @Override
  public void visitScale(ScaleTransform t) {
    double[] oldData = t.getOldData();
    double[] newData = t.getData();
    double posX = shape.getX();
    double posY = shape.getY();

    // get the before and after.
    Point2D[] oldPoints = getPoints(posX, posY, oldData[0], oldData[1]);
    Point2D[] newPoints = getPoints(posX, posY, newData[0], newData[1]);
    makePlusTag(t, oldPoints, newPoints);
    // mutate the shape.
    shape.reScale(newData[0], newData[1]);
  }

  private void makePlusTag(ITransform t, Point2D[] oldPoints, Point2D[] newPoints) {
    String template = "<animate attributeType=\"xml\" begin=\"%.3fs\" " +
            "dur=\"%.3fs\" attributeName=\"points\" from=\"%.3f %.3f, %.3f %.3f, %.3f %.3f, " +
            "%.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, " +
            "%.3f %.3f, %.3f %.3f, %.3f %.3f\" to=\"%.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, " +
            "%.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, " +
            "%.3f %.3f, %.3f %.3f\" fill=\"freeze\"/>";
    double start = (double) t.getStart() / tickRate;
    double duration = getDuration(t.getStart(), t.getEnd());
    animate = String.format(template, start, duration, oldPoints[0].getX(),
            oldPoints[0].getY(), oldPoints[1].getX(), oldPoints[1].getY(), oldPoints[2].getX(),
            oldPoints[2].getY(), oldPoints[3].getX(), oldPoints[3].getY(), oldPoints[4].getX(),
            oldPoints[4].getY(), oldPoints[5].getX(), oldPoints[5].getY(), oldPoints[6].getX(),
            oldPoints[6].getY(), oldPoints[7].getX(), oldPoints[7].getY(), oldPoints[8].getX(),
            oldPoints[8].getY(), oldPoints[9].getX(), oldPoints[9].getY(), oldPoints[10].getX(),
            oldPoints[10].getY(), oldPoints[11].getX(), oldPoints[11].getY(), newPoints[0].getX(),
            newPoints[0].getY(), newPoints[1].getX(), newPoints[1].getY(), newPoints[2].getX(),
            newPoints[2].getY(), newPoints[3].getX(), newPoints[3].getY(), newPoints[4].getX(),
            newPoints[4].getY(), newPoints[5].getX(), newPoints[5].getY(), newPoints[6].getX(),
            newPoints[6].getY(), newPoints[7].getX(), newPoints[7].getY(), newPoints[8].getX(),
            newPoints[8].getY(), newPoints[9].getX(), newPoints[9].getY(), newPoints[10].getX(),
            newPoints[10].getY(), newPoints[11].getX(), newPoints[11].getY());
  }

  /**
   * Get the coordinate points for the polygon.
   *
   * @param x is the xCoordinate of the shape.
   * @param y is the yCoordinate of the shape.
   * @param w is the width of the shape.
   * @param h is the height of the shape.
   * @return the coordinate points for the polygon.
   */
  private Point2D[] getPoints(double x, double y, double w, double h) {
    return new SVGShapeVisitor().getPlusCoordinates(x, y, w, h);
  }
}

package cs3500.view.textualviews.svg;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Objects;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;

/**
 * Visits a shape and formats the shape in XML format for an SVG animation.
 * In order to get the resulting information out of the class, use the toString method.
 * The output of the SVG will be '<svg>' ... '</svg>' where the tags are separated by a newline
 * character. The shape being visited should always have a valid name.
 */
public class SVGShapeVisitor implements ISVisitor {
  private String results;

  @Override
  public void visitRect(Rect rect) {
    checkForNulls(rect);
    this.results = String.format("<rect id=\"%s\" x=\"%.3f\" y=\"%.3f\" width=\"%.3f\" " +
            "height=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n</rect>",
            rect.getName(), rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(),
            rect.getRed(), rect.getBlue(), rect.getGreen());
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    checkForNulls(ellipse);
    this.results = String.format("<ellipse id=\"%s\" cx=\"%.3f\" cy=\"%.3f\" rx=\"%.3f\" " +
                    "ry=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n</ellipse>",
            ellipse.getName(), ellipse.getX(), ellipse.getY(), ellipse.getWidth(),
            ellipse.getHeight(), ellipse.getRed(), ellipse.getGreen(), ellipse.getBlue());
  }

  @Override
  public void visitPlus(Plus plus) {
    checkForNulls(plus);
    Point2D[] points = getPlusCoordinates(plus.getX(), plus.getY(), plus.getWidth(),
            plus.getHeight());
    this.results = String.format("<polygon id=\"%s\" points=\"%.3f %.3f, %.3f %.3f, %.3f %.3f, " +
            "%.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f, %.3f %.3f," +
            "%.3f %.3f, %.3f %.3f\" fill=\"rgb(%d,%d,%d)\" " +
            "visibility=\"hidden\">\n</polygon>", plus.getName(), points[0].getX(),
            points[0].getY(), points[1].getX(), points[1].getY(), points[2].getX(),
            points[2].getY(), points[3].getX(), points[3].getY(), points[4].getX(),
            points[4].getY(), points[5].getX(), points[5].getY(), points[6].getX(),
            points[6].getY(), points[7].getX(), points[7].getY(), points[8].getX(),
            points[8].getY(), points[9].getX(), points[9].getY(), points[10].getX(),
            points[10].getY(), points[11].getX(), points[11].getY(),
            plus.getRed(), plus.getGreen(), plus.getBlue());
  }

  @Override
  public String toString() {
    return this.results;
  }

  /**
   * Check is any of the given values is null.
   *
   * @param o is an array of parameters which will be checked for null.
   * @throws IllegalArgumentException if any value is null in the array.
   */
  private void checkForNulls(Object... o) {
    if (Arrays.stream(o).anyMatch(Objects::isNull)) {
      throw new IllegalArgumentException("Cannot have null values.");
    }
  }

  /**
   * Gets all the coordinate points for a plus shape.
   *
   * @param x is the xCoordinate
   * @param y is the yCoordinate
   * @param w is the width of the bounding box.
   * @param h is the height of the bounding box.
   */
  Point2D[] getPlusCoordinates(double x, double y, double w, double h) {
    double xsw = w / 3;
    double ysw = h / 3;

    // get all the points for the plus polygon.
    Point2D p1 = new Point2D.Double(x + xsw, y);
    Point2D p2 = new Point2D.Double(p1.getX() + xsw, y);
    Point2D p3 = new Point2D.Double(p2.getX(), y + ysw);
    Point2D p4 = new Point2D.Double(x + w, p3.getY());
    Point2D p5 = new Point2D.Double(p4.getX(), p3.getY() + ysw);
    Point2D p6 = new Point2D.Double(p2.getX(), p5.getY());
    Point2D p7 = new Point2D.Double(p2.getX(), y + h);
    Point2D p8 = new Point2D.Double(p1.getX(), p7.getY());
    Point2D p9 = new Point2D.Double(p1.getX(), p5.getY());
    Point2D p10 = new Point2D.Double(x, p5.getY());
    Point2D p11 = new Point2D.Double(x, p3.getY());
    Point2D p12 = new Point2D.Double(p1.getX(), p3.getY());

    return new Point2D[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12};
  }
}

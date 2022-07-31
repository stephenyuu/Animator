package cs3500.view.visualview;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.Arrays;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;

/**
 * Draws a shape using the Java AWT library. It draws the shape according to the shapes
 * attributes.
 */
public class VisualShapeVisitor implements ISVisitor {
  private final Graphics2D g2;
  private boolean fillHa;

  /**
   * Instantiates the visitor with a graphics 2D object. The object is used to draw the shape.
   * @param g2 is the graphics object used to draw a shape in a GUI.
   * @throws IllegalArgumentException if the graphics object is null.
   */
  public VisualShapeVisitor(Graphics2D g2, boolean fillHa) {
    if (g2 == null) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    this.g2 = g2;
    this.fillHa = fillHa;
  }

  @Override
  public void visitRect(Rect rect) {
    g2.setColor(new Color(rect.getRed(), rect.getGreen(), rect.getBlue()));

    if (fillHa) {
      g2.fillRect((int) rect.getX(), (int) rect.getY(),
              (int) rect.getWidth(), (int) rect.getHeight());
    } else {
      g2.drawRect((int) rect.getX(), (int) rect.getY(),
              (int) rect.getWidth(), (int) rect.getHeight());
    }
  }

  @Override
  public void visitEllipse(Ellipse ellipse) {
    g2.setColor(new Color(ellipse.getRed(), ellipse.getGreen(), ellipse.getBlue()));

    if (fillHa) {
      g2.fillOval((int)ellipse.getX(), (int)ellipse.getY(),
              (int)ellipse.getWidth(), (int)ellipse.getHeight());
    } else {
      g2.drawOval((int)ellipse.getX(), (int)ellipse.getY(),
              (int)ellipse.getWidth(), (int)ellipse.getHeight());
    }
  }

  @Override
  public void visitPlus(Plus plus) {
    Polygon plusPolygon = getPlusPolygon(plus.getX(), plus.getY(), plus.getWidth(),
            plus.getHeight());

    g2.setColor(new Color(plus.getRed(), plus.getBlue(), plus.getBlue()));

    if (fillHa) {
      g2.fillPolygon(plusPolygon);
    } else {
      g2.drawPolygon(plusPolygon);
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
  private Polygon getPlusPolygon(double x, double y, double w, double h) {
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

    Point2D[] pointArray = new Point2D[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12};

    Polygon plusPolygon = new Polygon();

    Arrays.stream(pointArray).forEach(p -> plusPolygon.addPoint((int)p.getX(), (int)p.getY()));

    return plusPolygon;
  }
}

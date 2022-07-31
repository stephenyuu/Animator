package cs3500.animations;

import java.awt.geom.Point2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;

/**
 * Creates an SVG sample animation demonstrating the Plus shape being animated.
 */
public class PlusSampleAnimation {

  /**
   * Creates a sample animation.
   * @param args are the arguments for the animation.
   */
  public static void main(String[] args) {
    Appendable ap = new StringBuilder();
    new PlusSampleAnimation().makeAnimation(ap);
    export("sample.txt", ap);
  }

  /**
   * Writes the SVG sample to a file.
   *
   * @param location is the output location for the file (file name).
   * @param ap is the appendable that contains the animation.
   */
  private static void export(String location, Appendable ap) {
    try {
      FileWriter myWriter = new FileWriter(location);
      myWriter.write(ap.toString());
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the file.");
    }
  }

  /**
   * Makes an animation and outputs the animation into an appendable object.
   *
   * @param ap is the appendable object.
   * @return the animation in an appendable.
   */
  private Appendable makeAnimation(Appendable ap) {
    List<String> animations = new ArrayList<>();
    IShape plus = new Plus(150, 150, 100, 100, 255, 0, 0);
    IShape rect = new Rect(100, 100, 300, 150, 0, 255, 0);
    IShape ellipse = new Ellipse(150, 150, 200, 200, 0, 0, 255);
    animations.add("canvas 400 400");
    plus.setName("p");
    rect.setName("r");
    ellipse.setName("c");

    // make the shapes
    String p = makePlus(plus, 0, 50);
    String r = makeRect(rect, 0, 50);
    String e = makeEllipse(ellipse, 0, 50);

    animations.add(p);
    animations.add(r);
    animations.add(e);

    IShape plusCopy = plus.copy();
    IShape rectCopy = rect.copy();
    IShape ellipseCopy = ellipse.copy();
    int start = 1;
    int end = 5;

    for (int i = 0; i < 4; i += 1) {

      // move
      animations.add(move("p", plusCopy.getX(), plusCopy.getY(),
              rectCopy.getX(), rectCopy.getY(), start, end));
      animations.add(move("r", rectCopy.getX(), rectCopy.getY(),
              ellipseCopy.getX(), ellipseCopy.getY(), start, end));
      animations.add(move("c", ellipseCopy.getX(), ellipseCopy.getY(),
              plusCopy.getX(), plusCopy.getY(), start, end));

      // re-color
      animations.add(setColor("p", plusCopy.getRed(), plusCopy.getGreen(),
                      plusCopy.getBlue(), rect.getRed(), rectCopy.getGreen(),
              rectCopy.getBlue(), start, end));
      animations.add(setColor("r", rectCopy.getRed(), rectCopy.getGreen(),
              rectCopy.getBlue(), ellipseCopy.getRed(), ellipseCopy.getGreen(),
              ellipseCopy.getBlue(), start, end));
      animations.add(setColor("c",  ellipseCopy.getRed(), ellipseCopy.getGreen(),
              ellipseCopy.getBlue(), plusCopy.getRed(), plusCopy.getGreen(),
              plusCopy.getBlue(), start, end));

      // save the state for mutations.
      Point2D rOld = new Point2D.Double(rectCopy.getX(), rectCopy.getY());
      Point2D eOld = new Point2D.Double(ellipseCopy.getX(), ellipseCopy.getY());
      Point2D pOld = new Point2D.Double(plusCopy.getX(), plusCopy.getY());

      // mutate position.
      ellipseCopy.move(pOld.getX(), pOld.getY());
      plusCopy.move(rOld.getX(), rOld.getY());
      rectCopy.move(eOld.getX(), eOld.getY());

      // mutate color.
      ellipseCopy.setColor(plusCopy.getRed(), plusCopy.getGreen(), plusCopy.getBlue());
      plusCopy.setColor(rectCopy.getRed(), rectCopy.getGreen(), rectCopy.getBlue());
      rectCopy.setColor(ellipseCopy.getRed(), ellipseCopy.getGreen(), ellipse.getBlue());

      // increase the interval.
      start += 10;
      end += 10;
    }

    animations.add(scale("p", plusCopy.getWidth(), plusCopy.getHeight(),
            30, 30, 0, 5));
    animations.add(scale("r", plusCopy.getWidth(), plusCopy.getHeight(),
            40, 40, 0, 5));
    animations.add(scale("c", plusCopy.getWidth(), plusCopy.getHeight(),
            10, 10, 0, 5));

    plus.reScale(20, 20);
    rect.reScale(20, 20);
    ellipse.reScale(20, 20);

    animations.add(scale("p", plusCopy.getWidth(), plusCopy.getHeight(),
            9, 9, 10, 30));
    animations.add(scale("r", plusCopy.getWidth(), plusCopy.getHeight(),
            15, 20, 10, 20));
    animations.add(scale("c", plusCopy.getWidth(), plusCopy.getHeight(),
            30, 30, 10, 20));

    try {
      ap.append(String.join("\n", animations));
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return ap;
  }

  /**
   * Draws a Rectangle shape in the reader format.
   *
   * @param s is the shape that will be drawn.
   * @param end is the last tick in which a transformation ends.
   * @param start is the tick at which the shape appears.
   * @return the shape in File Reader format.
   */
  private String makeRect(IShape s, int start, int end) {
    String template = "rectangle name %s min-x %.3f min-y %.3f width %.3f height " +
            "%.3f color %f %f %f from %d to %d";
    return String.format(template, s.getName(), s.getX(), s.getY(), s.getWidth(),
            s.getHeight(), s.getRed() / (float)255, s.getGreen() / (float)255,
            s.getBlue() / (float)255, start, end);
  }

  /**
   * Draws a Plus shape in the reader format.
   *
   * @param s is the shape that will be drawn.
   * @param end is the last tick in which a transformation ends.
   * @param start is the tick at which the shape appears.
   * @return the shape in File Reader format.
   */
  private String makePlus(IShape s, int start, int end) {
    String template = "plus name %s min-x %.3f min-y %.3f width %.3f height " +
            "%.3f color %f %f %f from %d to %d";
    return String.format(template, s.getName(), s.getX(), s.getY(), s.getWidth(),
            s.getHeight(), s.getRed() / (float)255, s.getGreen() / (float)255,
            s.getBlue() / (float)255, start, end);
  }

  /**
   * Draws an Ellipse shape in the reader format.
   *
   * @param s is the shape that will be drawn.
   * @param end is the last tick in which a transformation ends.
   * @param start is the tick at which the shape appears.
   * @return the shape in File Reader format.
   */
  private String makeEllipse(IShape s, int start, int end) {
    String template = "oval name %s center-x %.3f center-y %.3f x-radius %.3f y-radius " +
            "%.3f color %f %f %f from %d to %d";
    return String.format(template, s.getName(), s.getX(), s.getY(), s.getWidth(),
            s.getHeight(), s.getRed() / (float)255, s.getGreen() / (float)255,
            s.getBlue() / (float)255, start, end);
  }

  /**
   * Moves a shape in File Reader format.
   *
   * @param name is the id/name of the shape.
   * @param xFrom the origin x.
   * @param yFrom the origin y.
   * @param xTo the destination x.
   * @param yTo the destination y.
   * @param start the start tick.
   * @param end the end tick.
   * @return the file reader format move string.
   */
  private String move(String name, double xFrom, double yFrom, double xTo, double yTo,
                      int start, int end) {
    String template = "move name %s moveto %.3f %.3f %.3f %.3f from %d to %d";
    return String.format(template, name, xFrom, yFrom, xTo, yTo, start, end);
  }

  /**
   * Scales the shape in File Reader format.
   *
   * @param name is the id/name of the shape.
   * @param xFrom the origin x.
   * @param yFrom the origin y.
   * @param xTo the destination x.
   * @param yTo the destination y.
   * @param start the start tick.
   * @param end the end tick.
   * @return the file reader format for scaling a shape.
   */
  private String scale(String name, double xFrom, double yFrom, double xTo, double yTo,
                       int start, int end) {
    String template = "scale name %s scaleto %.3f %.3f %.3f %.3f from %d to %d";
    return String.format(template, name, xFrom, yFrom, xTo, yTo, start, end);
  }

  /**
   * Change color in file reader format.
   *
   * @param name is the name of the shape.
   * @param rFrom red origin.
   * @param gFrom green origin.
   * @param bFrom blue origin.
   * @param rTo red destination.
   * @param gTo green destination.
   * @param bTo blue destination.
   * @param start is the start of the transformation.
   * @param end is the end of the transformation.
   * @return the transformation in file reader format.
   */
  private String setColor(String name, int rFrom, int gFrom, int bFrom, int rTo, int gTo, int bTo,
                          int start, int end) {
    String template = "change-color name %s colorto %f %f %f %f %f %f from %d to %d";
    return String.format(template, name, rFrom / (double) 255, gFrom / (double)255,
            bFrom / (double)255, rTo / (double)255, gTo / (double)255, bTo / (double)255,
            start, end);
  }
}
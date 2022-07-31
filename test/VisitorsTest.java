import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.ISVisitor;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.view.textualviews.svg.SVGRectTransform;
import cs3500.view.textualviews.svg.SVGShapeAnimation;
import cs3500.view.textualviews.svg.SVGShapeVisitor;
import cs3500.view.textualviews.text.TextVisitor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Class to test the functionality of the different visitors for the views.
 */
public class VisitorsTest {

  ////// SVGShapeAnimationVisitor //////

  @Test
  public void testShapeAnim() {
    ITransform t1 = new PositionTransform(1, 4, 90, 80, 10, 10);
    ITransform t2 = new PositionTransform(4, 6, 10, 10, 100, 80);
    List<ITransform> trans = Arrays.asList(t1, t2);
    ISVisitor visitor = new SVGShapeAnimation(trans, 10);
    IShape r = new Rect(10, 10, 10, 20, 1, 56, 80);
    IShape e = new Ellipse(10, 10, 10, 10, 90, 90, 80);
    IShape p = new Plus(10, 10, 10, 10, 100, 100, 100);
    assertNull(visitor.toString());
    r.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"x\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"y\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.400s\" dur=\"0.200s\" " +
            "attributeName=\"x\" from=\"10.000\" to=\"100.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.400s\" dur=\"0.200s\" " +
            "attributeName=\"y\" from=\"10.000\" to=\"80.000\" fill=\"freeze\"/>",
            visitor.toString());
    e.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"cx\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"cy\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.400s\" dur=\"0.200s\" " +
            "attributeName=\"cx\" from=\"10.000\" to=\"100.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.400s\" dur=\"0.200s\" " +
            "attributeName=\"cy\" from=\"10.000\" to=\"80.000\" fill=\"freeze\"/>",
            visitor.toString());
    p.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"points\" from=\"93.333 80.000, 96.667 80.000, 96.667 83.333, " +
            "100.000 83.333, 100.000 86.667, 96.667 86.667, 96.667 90.000, 93.333 90.000, " +
            "93.333 86.667, 90.000 86.667, 90.000 83.333, 93.333 83.333\" to=\"13.333 10.000, " +
            "16.667 10.000, 16.667 13.333, 20.000 13.333, 20.000 16.667, 16.667 16.667, " +
            "16.667 20.000, 13.333 20.000, 13.333 16.667, 10.000 16.667, 10.000 13.333, " +
            "13.333 13.333\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.400s\" dur=\"0.200s\" " +
            "attributeName=\"points\" from=\"13.333 10.000, 16.667 10.000, " +
            "16.667 13.333, 20.000 13.333, 20.000 16.667, 16.667 16.667, " +
            "16.667 20.000, 13.333 20.000, 13.333 16.667, 10.000 16.667, " +
            "10.000 13.333, 13.333 13.333\" to=\"103.333 80.000, 106.667 80.000, " +
            "106.667 83.333, 110.000 83.333, 110.000 86.667, 106.667 86.667, 106.667 " +
            "90.000, 103.333 90.000, 103.333 86.667, 100.000 86.667, 100.000 83.333, " +
            "103.333 83.333\" fill=\"freeze\"/>", visitor.toString());
  }

  ////// SVGRectTransform //////

  @Test
  public void testRectTransformPos() {
    ITransform t1 = new PositionTransform(1, 4, 90, 80, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"x\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
            "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
            "attributeName=\"y\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testRectTransformColor() {
    ITransform t1 = new ColorTransform(1, 4, 90, 80,
            10, 10, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
                    "from=\"rgb(90.000,80.000,10.000)\" to=\"rgb(10.000,10.000,10.000)\" " +
                    "begin=\"0.100s\" dur=\"0.300s\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testRectTransform() {
    ITransform t1 = new ScaleTransform(1, 4, 90, 80,
            10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"width\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"height\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  ////// SVGEllipseTransform //////

  @Test
  public void testEllipseTransformScale() {
    ITransform t1 = new ScaleTransform(1, 4, 90, 80, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"width\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"height\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testEllipseTransformPos() {
    ITransform t1 = new PositionTransform(1, 4, 90, 80, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"x\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"y\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testEllipseTransformColor() {
    ITransform t1 = new ColorTransform(1, 4, 90, 80, 10,
            10, 1, 34);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
                    "from=\"rgb(90.000,80.000,10.000)\" to=\"rgb(10.000,1.000,34.000)\" " +
                    "begin=\"0.100s\" dur=\"0.300s\" fill=\"freeze\"/>",
            visitor.toString());
  }

  ////// SVGPlusTransform //////

  @Test
  public void testPlusTransformColor() {
    ITransform t1 = new ColorTransform(1, 4, 90, 80, 10, 10,
            10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeName=\"fill\" attributeType=\"CSS\" " +
                    "from=\"rgb(90.000,80.000,10.000)\" to=\"rgb(10.000,10.000,10.000)\" " +
                    "begin=\"0.100s\" dur=\"0.300s\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testPlusTransformScale() {
    ITransform t1 = new ScaleTransform(1, 4, 90, 80, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"width\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"height\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  @Test
  public void testPlusTransformPos() {
    ITransform t1 = new PositionTransform(1, 4, 90, 80, 10, 10);
    ITVisitor visitor = new SVGRectTransform(10);
    assertNull(visitor.toString());
    t1.visitor(visitor);
    assertEquals("<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"x\" from=\"90.000\" to=\"10.000\" fill=\"freeze\"/>\n" +
                    "<animate attributeType=\"xml\" begin=\"0.100s\" dur=\"0.300s\" " +
                    "attributeName=\"y\" from=\"80.000\" to=\"10.000\" fill=\"freeze\"/>",
            visitor.toString());
  }

  ////// SVGShapeVisitor //////

  @Test
  public void visitSVGShapeVisitor() {
    IShape rect = new Rect(10, 10, 1, 20, 10, 10, 10);
    IShape ellipse = new Ellipse(10, 10, 1, 30, 12, 12, 30);
    ISVisitor visitor = new SVGShapeVisitor();
    assertNull(visitor.toString());
    rect.visitor(visitor);
    assertEquals("<rect id=\"null\" x=\"1.000\" y=\"20.000\" width=\"10.000\" " +
            "height=\"10.000\" fill=\"rgb(10,10,10)\" visibility=\"hidden\">\n" +
            "</rect>", visitor.toString());
    ellipse.visitor(visitor);
    assertEquals("<ellipse id=\"null\" cx=\"1.000\" cy=\"30.000\" rx=\"10.000\" " +
            "ry=\"10.000\" fill=\"rgb(12,12,30)\" visibility=\"hidden\">\n" +
            "</ellipse>", visitor.toString());
  }

  @Test
  public void visitShapePlus() {
    IShape plusShape = new Plus(15, 15, 10, 10, 255, 255, 0);
    ISVisitor plus = new SVGShapeVisitor();
    assertNull(plus.toString());
    plusShape.visitor(plus);
    assertEquals("<polygon id=\"null\" points=\"15.000 10.000, 20.000 10.000, 20.000 " +
            "15.000, 25.000 15.000, 25.000 20.000, 20.000 20.000, 20.000 25.000, 15.000 25.000, " +
            "15.000 20.000, 10.000 20.000,10.000 15.000, 15.000 15.000\" " +
            "fill=\"rgb(255,255,0)\" visibility=\"hidden\">\n" +
            "</polygon>", plus.toString());
  }

  ////// TextViewVisitor //////

  @Test
  public void textViewVisitor() {
    ITransform scale = new ScaleTransform(10, 12, 10, 10,
            15, 20);
    ITransform pos = new PositionTransform(10, 30, 40, 10, 30, 60);
    ITransform color = new ColorTransform(10, 30, 1, 3, 5,
            12, 13, 15);
    ITVisitor text = new TextVisitor();

    assertNull(text.toString());
    scale.visitor(text);
    assertEquals("Re-scaled from (10.000,10.000) to (15.000,20.000).", text.toString());
    pos.visitor(text);
    assertEquals("Moves from (40.000,10.000) to (30.000,60.000).", text.toString());
    color.visitor(text);
    assertEquals("Color changes from rgb=[1.000,3.000,5.000] to " +
            "rgb=[12.000,13.000,15.000].", text.toString());
  }
}

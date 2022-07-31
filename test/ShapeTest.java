import org.junit.Test;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Rect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class represents the unit tests for the functionality of the SShape class. It tests
 * that the mutating methods function appropriately and that the getters return the correct value.
 */
public class ShapeTest {

  // to test the constructor.
  IShape constructor;
  // for functionality test
  IShape rect;
  IShape ellipse;

  // initializes a rectangle.
  private void initRect() {
    rect = new Rect(10, 5, 10, 0, 255, 255, 0);
  }

  // initializes an ellipse.
  private void initEllipse() {
    ellipse = new Ellipse(10, 5, 10, 0, 255, 255, 0);
  }

  //// TESTS for the Constructor ////

  @Test(expected = IllegalArgumentException.class)
  public void zeroWidth() {
    constructor = new Rect(0, 5, 10, 0, 255, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeWidth() {
    constructor = new Rect(-1, 5, 10, 0, 255, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void zeroHeight() {
    constructor = new Rect(1, 0, 10, 0, 255, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void negativeHeight() {
    constructor = new Ellipse(1, -1, 10, 0, 255, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void greenNegative() {
    constructor = new Ellipse(1, 5, 10, 0, 255, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void green256() {
    constructor = new Ellipse(1, 5, 10, 0, 255, 256, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void redNegative() {
    constructor = new Ellipse(1, 5, 10, 0, -1, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void red256() {
    constructor = new Ellipse(1, 5, 10, 0, 256, 255, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void blueNegative() {
    constructor = new Ellipse(1, 5, 10, 0, 255, 255, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void blue256() {
    constructor = new Ellipse(1, 5, 10, 0, 255, 255, 256);
  }

  //// TESTS for Move ////

  @Test
  public void testMove() {
    initRect();
    assertEquals(10, rect.getX(), 0.001);
    assertEquals(0, rect.getY(), 0.001);
    rect.move(30, 10);
    assertEquals(30, rect.getX(), 0.001);
    assertEquals(10, rect.getY(), 0.001);
  }

  //// TESTS for setColor ////

  @Test
  public void testSetColor() {
    initEllipse();
    assertEquals(255, ellipse.getRed(), 0.001);
    assertEquals(255, ellipse.getGreen(), 0.001);
    assertEquals(0, ellipse.getBlue(), 0.001);
    ellipse.setColor(20, 50, 1);
    assertEquals(20, ellipse.getRed(), 0.001);
    assertEquals(50, ellipse.getGreen(), 0.001);
    assertEquals(1, ellipse.getBlue(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRedLow() {
    initEllipse();
    ellipse.setColor(-1, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRedHigh() {
    initEllipse();
    ellipse.setColor(256, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreenLow() {
    initEllipse();
    ellipse.setColor(10, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreenHigh() {
    initEllipse();
    ellipse.setColor(10, 256, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlueLow() {
    initEllipse();
    ellipse.setColor(10, 10, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBlueHigh() {
    initEllipse();
    ellipse.setColor(10, 10, 256);
  }

  //// TEST for Scale ////

  @Test
  public void testScaleWidth() {
    initRect();
    assertEquals(10, rect.getWidth(), 0.0001);
    assertEquals(5, rect.getHeight(), 0.0001);
    rect.reScale(20, 5);
    assertEquals(20, rect.getWidth(), 0.0001);
    assertEquals(5, rect.getHeight(), 0.0001);
  }

  @Test
  public void testScaleHeight() {
    initRect();
    assertEquals(10, rect.getWidth(), 0.0001);
    assertEquals(5, rect.getHeight(), 0.0001);
    rect.reScale(10, 10);
    assertEquals(10, rect.getWidth(), 0.0001);
    assertEquals(10, rect.getHeight(), 0.0001);
  }

  @Test
  public void testScale() {
    initRect();
    assertEquals(10, rect.getWidth(), 0.0001);
    assertEquals(5, rect.getHeight(), 0.0001);
    rect.reScale(20, 10);
    assertEquals(20, rect.getWidth(), 0.0001);
    assertEquals(10, rect.getHeight(), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroScaleWidth() {
    initRect();
    rect.reScale(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroScaleHeight() {
    initRect();
    rect.reScale(2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeScaleWidth() {
    initRect();
    rect.reScale(-1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeScaleHeight() {
    initRect();
    rect.reScale(2, -1);
  }

  //// TEST GETTERS ////

  @Test
  public void getRed() {
    initRect();
    assertEquals(255, rect.getRed(), 0.001);
  }

  @Test
  public void getBlue() {
    initRect();
    assertEquals(0, rect.getBlue(), 0.001);
  }

  @Test
  public void getGreen() {
    initEllipse();
    assertEquals(255, ellipse.getGreen(), 0.001);
  }

  @Test
  public void getX() {
    initEllipse();
    assertEquals(10, ellipse.getX(), 0.0001);
  }

  @Test
  public void getY() {
    initRect();
    assertEquals(0, rect.getY(), 0.0001);
  }

  @Test
  public void getWidth() {
    initRect();
    assertEquals(10, rect.getWidth(), 0.0001);
  }

  @Test
  public void getHeight() {
    initRect();
    assertEquals(5, rect.getHeight(), 0.0001);
  }

  // TESTS that the copy constructor works.

  @Test
  public void testCopy() {
    initRect();
    IShape rect_copy = rect.copy();
    assertEquals(rect_copy.getX(), rect.getX(), 0.0001);
    assertEquals(rect_copy.getY(), rect.getY(), 0.0001);
    assertEquals(rect_copy.getRed(), rect.getRed(), 0.001);
    assertEquals(rect_copy.getGreen(), rect.getGreen(), 0.001);
    assertEquals(rect_copy.getBlue(), rect.getBlue(), 0.001);
    assertEquals(rect_copy.getWidth(), rect.getWidth(), 0.0001);
    assertEquals(rect_copy.getHeight(), rect.getHeight(), 0.0001);
    assertEquals(rect_copy.getName(), rect.getName());
  }

  @Test
  public void getName() {
    initRect();
    assertNull(rect.getName());
    rect.setName("cholo");
    assertEquals("cholo", rect.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void visitNullRect() {
    initRect();
    rect.visitor(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void visitNullEllipse() {
    initEllipse();
    ellipse.visitor(null);
  }
}
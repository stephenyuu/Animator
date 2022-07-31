import org.junit.Test;

import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.model.transformation.TransformType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

/**
 * To test that the transform's getter methods work appropriately. Makes sure that the
 * functionality of the transforms is correct.
 */
public class TransformTest {

  // dummy constructor.
  ITransform constructor;

  @Test
  public void testGetTypePosition() {
    ITransform t = new PositionTransform(0, 1, 1, 1, 10, 10);
    assertEquals(TransformType.POSITION, t.getType());
  }

  @Test
  public void testGetTypeColor() {
    ITransform t = new ColorTransform(0, 1, 1, 1, 1, 10,
            10, 10);
    assertEquals(TransformType.COLOR, t.getType());
  }

  @Test
  public void testGetTypeScale() {
    ITransform t = new ScaleTransform(0, 1, 1, 1, 10, 10);
    assertEquals(TransformType.SCALE, t.getType());
  }

  @Test
  public void testGetDataPosition() {
    ITransform t = new PositionTransform(0, 1, 1, 1, 10, 10);
    double[] result = new double[] {10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetDataColor() {
    ITransform t = new ColorTransform(0, 1, 1, 1, 1,10,
            10, 10);
    double[] result = new double[] {10, 10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetDataScale() {
    ITransform t = new ScaleTransform(0, 1, 1, 1,10, 10);
    double[] result = new double[] {10, 10};
    assertArrayEquals(result, t.getData(), 0.0001);
  }

  @Test
  public void testGetStartPosition() {
    ITransform t = new PositionTransform(0, 1, 1, 1, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetStartColor() {
    ITransform t = new ColorTransform(0, 1, 1, 1, 1,
            10, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetStartScale() {
    ITransform t = new ScaleTransform(0, 1, 1, 1, 10, 10);
    assertEquals(0, t.getStart());
  }

  @Test
  public void testGetEndPosition() {
    ITransform t = new PositionTransform(0, 1, 1, 1, 10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test
  public void testGetEndColor() {
    ITransform t = new ColorTransform(0, 1, 1, 1, 1,10,
            10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test
  public void testGetEndScale() {
    ITransform t = new ScaleTransform(0, 1, 1, 1, 10, 10);
    assertEquals(1, t.getEnd());
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeColor() {
    constructor = new ColorTransform(-1, 1, 0, 0, 0,1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimePosition() {
    constructor = new PositionTransform(-1, 1, 0,0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeScale() {
    constructor = new ScaleTransform(-1, 1, 1, 1, 2,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndScale() {
    constructor = new ScaleTransform(1, -1, 1, 1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndColor() {
    constructor = new ColorTransform(1, -1, 0, 0, 0, 1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeEndPosition() {
    constructor = new PositionTransform(1, -1, 0,0, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthScale() {
    constructor = new ScaleTransform(1, 1, 1, 1, -1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHeightScale() {
    constructor = new ScaleTransform(1, 1, 1, 1, 1,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorR() {
    constructor = new ColorTransform(1, 1, 0, 0, 0, -1,1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorG() {
    constructor = new ColorTransform(1, 1, 0, 0, 0, 1,-1 ,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColorB() {
    constructor = new ColorTransform(1, 1, 0, 0, 0, 1,1 ,-1);
  }

  @Test
  public void makeCopyColor() {
    ITransform t1 = new ColorTransform(0, 1, 0, 0, 0, 10,
            10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }

  @Test
  public void makeCopyScale() {
    ITransform t1 = new ScaleTransform(0, 1, 1, 1, 10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }

  @Test
  public void makeCopyMove() {
    ITransform t1 = new PositionTransform(0, 1, 1, 1, 10, 10);
    ITransform t2 = t1.copy();
    assertEquals(t2.getEnd(), t1.getEnd());
    assertEquals(t2.getStart(), t1.getStart());
    assertArrayEquals(t2.getData(), t1.getData(), 0.0001);
    assertEquals(t2.getType(), t1.getType());
  }

  @Test
  public void getOldDataColor() {
    ITransform c = new ColorTransform(1, 2, 1, 1, 1, 3, 4, 5);
    assertArrayEquals(new double[] {1, 1, 1}, c.getOldData(), 0.0001);
  }

  @Test
  public void getOldDataPos() {
    ITransform c = new PositionTransform(1, 2, 1, 1, 1, 3);
    assertArrayEquals(new double[] {1, 1}, c.getOldData(), 0.0001);
  }

  @Test
  public void getOldDataScale() {
    ITransform c = new ScaleTransform(1, 2, 1, 1, 1, 3);
    assertArrayEquals(new double[] {1, 1}, c.getOldData(), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisitorPos() {
    ITransform p = new PositionTransform(1, 3, 1, 1, 10, 20);
    p.visitor(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisitorColor() {
    ITransform p = new ColorTransform(1, 3, 1, 1, 10, 20,
            10, 10);
    p.visitor(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisitorScale() {
    ITransform p = new ScaleTransform(1, 3, 1, 1, 10, 20);
    p.visitor(null);
  }
}
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Rect;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.IShapeMutationVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;
import cs3500.model.transformation.TransformType;
import cs3500.model.TweeningVisitor;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Represents all the test to test the functionality of the animator model and the mutating
 * methods. It also tests constructor requirements and getter methods.
 */
public class AnimatorTest {

  IAnimator animator;

  private void animator() {
    animator = new Animator();
  }

  @Test
  public void testAdd() {
    animator();
    IShape rect = new Rect(10, 10, 1, 1, 2, 2, 2);
    assertNull(animator.getShape("rect"));
    animator.add("rect", rect, 0, 1);
    assertTrue(Objects.nonNull(animator.getShape("rect")));
  }

  @Test
  public void testRemove() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    IShape rect2 = new Rect(10, 10, 1, 1, 10, 10, 10);
    animator.add("rect1", rect1, 0, 1);
    animator.add("rect2", rect2, 0, 1);
    assertTrue(Objects.nonNull(animator.getShape("rect1")));
    assertTrue(Objects.nonNull(animator.getShape("rect2")));
    animator.remove("rect2");
    assertTrue(Objects.nonNull(animator.getShape("rect1")));
    assertNull(animator.getShape("rect2"));
  }

  @Test
  public void testMove() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.move("rect1", 0, 1, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.POSITION, transforms.getType());
    assertArrayEquals(new double[] {10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void reScaleTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.reScale("rect1", 0, 1, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.SCALE, transforms.getType());
    assertArrayEquals(new double[] {10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void setColorTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    Map<String, List<ITransform>> state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
    animator.setColor("rect1", 0, 1, 10, 10, 10);
    state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.COLOR, transforms.getType());
    assertArrayEquals(new double[] {10, 10, 10}, transforms.getData(), 0.00001);
  }

  @Test
  public void deleteTest() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 1);
    animator.setColor("rect1", 0, 1, 10, 10, 10);
    Map<String, List<ITransform>> state = animator.getState();
    ITransform transforms = state.get("rect1").get(0);
    assertEquals(1, transforms.getEnd());
    assertEquals(0, transforms.getStart());
    assertEquals(TransformType.COLOR, transforms.getType());
    assertArrayEquals(new double[] {10, 10, 10}, transforms.getData(), 0.00001);
    animator.deleteTransform("rect1", TransformType.COLOR);
    state = animator.getState();
    assertTrue(state.get("rect1").isEmpty());
  }

  @Test
  public void setBoundsTest() {
    animator();
    assertEquals(0, animator.getCanvasHeight());
    assertEquals(0, animator.getCanvasWidth());
    animator.setBounds(100, 100);
    assertEquals(100, animator.getCanvasHeight());
    assertEquals(100, animator.getCanvasWidth());
  }

  @Test
  public void getCanvasHeight() {
    animator();
    assertEquals(0, animator.getCanvasHeight());
  }

  @Test
  public void getCanvasWidth() {
    animator();
    assertEquals(0, animator.getCanvasWidth());
  }

  @Test
  public void setTickRate() {
    animator();
    assertEquals(1, animator.getTickRate());
    animator.setTickRate(100);
    assertEquals(100, animator.getTickRate());
  }

  @Test
  public void getTickRate() {
    animator();
    assertEquals(1, animator.getTickRate());
  }

  @Test
  public void getState() {
    animator();
    assertNull(animator.getState());
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.move("rect1", 3, 10, 10, 10);
    animator.setColor("rect1", 2, 5, 10, 10, 10);
    animator.reScale("rect1", 7, 10, 10, 19);
    List<ITransform> list = animator.getState().get("rect1");
    assertEquals(TransformType.COLOR, list.get(0).getType());
    assertEquals(TransformType.POSITION, list.get(1).getType());
    assertEquals(TransformType.SCALE, list.get(2).getType());
    assertEquals(3, list.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.move("rect1", 3, 10, 10, 10);
    animator.move("rect1", 3, 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void scalingIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.reScale("rect1", 3, 10, 10, 10);
    animator.reScale("rect1", 3, 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void colorIllegalSequence() {
    animator();
    IShape rect1 = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("rect1", rect1, 0, 100);
    animator.setColor("rect1", 3, 10, 10, 10, 10);
    animator.setColor("rect1", 3, 10, 10, 10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullShapeAdd() {
    animator();
    animator.add("Hola", null, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTimeStartAdd() {
    animator();
    animator.add("Hola",
            new Rect(10, 10, 1, 1, 2, 2, 2), -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidTimeEndAdd() {
    animator();
    animator.add("Hola",
            new Rect(10, 10, 1, 1, 2, 2, 2), 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidNameAdd() {
    animator();
    animator.add("Hola",
            new Rect(10, 10, 1, 1, 2, 2, 2), 0, 1);
    animator.add("Hola",
            new Rect(10, 10, 1, 1, 2, 2, 2), 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shapeRepeatAdd() {
    animator();
    IShape rect = new Rect(10, 10, 1, 1, 2, 2, 2);
    animator.add("Hola", rect, 0, 1);
    animator.add("Hola", rect, 0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullNameRemove() {
    animator();
    animator.remove(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nameNotInAnimatorRemove() {
    animator();
    animator.remove("Hola");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nameNotInAnimatorMove() {
    animator();
    animator.move("Hola", 1, 2, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeMove() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 10);
    animator.move("Hola", 2, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntervalMove() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 9);
    animator.move("Hola", 1, 10, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nameNotInAnimatorScale() {
    animator();
    animator.reScale("Hola", 1, 2, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeScale() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 10);
    animator.reScale("Hola", 2, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntervalScale() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 9);
    animator.reScale("Hola", 1, 10, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nameNotInAnimatorColor() {
    animator();
    animator.setColor("Hola", 1, 2, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeColor() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 10);
    animator.setColor("Hola", 2, 1, 1, 1,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidIntervalColor() {
    animator();
    IShape rect = new Rect(1, 1, 1, 1, 1, 1, 1);
    animator.add("Hola", rect, 1, 9);
    animator.setColor("Hola", 1, 10, 1, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidNameDelete() {
    animator();
    animator.deleteTransform("name", TransformType.COLOR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidNullDelete() {
    animator();
    animator.deleteTransform("name", TransformType.COLOR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidth() {
    animator();
    animator.setBounds(-10, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidHeight() {
    animator();
    animator.setBounds(10, -10);
  }

  @Test
  public void getStartEndTest() {
    animator();
    animator.add("rect",
            new Rect(1, 1, 1, 1, 10, 20, 10), 1, 10);
    assertEquals(1 ,animator.getStart("rect"));
    assertEquals(10 ,animator.getEnd("rect"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getStartInvalidName() {
    animator();
    animator.getStart("Hola");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getStartNullName() {
    animator();
    animator.getStart(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getEndNullName() {
    animator();
    animator.getEnd(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getStarInvalidName() {
    animator();
    animator.getStart("hola");
  }

  @Test
  public void getShapeTest() {
    animator();
    IShape circle = new Ellipse(10, 10, 1, 1, 1, 2, 3);
    animator.add("circle", circle, 1, 10);
    IShape c = animator.getShape("circle");
    assertEquals(circle.getHeight(), c.getHeight(), 0.0001);
    assertEquals(circle.getWidth(), c.getWidth(), 0.0001);
    assertEquals(circle.getName(), c.getName());
    assertEquals(circle.getBlue(), c.getBlue());
    assertEquals(circle.getGreen(), c.getGreen());
    assertEquals(circle.getRed(), c.getRed());
    assertEquals(circle.getX(), c.getX(), 0.0001);
    assertEquals(circle.getY(), c.getY(), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullNameGetShape() {
    animator();
    animator.getShape(null);
  }

  @Test
  public void invalidNameGetShape() {
    animator();
    assertNull(animator.getShape("hsu"));
  }

  @Test
  public void getShapes() {
    animator();
    IShape circle = new Ellipse(10, 10, 1, 1, 1, 2, 3);
    animator.add("circle", circle, 1, 10);
    List<IShape> list = animator.getShapes();
    assertEquals(1, list.size());
    IShape c = list.get(0);
    assertEquals(circle.getHeight(), c.getHeight(), 0.0001);
    assertEquals(circle.getWidth(), c.getWidth(), 0.0001);
    assertEquals(circle.getName(), c.getName());
    assertEquals(circle.getBlue(), c.getBlue());
    assertEquals(circle.getGreen(), c.getGreen());
    assertEquals(circle.getRed(), c.getRed());
    assertEquals(circle.getX(), c.getX(), 0.0001);
    assertEquals(circle.getY(), c.getY(), 0.0001);
  }

  @Test
  public void testCalculateStatesAtTickNoTransforms() {
    animator();

    IShape square = new Rect(10, 10, 1, 1, 1, 2, 3);
    IShape rectangle = new Rect(10, 30, 50, 50, 10, 11, 12);
    IShape circle = new Ellipse(10, 10, 15, 20, 50, 1, 3);

    animator.add("square", square, 1, 10);
    animator.add("rectangle", rectangle, 7, 12);
    animator.add("circle", circle, 15, 20);

    List<IShape> list = animator.calculateStatesAtTick(8);

    assertEquals(2, list.size());

    IShape s = list.get(0);
    IShape r = list.get(1);

    assertEquals(square.getHeight(), s.getHeight(), 0.0001);
    assertEquals(square.getWidth(), s.getWidth(), 0.0001);
    assertEquals(square.getName(), s.getName());
    assertEquals(square.getBlue(), s.getBlue());
    assertEquals(square.getGreen(), s.getGreen());
    assertEquals(square.getRed(), s.getRed());
    assertEquals(square.getX(), s.getX(), 0.0001);
    assertEquals(square.getY(), s.getY(), 0.0001);

    assertEquals(rectangle.getHeight(), r.getHeight(), 0.0001);
    assertEquals(rectangle.getWidth(), r.getWidth(), 0.0001);
    assertEquals(rectangle.getName(), r.getName());
    assertEquals(rectangle.getBlue(), r.getBlue());
    assertEquals(rectangle.getGreen(), r.getGreen());
    assertEquals(rectangle.getRed(), r.getRed());
    assertEquals(rectangle.getX(), r.getX(), 0.0001);
    assertEquals(rectangle.getY(), r.getY(), 0.0001);
  }

  @Test
  public void testCalculateStatesAtTickAfterTransforms() {
    animator();

    IShape square = new Rect(10, 10, 1, 1, 1, 2, 3);
    IShape rectangle = new Rect(10, 30, 50, 50, 10, 11, 12);
    IShape circle = new Ellipse(10, 10, 15, 20, 50, 1, 3);

    animator.add("square", square, 1, 10);
    animator.add("rectangle", rectangle, 7, 12);
    animator.add("circle", circle, 15, 20);

    animator.move("square", 3, 7, 15, 10);

    List<IShape> list = animator.calculateStatesAtTick(7);

    assertEquals(2, list.size());

    IShape s = list.get(0);

    assertEquals(10, s.getHeight(), 0.0001);
    assertEquals(10, s.getWidth(), 0.0001);
    assertEquals("square", s.getName());
    assertEquals(3, s.getBlue());
    assertEquals(2, s.getGreen());
    assertEquals(1, s.getRed());
    assertEquals(15, s.getX(), 0.0001);
    assertEquals(10, s.getY(), 0.0001);
  }

  @Test
  public void testCalculateStatesAtTickDuringTransforms() {
    animator();

    IShape square = new Rect(10, 10, 1, 1, 1, 2, 3);
    IShape rectangle = new Rect(10, 30, 50, 50, 10, 11, 12);
    IShape circle = new Ellipse(10, 10, 15, 20, 50, 1, 3);

    animator.add("square", square, 1, 10);
    animator.add("rectangle", rectangle, 3, 12);
    animator.add("circle", circle, 15, 20);

    animator.move("square", 3, 7, 15, 10);

    List<IShape> list = animator.calculateStatesAtTick(5);

    assertEquals(2, list.size());

    IShape s = list.get(0);

    assertEquals(10, s.getHeight(), 0.0001);
    assertEquals(10, s.getWidth(), 0.0001);
    assertEquals("square", s.getName());
    assertEquals(3, s.getBlue());
    assertEquals(2, s.getGreen());
    assertEquals(1, s.getRed());
    assertEquals(8, s.getX(), 0.0001);
    assertEquals(5.5, s.getY(), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMutatingVisitorConstructorNullShape() {
    IShapeMutationVisitor v = new TweeningVisitor(null, 0);
    v.visitAndApplyColorTransform(new ColorTransform(1, 1, 2, 2,
            2, 3, 4, 5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMutatingVisitorConstructorInvalidTime() {
    IShape r = new Rect(10, 10, 10, 10, 2, 4,6);
    IShapeMutationVisitor v = new TweeningVisitor(r, -1);
    v.visitAndApplyColorTransform(new ColorTransform(1, 1, 2, 2,
            2, 3, 4, 5));
  }

  @Test
  public void testMutatingVisitorColor() {
    IShape r = new Rect(10, 10, 10, 10, 2, 4,6);
    IShapeMutationVisitor v = new TweeningVisitor(r, 10);
    assertEquals(10, r.getY(), 0.01);
    assertEquals(10, r.getX(), 0.01);
    assertEquals(10, r.getWidth(), 0.01);
    assertEquals(10, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(4, r.getGreen());
    assertEquals(6, r.getBlue());
    v.visitAndApplyColorTransform(new ColorTransform(1, 12, 2, 2,
            2, 3, 4, 5));
    assertEquals(10, r.getY(), 0.01);
    assertEquals(10, r.getX(), 0.01);
    assertEquals(10, r.getWidth(), 0.01);
    assertEquals(10, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(3, r.getGreen());
    assertEquals(4, r.getBlue());
  }

  @Test
  public void testMutatingVisitorPos() {
    IShape r = new Rect(10, 10, 10, 10, 2, 4,6);
    IShapeMutationVisitor v = new TweeningVisitor(r, 10);
    assertEquals(10, r.getY(), 0.01);
    assertEquals(10, r.getX(), 0.01);
    assertEquals(10, r.getWidth(), 0.01);
    assertEquals(10, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(4, r.getGreen());
    assertEquals(6, r.getBlue());
    v.visitAndApplyPositionTransform(new PositionTransform(1, 12, 2, 2,
            2, 3));
    assertEquals(2.81818, r.getY(), 0.01);
    assertEquals(2.0, r.getX(), 0.01);
    assertEquals(10, r.getWidth(), 0.01);
    assertEquals(10, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(4, r.getGreen());
    assertEquals(6, r.getBlue());
  }

  @Test
  public void testMutatingVisitorScale() {
    IShape r = new Rect(10, 10, 10, 10, 2, 4,6);
    IShapeMutationVisitor v = new TweeningVisitor(r, 10);
    assertEquals(10, r.getY(), 0.01);
    assertEquals(10, r.getX(), 0.01);
    assertEquals(10, r.getWidth(), 0.01);
    assertEquals(10, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(4, r.getGreen());
    assertEquals(6, r.getBlue());
    v.visitAndApplyScaleTransform(new ScaleTransform(1, 12, 2, 2,
            2, 3));
    assertEquals(10, r.getY(), 0.01);
    assertEquals(10, r.getX(), 0.01);
    assertEquals(2.0, r.getWidth(), 0.01);
    assertEquals(2.81818, r.getHeight(), 0.01);
    assertEquals(2, r.getRed());
    assertEquals(4, r.getGreen());
    assertEquals(6, r.getBlue());
  }

  @Test
  public void testToggleDrawMode() {
    animator();
    assertTrue(animator.getFillHa());
    animator.toggleFill();
    assertFalse(animator.getFillHa());
  }

  @Test
  public void testGetDraw() {
    animator();
    assertTrue(animator.getFillHa());
  }

  @Test
  public void testGetSetTempo() {
    animator();
    List<int[]> t = animator.getTempos();
    assertEquals(0, t.size());
    animator.setTempo(2, 10, 20);
    t = animator.getTempos();
    assertEquals(1, t.size());
    assertEquals(2, t.get(0)[0]);
    assertEquals(10, t.get(0)[1]);
    assertEquals(20, t.get(0)[2]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSetTempoNegativeTick() {
    animator();
    animator.setTempo(-2, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSetTempoZeroTick() {
    animator();
    animator.setTempo(0, 10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSetTempoNegativeStart() {
    animator();
    animator.setTempo(1, -10, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetSetTempoNegativeEnd() {
    animator();
    animator.setTempo(1, 10, -20);
  }

  @Test
  public void getDiscrete() {
    animator();
    animator.add("e", new Ellipse(10, 10, 10, 1, 1, 13, 13),
            0, 90);
    animator.setColor("e", 1, 13, 13, 13, 15);
    animator.reScale("e", 12, 30, 12, 11);
    animator.move("e", 16, 40, 10, 70);
    Integer[] ticks = new Integer[] {1, 12, 13, 16, 30, 40};
    Integer[] tickArray = new Integer[animator.getDiscretePlaying().size()];
    tickArray = animator.getDiscretePlaying().toArray(tickArray);
    assertArrayEquals(ticks, tickArray);
  }
}

import org.junit.Test;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.Rect;
import cs3500.view.IView;
import cs3500.view.textualviews.text.TextView;
import static org.junit.Assert.assertEquals;

/**
 * Represents the tests for the text output for an animation. It tests the output for the
 * different shapes as well.
 */
public class TextViewTest {

  @Test
  public void testTextRect() {
    IAnimator animator = new Animator();
    animator.setTickRate(2);
    animator.setBounds(100, 100);
    animator.add("rect", new Rect(10, 10, 1, 1, 100, 100, 100),
            0, 20);
    animator.move("rect", 0,2, 20, 10);
    animator.setColor("rect", 1, 3, 130, 10, 200);
    animator.move("rect", 3, 4, 40, 30);
    animator.reScale("rect", 4, 5, 20, 10);

    Appendable output = new StringBuilder();
    IView view = new TextView(animator, output);
    view.render();
    String expected = "Canvas 100 100\n" +
            "Add shape rect at time 0.000s\n" +
            "Moves from (1.000,1.000) to (20.000,10.000).\n" +
            "Color changes from rgb=[100.000,100.000,100.000] to rgb=[130.000,10.000,200.000].\n" +
            "Moves from (20.000,10.000) to (40.000,30.000).\n" +
            "Re-scaled from (10.000,10.000) to (20.000,10.000).";
    assertEquals(expected, output.toString());
  }

  @Test
  public void testTextEllipse() {
    IAnimator animator = new Animator();
    animator.setTickRate(2);
    animator.setBounds(100, 100);
    animator.add("ellipse", new Ellipse(10, 10, 1, 1, 100, 100, 100),
            0, 20);
    animator.move("ellipse", 0,2, 20, 10);
    animator.setColor("ellipse", 1, 3, 130, 10, 200);
    animator.move("ellipse", 3, 4, 40, 30);
    animator.reScale("ellipse", 4, 5, 20, 10);

    Appendable output = new StringBuilder();
    IView view = new TextView(animator, output);
    view.render();
    String expected = "Canvas 100 100\n" +
            "Add shape ellipse at time 0.000s\n" +
            "Moves from (1.000,1.000) to (20.000,10.000).\n" +
            "Color changes from rgb=[100.000,100.000,100.000] to rgb=[130.000,10.000,200.000].\n" +
            "Moves from (20.000,10.000) to (40.000,30.000).\n" +
            "Re-scaled from (10.000,10.000) to (20.000,10.000).";
    assertEquals(expected, output.toString());
  }
}
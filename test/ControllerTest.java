import org.junit.Test;
import cs3500.controller.clock.Clock;
import cs3500.controller.Controller;
import cs3500.controller.IBasicController;
import cs3500.controller.clock.TimeKeeper;
import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.view.visualview.IVisual;
import static org.junit.Assert.assertEquals;

/**
 * Tests that the controller works in the appropriate manner. Makes sure that the controller has
 * correct behavior.
 */
public class ControllerTest {
  IAnimator animator;

  // makes an animator
  private void animator() {
    animator = new Animator();
  }

  @Test
  public void testClock() {
    TimeKeeper c = new Clock();
    assertEquals(0, c.getTime());
    c.increaseTime();
    assertEquals(1, c.getTime());
    c.decreaseTime();
    assertEquals(0, c.getTime());
    c.increaseTime();
    c.increaseTime();
    assertEquals(2, c.getTime());
    c.reset();
    assertEquals(0, c.getTime());
    c.setTime(13);
    assertEquals(13, c.getTime());
  }

  @Test
  public void testBasicController() {
    animator();
    IVisual v = new MockView();
    IBasicController c = new Controller(v, animator);
    c.start();
    assertEquals("visible", v.toString());
  }

  @Test
  public void testInteractiveController() {
    animator();
    IVisual v = new MockView();
    IBasicController c = new Controller(v, animator);
    c.start();
    assertEquals("visible", v.toString());
  }
}

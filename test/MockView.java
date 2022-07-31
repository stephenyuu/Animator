import java.util.ArrayList;
import java.util.List;

import cs3500.controller.IInteractiveFeatures;
import cs3500.view.visualview.IInteractiveVisual;
import cs3500.view.visualview.IVisual;

/**
 * Mock class to test the functionality of the controller.
 */
public class MockView implements IVisual, IInteractiveVisual {
  private final List<String> output;

  /**
   * Makes a string to test that the controller calls are reaching the view.
   */
  public MockView() {
    this.output = new ArrayList<>();
  }

  @Override
  public void render() {
    output.add("render");
  }

  @Override
  public void makeVisible() {
    output.add("visible");
  }

  @Override
  public void moveFrame() {
    output.add("frame moved");
  }

  @Override
  public String toString() {
    return String.join(" ", output);
  }

  @Override
  public void addFeatures(IInteractiveFeatures feat) {
    output.add("feat");
  }

  @Override
  public void reset() {
    output.add("reset");
  }

  @Override
  public void setFrame(int tick) {
    output.add("setFrame");
  }
}

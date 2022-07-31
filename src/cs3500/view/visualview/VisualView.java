package cs3500.view.visualview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import cs3500.controller.clock.Clock;
import cs3500.controller.clock.TimeKeeper;
import cs3500.model.ReadAnimator;

/**
 * Represents a visual view of an animation. The visual view is displayed in a GUI on the local
 * machine. The visual view of an animation consists of a frame holding a panel in which the
 * animation occurs in. The frame is repainted after every delay period of the timer. The animation
 * is essentially a sequence of this repainting action.
 */
public class VisualView extends JFrame implements IVisual {
  private final TimeKeeper clock;

  /**
   * Constructs the window that holds the animation. The animation is played in this window.
   *
   * @param model the given animation model populated with data
   */
  public VisualView(ReadAnimator model) {
    super();
    this.setTitle("The Easy Animator Visual View");
    this.setLayout(new BorderLayout());
    this.clock = new Clock();
    VisualViewPanel panel = new VisualViewPanel(model, clock);

    panel.setPreferredSize(new Dimension(model.getCanvasWidth(),
            model.getCanvasHeight()));
    this.add(panel, BorderLayout.CENTER);


    // add scroll pane.
    JScrollPane scroll = new JScrollPane(panel);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scroll);

    // format the window.
    this.pack();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  @Override
  public void render() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void moveFrame() {
    clock.increaseTime();
  }
}

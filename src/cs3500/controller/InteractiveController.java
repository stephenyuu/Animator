package cs3500.controller;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import javax.swing.Timer;
import cs3500.controller.clock.Clock;
import cs3500.controller.clock.TimeKeeper;
import cs3500.model.IAnimator;
import cs3500.view.visualview.IInteractiveVisual;

/**
 * Represents a controller for an interactive view. Responds to button presses and inputs
 * from the user. The controller handles the user input and propagates the changes to the view.
 * By default, the looping is set to false.
 */
public class InteractiveController implements IInteractiveFeatures {
  private final TimeKeeper clock;
  private final Timer timer;
  private final IAnimator model;
  private final IInteractiveVisual view;
  private boolean loop;
  private boolean discrete;
  private final Deque<Integer> frames;
  private final List<int[]> tempos;
  private final Deque<int[]> temposCopy;
  private int lastKnownTickRate;

  /**
   * Constructs a controller that handles the user interaction with buttons in the interactive
   * view. This includes the handling of when the play/pause, restart, enable/disable looping,
   * increase speed, decrease speed, and exit buttons are pressed. The controller keeps track and
   * manages the time accordingly.
   * @param model is the populated model to generate the animation.
   * @param view is the view for the animation. In this case, it is an interactive view.
   */
  public InteractiveController(IAnimator model, IInteractiveVisual view) {
    this.discrete = false;
    this.clock = new Clock();
    this.loop = false;
    this.model = model;
    this.view = view;
    this.frames = new LinkedList<>(model.getDiscretePlaying());
    this.tempos = model.getTempos();
    this.temposCopy = new LinkedList<>(tempos);
    this.lastKnownTickRate = model.getTickRate();
    int lastTick = model.getEndingTick();

    this.timer = new Timer(1000 / model.getTickRate(), e -> {

      // slow-mo / tempo.
      if (!temposCopy.isEmpty()) {
        int[] info = temposCopy.getFirst();
        if (info[1] == clock.getTime()) {
          model.setTickRate(info[0]);
          setTimer(model.getTickRate());
        }
        if (info[2] == clock.getTime()) {
          model.setTickRate(lastKnownTickRate);
          setTimer(model.getTickRate());
          temposCopy.pop();
        }
      }

      // delete from frames if we pass the frame.
      if (!frames.isEmpty() && clock.getTime() > frames.getFirst()) {
        frames.pop();
      }
      if (discrete && !frames.isEmpty()) {
        playDiscrete();
      } else {
        playContinuous();
      }
      if (clock.getTime() == lastTick && loop) {
        restart();
      }
    });

    setView(view);
  }

  @Override
  public void start() {
    view.makeVisible();
  }

  @Override
  public void setView(IInteractiveVisual view) {
    view.addFeatures(this);
  }

  @Override
  public void pauseResume() {
    if (timer.isRunning()) {
      timer.stop();
    } else {
      timer.start();
    }
  }

  @Override
  public void restart() {
    timer.restart();
    view.reset();
    clock.reset();
    model.resetShapes();
    frames.clear();
    frames.addAll(model.getDiscretePlaying());
    temposCopy.clear();
    temposCopy.addAll(tempos);
    setTimer(lastKnownTickRate);
  }

  @Override
  public void looping() {
    loop = !loop;
  }

  @Override
  public void increaseSpeed() {
    model.setTickRate(model.getTickRate() + 1);
    timer.setDelay(1000 / model.getTickRate());
    lastKnownTickRate += 1;
  }

  @Override
  public void decreaseSpeed() {
    if (model.getTickRate() - 1 <= 0) {
      return;
    }
    model.setTickRate(model.getTickRate() - 1);
    timer.setDelay(1000 / model.getTickRate());
    lastKnownTickRate -= 1;
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void toggleFillOutline() {
    model.toggleFill();
  }

  @Override
  public void toggleDiscretePlaying() {
    discrete = !discrete;
  }

  /**
   * Plays the discrete states.
   */
  private void playDiscrete() {
    view.render();
    view.setFrame(frames.getFirst());
    clock.setTime(frames.getFirst());
    frames.pop();
  }

  /**
   * Plays the continuous states.
   */
  private void playContinuous() {
    view.render();
    view.moveFrame();
    clock.increaseTime();
  }

  /**
   * Sets the timers delay to the given tick rate.
   *
   * @param tick the tick rate.
   */
  private void setTimer(int tick) {
    timer.setDelay(1000 / tick);
  }
}

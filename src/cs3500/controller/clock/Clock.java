package cs3500.controller.clock;

/**
 * Represents a clock that helps keep track of time by being synced up with the Timer object.
 * The clock is able to tell us the current time, go forward in time, go backward in time, and
 * reset back to the beginning.
 */
public class Clock implements TimeKeeper {
  private int currentTick;

  /**
   * Constructs a clock object with the current tick initialized to 0 since no time has
   * passed when created.
   */
  public Clock() {
    this.currentTick = 0;
  }

  @Override
  public int getTime() {
    return currentTick;
  }

  @Override
  public void increaseTime() {
    this.currentTick += 1;
  }

  @Override
  public void decreaseTime() {
    this.currentTick -= 1;
  }

  @Override
  public void reset() {
    this.currentTick = 0;
  }

  @Override
  public void setTime(int time) {
    if (time < 0) {
      throw new IllegalArgumentException("Time cannot be native");
    }
    currentTick = time;
  }
}

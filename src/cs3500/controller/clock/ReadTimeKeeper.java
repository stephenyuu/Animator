package cs3500.controller.clock;

/**
 * Represents the observable functionalities of the clock. This interface allows the client
 * to get the current time.
 */
public interface ReadTimeKeeper {
  /**
   * Get the current time.
   * @return the current tick
   */
  int getTime();
}

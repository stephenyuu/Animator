package cs3500.animations;

/**
 * Creates a quick sort animation. This animation displays how the quicksort algorithm works from
 * a visual point of view.
 */
public class QuicksortAnimation {

  /**
   * Runs the quick sort algorithm and writes the animation to a file.
   * @param args is the parameters for the animation.
   */
  public static void main(String[] args) {
    SortingAnimations quicksort = new Quicksort();
    quicksort.create();
    quicksort.export("quicksort.txt");
  }
}

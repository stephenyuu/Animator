package cs3500.animations;

/**
 * Creates an insertion sort animation. This animation displays how the insertion sort algorithm
 * works from a visual point of view.
 */
public class InsertionsortAnimation {

  /**
   * Runs the insertion sort algorithm and writes the animation to a file.
   * @param args is the parameters for the animation.
   */
  public static void main(String[] args) {
    SortingAnimations insertion = new InsertionSort();
    insertion.create();
    insertion.export("insertionsort.txt");
  }
}

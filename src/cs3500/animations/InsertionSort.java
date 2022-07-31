package cs3500.animations;

import java.util.List;
import cs3500.model.shape.IShape;

/**
 * Represents an animation for insertion sort algorithm for sorting lists. The animation will sort
 * a list of shapes. The sorting criteria is the sum of the RGB color of the shape. The class
 * will look at the G component of the RGB. Larger R value means greater. The animation will sort
 * a list of six elements.
 */
public class InsertionSort extends ASorting {

  @Override
  public void create() {
    populate(8);
    tick += 4;
    animation.add("canvas 340 120");
    insertionSort(array);
    array_origin.forEach(s -> drawCircle(s, getEnd() + 1));
    makeEntries();
  }

  /**
   * Sorts the given list using insertion sort.
   * @param arr is the list that will be sorted.
   */
  private void insertionSort(List<IShape> arr) {
    ColorComparator comp = new ColorComparator();
    for (int i = 1; i < arr.size(); i++) {
      IShape key = arr.get(i);
      int currIdx = i - 1;
      while (currIdx >= 0 && comp.compare(key, arr.get(currIdx)) < 0) {
        move(key, arr.get(currIdx));
        arr.set(currIdx + 1, arr.get(currIdx));
        currIdx -= 1;
      }
      arr.set(currIdx + 1, key);
    }
  }
}

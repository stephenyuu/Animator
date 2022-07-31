package cs3500.animations;

import java.util.Comparator;
import java.util.List;
import cs3500.model.shape.IShape;

/**
 * Animates the quicksort algorithm that sorts a list. The animation will use a list of size
 * 6 and the "things" being sorted will be shapes. The shapes will be sorted according to the
 * sum of the RGB color. Larger RGB sum means it goes last. The animation will use the green
 * component only.
 */
public class Quicksort extends ASorting {

  @Override
  public void create() {
    // add shapes with random colors.
    populate(8);
    tick += 5;
    // set the canvas size.
    animation.add("canvas 340 120");
    // sort the shapes.
    quickSort(array, 0, array.size(), new ColorComparator());
    // draw the shapes.
    // for circle switch lambda for drawCricle (makePlus)
    // u also have to edit populate so it makes ellipses.
    array_origin.forEach(s -> drawCircle(s, getEnd() + 1));
    makeEntries();
  }

  /**
   * Sorts the given list using the quicksort algorithm. Sorts according to the sum of the
   * RGB values of the shape.
   *
   * @param source is list that will be sorted using quick sort.
   * @param lowIdx is the smallest index in the partition.
   * @param highIdx is the highest index in a partition.
   */
  private void quickSort(List<IShape> source, int lowIdx, int highIdx,
                         Comparator<IShape> comp) {
    if (lowIdx >= highIdx) {
      return;
    }
    IShape pivot = source.get(lowIdx);
    int pivotIdx = getPivotIdx(source, lowIdx, highIdx, pivot, comp);

    quickSort(source, lowIdx, pivotIdx, comp);
    quickSort(source,pivotIdx + 1, highIdx, comp);
  }

  /**
   * Gets the index where the pivot should be placed. The index separates the lists into
   * two partitions, where all the elements left of the index are smaller than the pivot and
   * all the elements right of the index are greater than the pivot..
   *
   * @param source is the list that is being sorted.
   * @param lowIdx is the lowest index in the partition.
   * @param highIdx is the highest index in the partition.
   * @param pivot is the shape that we are trying to find the index for.
   * @return the index for the pivot.
   */
  private int getPivotIdx(List<IShape> source, int lowIdx,
                          int highIdx, IShape pivot, Comparator<IShape> comp) {
    int currLow = lowIdx;
    int currHigh = highIdx - 1;
    while (currLow < currHigh) {
      while (currLow < highIdx && comp.compare(source.get(currLow), pivot) <= 0) {
        currLow += 1;
      }
      while (currHigh >= lowIdx && comp.compare(source.get(currHigh), pivot) > 0) {
        currHigh -= 1;
      }
      if (currLow < currHigh) {
        move(array.get(currLow), array.get(currHigh));
        source.set(currLow, source.set(currHigh, source.get(currLow)));
      }
    }
    move(array.get(lowIdx), array.get(currHigh));
    source.set(lowIdx, source.set(currHigh, source.get(lowIdx)));
    return currHigh;
  }

  /**
   * Draws a Plus shape in the reader format.
   *
   * @param s is the shape that will be drawn.
   * @param end is the last tick in which a transformation ends.
   * @return the shape in File Reader format.
   */
  private String makePlus(IShape s, int end) {
    String template = "plus name %s min-x %.3f min-y %.3f width %.3f height " +
            "%.3f color %f %f %f from 0 to %d";
    return String.format(template, s.getName(), s.getX(), s.getY(), s.getWidth(),
            s.getHeight(), s.getRed() / (float)255, s.getGreen() / (float)255,
            s.getBlue() / (float)255, end);
  }
}

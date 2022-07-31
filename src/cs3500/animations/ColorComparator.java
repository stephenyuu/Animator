package cs3500.animations;

import java.util.Comparator;

import cs3500.model.shape.IShape;

/**
 * Comparator that compares the color of IShapes. Its compares the sum of the RGB components of the
 * shape. Larger sum means that the shape is greater than the other shape.
 */
public class ColorComparator implements Comparator<IShape> {

  @Override
  public int compare(IShape o1, IShape o2) {
    return sum(o1) - sum(o2);
  }

  /**
   * Returns the sum of the RGB components of the given shape.
   *
   * @param shape is the shape whose RGB components you wish to add.
   * @return the sum of the RGB components of the shape.
   */
  private int sum(IShape shape) {
    return shape.getBlue() + shape.getGreen() + shape.getRed();
  }
}

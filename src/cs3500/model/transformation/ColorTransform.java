package cs3500.model.transformation;

import java.awt.Color;
import java.util.stream.Stream;

/**
 * Represents a transformation that changes the color of a given object to a new color. A color
 * is specified by its RGB values. This object holds the data of a color change, including its
 * start and end times, beginning color RGB values, and ending color RGB values.
 */
public class ColorTransform extends ATransform {
  private final Color oldColor;
  private final Color color;

  /**
   * Instantiates a color transformation. The parameters are in RGB.
   *
   * @param start is the tick in which the transformation starts.
   * @param end is the tick in which the transformation ends.
   * @param r is the red component in RGB.
   * @param g is the green component in RGB.
   * @param b is the blue component in RGB.
   * @throws IllegalArgumentException if any parameter is not in the range [0, 255].
   */
  public ColorTransform(int start, int end, int oldR, int oldG, int oldB, int r,
                        int g, int b) {
    super(start, end);
    checkRGB(r, g, b);
    checkRGB(oldR, oldG, oldR);
    this.oldColor = new Color(oldR, oldG, oldB);
    this.color = new Color(r, g, b);
  }

  @Override
  public TransformType getType() {
    return TransformType.COLOR;
  }

  @Override
  public double[] getData() {
    return new double[] {color.getRed(), color.getGreen(), color.getBlue()};
  }

  @Override
  public double[] getOldData() {
    return new double[] {oldColor.getRed(), oldColor.getGreen(), oldColor.getBlue()};
  }

  @Override
  public ITransform copy() {
    return new ColorTransform(start, end, oldColor.getRed(), oldColor.getGreen(),
            oldColor.getBlue(), color.getRed(), color.getGreen(), color.getBlue());
  }

  @Override
  public void visitor(ITVisitor visitor) {
    checkForNulls(visitor);
    visitor.visitColor(this);
  }

  @Override
  public void acceptShapeMutationVisitor(IShapeMutationVisitor shapeMutationVisitor) {
    checkForNulls(shapeMutationVisitor);
    shapeMutationVisitor.visitAndApplyColorTransform(this);
  }

  /**
   * Checks that the RGB values are in the range [0, 255].
   * @param r is the red component of the RGB.
   * @param g is the green component of the RGB.
   * @param b is the blue component of the RGB.
   * @throws IllegalArgumentException if any value of the RGB is not in the range [0, 255].
   */
  private void checkRGB(double r, double g, double b) {
    boolean validateColors = Stream.of(r, g, b).allMatch((val -> val >= 0 && val <= 255));
    if (!validateColors) {
      throw new IllegalArgumentException("A parameter is not in the range [0, 255]");
    }
  }
}

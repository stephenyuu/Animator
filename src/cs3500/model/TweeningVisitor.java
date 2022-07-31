package cs3500.model;

import cs3500.model.shape.IShape;
import cs3500.model.transformation.ColorTransform;
import cs3500.model.transformation.IShapeMutationVisitor;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;
import cs3500.model.transformation.ScaleTransform;

/**
 * Mutates a shape according to the tick and mutation. The visitor tweens the shape
 * according to the transform and the given tick.
 */
public class TweeningVisitor implements IShapeMutationVisitor {
  private final IShape shapeToTransform;
  private int currentTick;

  /**
   * Create a visitor which will transform the shape given the tick. It tweens the shape to the
   * appropriate transition state.
   *
   * @param shapeToTransform is the shape that will be transformed.
   * @param currentTick is the tick at which the shape needs to be inbetweenning at.
   */
  public TweeningVisitor(IShape shapeToTransform, int currentTick) {
    if (currentTick < 0 || shapeToTransform == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.shapeToTransform = shapeToTransform;
    this.currentTick = currentTick;
  }

  @Override
  public IShape visitAndApplyColorTransform(ColorTransform t) {
    double[] beginColor = t.getOldData();
    double[] endColor = t.getData();

    double redAtTick = getUpdatedState(t, currentTick, beginColor[0], endColor[0]);
    double greenAtTick = getUpdatedState(t, currentTick, beginColor[1], endColor[1]);
    double blueAtTick = getUpdatedState(t, currentTick, beginColor[2], endColor[2]);

    shapeToTransform.setColor((int)redAtTick, (int)greenAtTick, (int)blueAtTick);

    return shapeToTransform;
  }

  @Override
  public IShape visitAndApplyPositionTransform(PositionTransform t) {
    double[] beginPosition = t.getOldData();
    double[] endPosition = t.getData();

    double xAtTick = getUpdatedState(t, currentTick, beginPosition[0], endPosition[0]);
    double yAtTick = getUpdatedState(t, currentTick, beginPosition[1], endPosition[1]);

    shapeToTransform.move(xAtTick, yAtTick);

    return shapeToTransform;
  }

  @Override
  public IShape visitAndApplyScaleTransform(ScaleTransform t) {
    double[] beginSize = t.getOldData();
    double[] endSize = t.getData();

    double widthAtTick = getUpdatedState(t, currentTick, beginSize[0], endSize[0]);
    double heightAtTick = getUpdatedState(t, currentTick, beginSize[1], endSize[1]);

    shapeToTransform.reScale(widthAtTick, heightAtTick);

    return shapeToTransform;
  }

  /**
   *  Does the math for the tweening for the trasnfomration.
   *
   * @param t is the transform object.
   * @param currentTick is the tick.
   * @param beginState is the starting state of the shape.
   * @param endState is the ending state of the shape.
   * @return the in between state of the object.
   */
  private double getUpdatedState(ITransform t, int currentTick, double beginState,
                                 double endState) {
    return (beginState * ((t.getEnd() - currentTick) / (double)(t.getEnd() - t.getStart())) +
            (endState * ((currentTick - t.getStart()) / (double)(t.getEnd() - t.getStart()))));
  }
}

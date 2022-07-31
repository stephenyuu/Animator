package cs3500.io;

import cs3500.model.Animator;
import cs3500.model.IAnimator;
import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;
import cs3500.model.shape.Plus;
import cs3500.model.shape.Rect;

/**
 * Adapts the code to file reader. The class serves as an adaptor and builder base on the input
 * of the reader.
 */
public class Builder implements TweenModelBuilder<IAnimator> {
  private final IAnimator animator;

  /**
   * Builds an animator using the data from the reader.
   */
  public Builder() {
    this.animator = new Animator();
  }

  @Override
  public TweenModelBuilder<IAnimator> setBounds(int width, int height) {
    animator.setBounds(width, height);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addOval(String name, float cx, float cy, float xRadius,
                                              float yRadius, float red, float green, float blue,
                                              int startOfLife, int endOfLife) {
    IShape oval = new Ellipse(xRadius, yRadius, cx, cy, (int)(red * 255),
            (int)(green * 255), (int)(blue * 255));
    animator.add(name, oval, startOfLife, endOfLife);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addRectangle(String name, float lx, float ly, float width,
                                                   float height, float red, float green, float blue,
                                                   int startOfLife, int endOfLife) {
    IShape rect = new Rect(width, height, lx, ly, (int)(red * 255), (int)(green * 255),
            (int)(blue * 255));
    animator.add(name, rect, startOfLife, endOfLife);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addPlus(String name, float lx, float ly, float width,
                                              float height, float red, float green, float blue,
                                              int startOfLife, int endOfLife) {
    IShape plus = new Plus(width, height, lx, ly, (int)(red * 255), (int)(green * 255),
            (int)(blue * 255));
    animator.add(name, plus, startOfLife, endOfLife);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addMove(String name, float moveFromX, float moveFromY,
                                              float moveToX, float moveToY, int startTime,
                                              int endTime) {
    animator.move(name, startTime, endTime, moveToX, moveToY);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addColorChange(String name, float oldR, float oldG,
                                                     float oldB, float newR, float newG,
                                                     float newB, int startTime, int endTime) {
    animator.setColor(name, startTime, endTime, (int)(newR * 255), (int)(newG * 255),
            (int)(newB * 255));
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addScaleToChange(String name, float fromSx, float fromSy,
                                                       float toSx, float toSy, int startTime,
                                                       int endTime) {
    animator.reScale(name, startTime, endTime, toSx, toSy);
    return this;
  }

  @Override
  public TweenModelBuilder<IAnimator> addTempo(int tickRate, int start, int end) {
    animator.setTempo(tickRate, start, end);
    return this;
  }

  @Override
  public IAnimator build() {
    return animator;
  }
}

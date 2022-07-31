package cs3500.model.shape;

/**
 * Visitor interface to add functionality to the shapes.
 */
public interface ISVisitor {

  /**
   * Visits the React Class and executes functionality according to the visitor.
   *
   * @param rect is the rectangle that will be visited.
   * @throws IllegalArgumentException if the shape is null.
   */
  void visitRect(Rect rect);

  /**
   * Visits the Ellipse Class and executes functionality according to the visitor.
   *
   * @param ellipse is the ellipse being visited.
   * @throws IllegalArgumentException if the shape is null.
   */
  void visitEllipse(Ellipse ellipse);

  /**
   * Visits the Plus Class and executes the functionality according to the visitor.
   *
   * @param plus is the plus shape being visited.
   * @throws IllegalArgumentException if the shape is null.
   */
  void visitPlus(Plus plus);
}

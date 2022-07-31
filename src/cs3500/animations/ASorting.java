package cs3500.animations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import cs3500.model.shape.Ellipse;
import cs3500.model.shape.IShape;
import cs3500.model.transformation.ITransform;
import cs3500.model.transformation.PositionTransform;

/**
 * Represents animations that animate a sorting algorithm. Abstract class to share common
 * functionalities of sorting algorithms.
 */
public abstract class ASorting implements SortingAnimations {
  protected final List<String> animation;
  protected final Random random;
  protected final List<IShape> array;
  protected final List<IShape> array_origin;
  protected final Map<String, List<ITransform>> transforms;
  int tick;

  protected ASorting() {
    this.animation = new ArrayList<>();
    this.random = new Random();
    this.array = new ArrayList<>();
    this.array_origin = new ArrayList<>();
    this.transforms = new HashMap<>();
    this.tick = 0;
  }

  @Override
  public void export(String location) {
    try {
      FileWriter myWriter = new FileWriter(location);
      myWriter.write(String.join("\n", animation));
      myWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to write to the file.");
    }
  }

  @Override
  public List<IShape> getUnSortedList() {
    return array_origin.stream().map(IShape::copy).collect(Collectors.toList());
  }

  @Override
  public List<IShape> getSortedList() {
    return array.stream().map(IShape::copy).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return String.join("\n", animation);
  }

  @Override
  public void setSeed(int seed) {
    random.setSeed(seed);
  }

  /**
   * Initializes the transformations map and adds six ellipses to the animation. The ellipses
   * are of radius 10 on both directions and are separated by 20 units (boundary wise).
   *
   * @param num is the number of circle you want to add.
   */
  protected void populate(int num) {
    for (int i = 0; i < num; i++) {
      IShape c = new Ellipse(15, 15, 30 + 40 * i, 60,
              0, random.nextInt(256),  0);
      c.setName("circle" + i);
      IShape c_copy = c.copy();
      array.add(c);
      array_origin.add(c_copy);
    }
    // initialize the transforms with a key and an empty list.
    array.forEach((s) -> transforms.put(s.getName(), new ArrayList<>()));
  }

  /**
   * Draws a shape in the reader format.
   * @param s is the shape that will be drawn.
   * @param end is the last tick in which a transformation ends.
   */
  protected void drawCircle(IShape s, int end) {
    String template = "oval name %s center-x %.3f center-y %.1f x-radius %.1f y-radius " +
            "%.1f color %f %f %f from 0 to %d";
    animation.add(String.format(template, s.getName(), s.getX(), s.getY(), s.getWidth(),
            s.getHeight(), s.getRed() / (float)255, s.getGreen() / (float)255,
            s.getBlue() / (float)255, end));
  }

  /**
   * Gets the interval at which the animation should end, meaning that there are no
   * transformation after the tick.
   *
   * @return the tick after which there is no more transformations.
   */
  protected int getEnd() {
    int max = 0;
    for (List<ITransform> t : transforms.values()) {
      if (t.size() != 0) {
        ITransform tLast = t.get(t.size() - 1);
        if (tLast.getEnd() > max) {
          max = tLast.getEnd();
        }
      }
    }
    return max;
  }

  /**
   * Adds transformations for moving a shape.
   *
   * @param t is the list of transformations for the shape.
   * @param before is the original position of the shape.
   * @param after is the position of the shape after moving.
   * @param direction is the direction in the y direction the shape moves. (up or down).
   */
  protected void moveTransforms(List<ITransform> t, double[] before,
                                double[] after, int direction) {
    int y = 20 * direction;
    t.add(new PositionTransform(tick, tick + 2, before[0], before[1],
            before[0], before[1] + y));
    t.add(new PositionTransform(tick + 2, tick + 4, before[0],
            before[1] + y, after[0], before[1] + y));
    t.add(new PositionTransform(tick + 4, tick + 6, after[0],
            before[1] + y, after[0], after[1]));
    tick += 7;
  }

  /**
   * Swaps the positions of the two shapes.
   *
   * @param s1 is the first shape.
   * @param s2 is the second shape.
   */
  protected void move(IShape s1, IShape s2) {
    double[] s1_pos = new double[] {s1.getX(), s1.getY()};
    double[] s2_pos = new double[] {s2.getX(), s2.getY()};
    s1.move(s2_pos[0], s2_pos[1]);
    s2.move(s1_pos[0], s1_pos[1]);
    moveTransforms(transforms.get(s1.getName()), s1_pos, s2_pos, 1);
    moveTransforms(transforms.get(s2.getName()), s2_pos, s1_pos, -1);
  }

  /**
   * Makes the entries for all the animations for all the shape and stores them.
   */
  protected void makeEntries() {
    String template = "move name %s moveto %.3f %.3f %.3f %.3f from %d to %d";
    for (String name : transforms.keySet()) {
      transforms.get(name).forEach(t -> addAnimation(template, name, t));
    }
  }

  /**
   * Converts a move transform to string format (readable by the reader).
   *
   * @param template is the format for a move animation for the reader.
   * @param name is the name of the shape.
   * @param t is a transformation for the shape.
   */
  private void addAnimation(String template, String name, ITransform t) {
    double[] old = t.getOldData();
    double[] latest = t.getData();
    animation.add(String.format(template, name, old[0], old[1], latest[0], latest[1],
            t.getStart(), t.getEnd()));
  }
}

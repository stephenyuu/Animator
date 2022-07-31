import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.animations.InsertionSort;
import cs3500.animations.SortingAnimations;
import cs3500.animations.Quicksort;
import cs3500.io.AnimationFileReader;
import cs3500.io.Builder;
import cs3500.model.IAnimator;
import cs3500.model.shape.IShape;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test that the algorithm is working and creating the appropriate animation.
 */
public class SortingAnimationsTest {

  @Test
  public void testAnimQuickSort() {
    SortingAnimations quick = new Quicksort();
    quick.setSeed(2);
    quick.create();
    List<IShape> s1 = quick.getUnSortedList();
    List<Integer> greens = s1.stream().map(IShape::getGreen).collect(Collectors.toList());
    boolean condition = greens.get(0) < greens.get(1) && greens.get(1) < greens.get(2) &&
            greens.get(2) < greens.get(3) && greens.get(3) < greens.get(4) &&
            greens.get(4) < greens.get(5) && greens.get(5) < greens.get(5);
    assertFalse(condition);
    List<IShape> s2 = quick.getSortedList();
    for (int i = 0; i < s2.size() - 1; i++) {
      assertTrue(s2.get(i).getGreen() < s2.get(i + 1).getGreen());
    }
  }

  @Test
  public void testAnimQuickSortAnimation() {
    SortingAnimations quick = new Quicksort();
    quick.setSeed(2);
    assertEquals("", quick.toString());
    quick.create();
    String expected = "canvas 340 120\n" +
            "oval name circle0 center-x 30.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.733333 0.000000 from 0 to 131\n" +
            "oval name circle1 center-x 70.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.294118 0.000000 from 0 to 131\n" +
            "oval name circle2 center-x 110.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.901961 0.000000 from 0 to 131\n" +
            "oval name circle3 center-x 150.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.003922 0.000000 from 0 to 131\n" +
            "oval name circle4 center-x 190.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.498039 0.000000 from 0 to 131\n" +
            "oval name circle5 center-x 230.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.854902 0.000000 from 0 to 131\n" +
            "oval name circle6 center-x 270.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.988235 0.000000 from 0 to 131\n" +
            "oval name circle7 center-x 310.000 center-y 60.0 x-radius 10.0 " +
            "y-radius 10.0 color 0.000000 0.031373 0.000000 from 0 to 131\n" +
            "move name circle2 moveto 110.000 60.000 110.000 80.000 from 5 to 7\n" +
            "move name circle2 moveto 110.000 80.000 310.000 80.000 from 7 to 9\n" +
            "move name circle2 moveto 310.000 80.000 310.000 60.000 from 9 to 11\n" +
            "move name circle2 moveto 310.000 60.000 310.000 40.000 from 110 to 112\n" +
            "move name circle2 moveto 310.000 40.000 270.000 40.000 from 112 to 114\n" +
            "move name circle2 moveto 270.000 40.000 270.000 60.000 from 114 to 116\n" +
            "move name circle2 moveto 270.000 60.000 270.000 80.000 from 117 to 119\n" +
            "move name circle2 moveto 270.000 80.000 270.000 80.000 from 119 to 121\n" +
            "move name circle2 moveto 270.000 80.000 270.000 60.000 from 121 to 123\n" +
            "move name circle2 moveto 270.000 60.000 270.000 40.000 from 124 to 126\n" +
            "move name circle2 moveto 270.000 40.000 270.000 40.000 from 126 to 128\n" +
            "move name circle2 moveto 270.000 40.000 270.000 60.000 from 128 to 130\n" +
            "move name circle3 moveto 150.000 60.000 150.000 40.000 from 40 to 42\n" +
            "move name circle3 moveto 150.000 40.000 30.000 40.000 from 42 to 44\n" +
            "move name circle3 moveto 30.000 40.000 30.000 60.000 from 44 to 46\n" +
            "move name circle3 moveto 30.000 60.000 30.000 80.000 from 47 to 49\n" +
            "move name circle3 moveto 30.000 80.000 30.000 80.000 from 49 to 51\n" +
            "move name circle3 moveto 30.000 80.000 30.000 60.000 from 51 to 53\n" +
            "move name circle3 moveto 30.000 60.000 30.000 40.000 from 54 to 56\n" +
            "move name circle3 moveto 30.000 40.000 30.000 40.000 from 56 to 58\n" +
            "move name circle3 moveto 30.000 40.000 30.000 60.000 from 58 to 60\n" +
            "move name circle0 moveto 30.000 60.000 30.000 80.000 from 19 to 21\n" +
            "move name circle0 moveto 30.000 80.000 190.000 80.000 from 21 to 23\n" +
            "move name circle0 moveto 190.000 80.000 190.000 60.000 from 23 to 25\n" +
            "move name circle1 moveto 70.000 60.000 70.000 80.000 from 61 to 63\n" +
            "move name circle1 moveto 70.000 80.000 110.000 80.000 from 63 to 65\n" +
            "move name circle1 moveto 110.000 80.000 110.000 60.000 from 65 to 67\n" +
            "move name circle6 moveto 270.000 60.000 270.000 80.000 from 103 to 105\n" +
            "move name circle6 moveto 270.000 80.000 310.000 80.000 from 105 to 107\n" +
            "move name circle6 moveto 310.000 80.000 310.000 60.000 from 107 to 109\n" +
            "move name circle7 moveto 310.000 60.000 310.000 40.000 from 12 to 14\n" +
            "move name circle7 moveto 310.000 40.000 110.000 40.000 from 14 to 16\n" +
            "move name circle7 moveto 110.000 40.000 110.000 60.000 from 16 to 18\n" +
            "move name circle7 moveto 110.000 60.000 110.000 40.000 from 68 to 70\n" +
            "move name circle7 moveto 110.000 40.000 70.000 40.000 from 70 to 72\n" +
            "move name circle7 moveto 70.000 40.000 70.000 60.000 from 72 to 74\n" +
            "move name circle7 moveto 70.000 60.000 70.000 80.000 from 75 to 77\n" +
            "move name circle7 moveto 70.000 80.000 70.000 80.000 from 77 to 79\n" +
            "move name circle7 moveto 70.000 80.000 70.000 60.000 from 79 to 81\n" +
            "move name circle7 moveto 70.000 60.000 70.000 40.000 from 82 to 84\n" +
            "move name circle7 moveto 70.000 40.000 70.000 40.000 from 84 to 86\n" +
            "move name circle7 moveto 70.000 40.000 70.000 60.000 from 86 to 88\n" +
            "move name circle4 moveto 190.000 60.000 190.000 40.000 from 26 to 28\n" +
            "move name circle4 moveto 190.000 40.000 30.000 40.000 from 28 to 30\n" +
            "move name circle4 moveto 30.000 40.000 30.000 60.000 from 30 to 32\n" +
            "move name circle4 moveto 30.000 60.000 30.000 80.000 from 33 to 35\n" +
            "move name circle4 moveto 30.000 80.000 150.000 80.000 from 35 to 37\n" +
            "move name circle4 moveto 150.000 80.000 150.000 60.000 from 37 to 39\n" +
            "move name circle5 moveto 230.000 60.000 230.000 80.000 from 89 to 91\n" +
            "move name circle5 moveto 230.000 80.000 230.000 80.000 from 91 to 93\n" +
            "move name circle5 moveto 230.000 80.000 230.000 60.000 from 93 to 95\n" +
            "move name circle5 moveto 230.000 60.000 230.000 40.000 from 96 to 98\n" +
            "move name circle5 moveto 230.000 40.000 230.000 40.000 from 98 to 100\n" +
            "move name circle5 moveto 230.000 40.000 230.000 60.000 from 100 to 102";
    assertEquals(expected, quick.toString());
  }

  @Test
  public void testCompatibleQuickSort() {
    AnimationFileReader reader = new AnimationFileReader();
    SortingAnimations quicksort = new Quicksort();
    quicksort.setSeed(2);
    quicksort.create();
    quicksort.export("quicksort.txt");
    try {
      IAnimator a = reader.readFile("quicksort.txt", new Builder());
      String[] names1 = quicksort.getSortedList().stream().map(IShape::getName).sorted()
              .toArray(String[]::new);
      String[] names2 = a.getShapes().stream().map(IShape::getName).sorted().toArray(String[]::new);
      assertArrayEquals(names1, names2);
    } catch (FileNotFoundException | IllegalStateException | InputMismatchException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testAnimInsertionSort() {
    SortingAnimations quick = new InsertionSort();
    quick.setSeed(2);
    quick.create();
    List<IShape> s1 = quick.getUnSortedList();
    List<Integer> greens = s1.stream().map(IShape::getGreen).collect(Collectors.toList());
    boolean condition = greens.get(0) < greens.get(1) && greens.get(1) < greens.get(2) &&
            greens.get(2) < greens.get(3) && greens.get(3) < greens.get(4) &&
            greens.get(4) < greens.get(5) && greens.get(5) < greens.get(5);
    assertFalse(condition);
    List<IShape> s2 = quick.getSortedList();
    for (int i = 0; i < s2.size() - 1; i++) {
      assertTrue(s2.get(i).getGreen() < s2.get(i + 1).getGreen());
    }
  }

  @Test
  public void testAnimInsertionSortAnimation() {
    SortingAnimations quick = new InsertionSort();
    quick.setSeed(2);
    assertEquals("", quick.toString());
    quick.create();
    String expected = "canvas 340 120\n" +
            "oval name circle0 center-x 30.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.733333 0.000000 from 0 to 186\n" +
            "oval name circle1 center-x 70.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.294118 0.000000 from 0 to 186\n" +
            "oval name circle2 center-x 110.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.901961 0.000000 from 0 to 186\n" +
            "oval name circle3 center-x 150.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.003922 0.000000 from 0 to 186\n" +
            "oval name circle4 center-x 190.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.498039 0.000000 from 0 to 186\n" +
            "oval name circle5 center-x 230.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.854902 0.000000 from 0 to 186\n" +
            "oval name circle6 center-x 270.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.988235 0.000000 from 0 to 186\n" +
            "oval name circle7 center-x 310.000 center-y 60.0 x-radius 10.0 y-radius 10.0 " +
            "color 0.000000 0.031373 0.000000 from 0 to 186\n" +
            "move name circle2 moveto 110.000 60.000 110.000 40.000 from 25 to 27\n" +
            "move name circle2 moveto 110.000 40.000 150.000 40.000 from 27 to 29\n" +
            "move name circle2 moveto 150.000 40.000 150.000 60.000 from 29 to 31\n" +
            "move name circle2 moveto 150.000 60.000 150.000 40.000 from 67 to 69\n" +
            "move name circle2 moveto 150.000 40.000 190.000 40.000 from 69 to 71\n" +
            "move name circle2 moveto 190.000 40.000 190.000 60.000 from 71 to 73\n" +
            "move name circle2 moveto 190.000 60.000 190.000 40.000 from 95 to 97\n" +
            "move name circle2 moveto 190.000 40.000 230.000 40.000 from 97 to 99\n" +
            "move name circle2 moveto 230.000 40.000 230.000 60.000 from 99 to 101\n" +
            "move name circle2 moveto 230.000 60.000 230.000 40.000 from 123 to 125\n" +
            "move name circle2 moveto 230.000 40.000 270.000 40.000 from 125 to 127\n" +
            "move name circle2 moveto 270.000 40.000 270.000 60.000 from 127 to 129\n" +
            "move name circle3 moveto 150.000 60.000 150.000 80.000 from 18 to 20\n" +
            "move name circle3 moveto 150.000 80.000 110.000 80.000 from 20 to 22\n" +
            "move name circle3 moveto 110.000 80.000 110.000 60.000 from 22 to 24\n" +
            "move name circle3 moveto 110.000 60.000 110.000 80.000 from 32 to 34\n" +
            "move name circle3 moveto 110.000 80.000 70.000 80.000 from 34 to 36\n" +
            "move name circle3 moveto 70.000 80.000 70.000 60.000 from 36 to 38\n" +
            "move name circle3 moveto 70.000 60.000 70.000 80.000 from 46 to 48\n" +
            "move name circle3 moveto 70.000 80.000 30.000 80.000 from 48 to 50\n" +
            "move name circle3 moveto 30.000 80.000 30.000 60.000 from 50 to 52\n" +
            "move name circle0 moveto 30.000 60.000 30.000 40.000 from 11 to 13\n" +
            "move name circle0 moveto 30.000 40.000 70.000 40.000 from 13 to 15\n" +
            "move name circle0 moveto 70.000 40.000 70.000 60.000 from 15 to 17\n" +
            "move name circle0 moveto 70.000 60.000 70.000 40.000 from 39 to 41\n" +
            "move name circle0 moveto 70.000 40.000 110.000 40.000 from 41 to 43\n" +
            "move name circle0 moveto 110.000 40.000 110.000 60.000 from 43 to 45\n" +
            "move name circle0 moveto 110.000 60.000 110.000 40.000 from 81 to 83\n" +
            "move name circle0 moveto 110.000 40.000 150.000 40.000 from 83 to 85\n" +
            "move name circle0 moveto 150.000 40.000 150.000 60.000 from 85 to 87\n" +
            "move name circle0 moveto 150.000 60.000 150.000 40.000 from 151 to 153\n" +
            "move name circle0 moveto 150.000 40.000 190.000 40.000 from 153 to 155\n" +
            "move name circle0 moveto 190.000 40.000 190.000 60.000 from 155 to 157\n" +
            "move name circle1 moveto 70.000 60.000 70.000 80.000 from 4 to 6\n" +
            "move name circle1 moveto 70.000 80.000 30.000 80.000 from 6 to 8\n" +
            "move name circle1 moveto 30.000 80.000 30.000 60.000 from 8 to 10\n" +
            "move name circle1 moveto 30.000 60.000 30.000 40.000 from 53 to 55\n" +
            "move name circle1 moveto 30.000 40.000 70.000 40.000 from 55 to 57\n" +
            "move name circle1 moveto 70.000 40.000 70.000 60.000 from 57 to 59\n" +
            "move name circle1 moveto 70.000 60.000 70.000 40.000 from 179 to 181\n" +
            "move name circle1 moveto 70.000 40.000 110.000 40.000 from 181 to 183\n" +
            "move name circle1 moveto 110.000 40.000 110.000 60.000 from 183 to 185\n" +
            "move name circle6 moveto 270.000 60.000 270.000 40.000 from 109 to 111\n" +
            "move name circle6 moveto 270.000 40.000 310.000 40.000 from 111 to 113\n" +
            "move name circle6 moveto 310.000 40.000 310.000 60.000 from 113 to 115\n" +
            "move name circle7 moveto 310.000 60.000 310.000 80.000 from 102 to 104\n" +
            "move name circle7 moveto 310.000 80.000 270.000 80.000 from 104 to 106\n" +
            "move name circle7 moveto 270.000 80.000 270.000 60.000 from 106 to 108\n" +
            "move name circle7 moveto 270.000 60.000 270.000 80.000 from 116 to 118\n" +
            "move name circle7 moveto 270.000 80.000 230.000 80.000 from 118 to 120\n" +
            "move name circle7 moveto 230.000 80.000 230.000 60.000 from 120 to 122\n" +
            "move name circle7 moveto 230.000 60.000 230.000 80.000 from 130 to 132\n" +
            "move name circle7 moveto 230.000 80.000 190.000 80.000 from 132 to 134\n" +
            "move name circle7 moveto 190.000 80.000 190.000 60.000 from 134 to 136\n" +
            "move name circle7 moveto 190.000 60.000 190.000 80.000 from 144 to 146\n" +
            "move name circle7 moveto 190.000 80.000 150.000 80.000 from 146 to 148\n" +
            "move name circle7 moveto 150.000 80.000 150.000 60.000 from 148 to 150\n" +
            "move name circle7 moveto 150.000 60.000 150.000 80.000 from 158 to 160\n" +
            "move name circle7 moveto 150.000 80.000 110.000 80.000 from 160 to 162\n" +
            "move name circle7 moveto 110.000 80.000 110.000 60.000 from 162 to 164\n" +
            "move name circle7 moveto 110.000 60.000 110.000 80.000 from 172 to 174\n" +
            "move name circle7 moveto 110.000 80.000 70.000 80.000 from 174 to 176\n" +
            "move name circle7 moveto 70.000 80.000 70.000 60.000 from 176 to 178\n" +
            "move name circle4 moveto 190.000 60.000 190.000 80.000 from 60 to 62\n" +
            "move name circle4 moveto 190.000 80.000 150.000 80.000 from 62 to 64\n" +
            "move name circle4 moveto 150.000 80.000 150.000 60.000 from 64 to 66\n" +
            "move name circle4 moveto 150.000 60.000 150.000 80.000 from 74 to 76\n" +
            "move name circle4 moveto 150.000 80.000 110.000 80.000 from 76 to 78\n" +
            "move name circle4 moveto 110.000 80.000 110.000 60.000 from 78 to 80\n" +
            "move name circle4 moveto 110.000 60.000 110.000 40.000 from 165 to 167\n" +
            "move name circle4 moveto 110.000 40.000 150.000 40.000 from 167 to 169\n" +
            "move name circle4 moveto 150.000 40.000 150.000 60.000 from 169 to 171\n" +
            "move name circle5 moveto 230.000 60.000 230.000 80.000 from 88 to 90\n" +
            "move name circle5 moveto 230.000 80.000 190.000 80.000 from 90 to 92\n" +
            "move name circle5 moveto 190.000 80.000 190.000 60.000 from 92 to 94\n" +
            "move name circle5 moveto 190.000 60.000 190.000 40.000 from 137 to 139\n" +
            "move name circle5 moveto 190.000 40.000 230.000 40.000 from 139 to 141\n" +
            "move name circle5 moveto 230.000 40.000 230.000 60.000 from 141 to 143";
    assertEquals(expected, quick.toString());
  }

  @Test
  public void testCompatibleInsertionSort() {
    AnimationFileReader reader = new AnimationFileReader();
    SortingAnimations quicksort = new InsertionSort();
    quicksort.setSeed(2);
    quicksort.create();
    quicksort.export("insert.txt");
    try {
      IAnimator a = reader.readFile("insert.txt", new Builder());
      String[] names1 = quicksort.getSortedList().stream().map(IShape::getName).sorted()
              .toArray(String[]::new);
      String[] names2 = a.getShapes().stream().map(IShape::getName).sorted().toArray(String[]::new);
      assertArrayEquals(names1, names2);
    } catch (FileNotFoundException | IllegalStateException | InputMismatchException e) {
      e.printStackTrace();
    }
  }
}

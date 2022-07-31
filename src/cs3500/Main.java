package cs3500;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.swing.JOptionPane;
import cs3500.controller.Controller;
import cs3500.controller.IBasicController;
import cs3500.controller.InteractiveController;
import cs3500.io.AnimationFileReader;
import cs3500.io.Builder;
import cs3500.model.IAnimator;
import cs3500.view.visualview.IInteractiveVisual;
import cs3500.view.IView;
import cs3500.view.visualview.IVisual;
import cs3500.view.ViewCreator;

/**
 * Runs the program. Depending on the arguments to the program will output different views for
 * the animation.
 */
public class Main {

  /**
   * Instantiates the program and starts it. Runs according to parameters.
   *
   * @param args is the arguments that determine the view type, file source, output source and
   *             tick speed.
   */
  public static void main(String[] args) {
    int speed = 1;
    int params = args.length;
    IAnimator animator = null;
    Appendable dump = new StringBuilder();
    List<String> commands = new ArrayList<>(Arrays.asList("-in", "-view", "-speed", "-out"));
    Map<String, String> cmdPairs = new HashMap<>();

    // checks that parameters come in pairs.
    if (params % 2 != 0) {
      showError("Incorrect number of parameters");
    }

    // save the command and value together.
    for (int i = 0; i < params; i += 2) {
      cmdPairs.put(args[i], args[i + 1]);
    }

    checkValidCmd(commands, cmdPairs.keySet());
    String source = cmdPairs.get("-in");
    String output = cmdPairs.get("-out");
    String view = cmdPairs.get("-view");

    // read the file input.
    try {
      animator = new AnimationFileReader().readFile(source, new Builder());

      // set the speed if a speed is given.
      if (Objects.nonNull(cmdPairs.get("-speed"))) {
        try {
          speed = Integer.parseInt(cmdPairs.get("-speed"));
        } catch (IllegalFormatException e) {
          showError("The given speed must be numeric.");
        }
      }
      animator.setTickRate(speed);
    }
    catch (FileNotFoundException | IllegalStateException | InputMismatchException e) {
      showError("There was an issue reading the file.");
      System.exit(0);
    }

    // make a build and render the view.
    ViewCreator c = new ViewCreator(animator, dump);
    try {
      IView v = c.factory(view);
      if (view.equals("visual") || view.equals("interactive")) {
        startView(view, v, animator);
        return;
      }
      v.render();
    } catch (IllegalArgumentException e) {
      showError("The given view is invalid.");
      System.exit(0);
    }

    // output the file to the output location if it exists.
    if (Objects.nonNull(output)) {
      try {
        FileWriter myWriter = new FileWriter(output);
        myWriter.write(dump.toString());
        myWriter.close();
      } catch (IOException e) {
        showError("File destination is invalid.");
        System.exit(0);
      }
    } else {
      System.out.print(dump);
    }
  }

  /**
   * Checks that all the values in the set appear in the given list.
   * @param commands is the list of all valid commands.
   * @param keys is set of commands that the user inputted.
   */
  private static void checkValidCmd(List<String> commands, Set<String> keys) {
    if (!new HashSet<>(commands).containsAll(keys)) {
      showError("Some of the commands are invalid.");
    }
  }

  /**
   * How an error message pop up with the given message.
   *
   * @param infoMessage the error message you want to display.
   */
  private static void showError(String infoMessage) {
    JOptionPane.showMessageDialog(null, infoMessage, "Error",
            JOptionPane.INFORMATION_MESSAGE);
    System.exit(0);
  }

  /**
   * Start a visual view depending on the view type.
   *
   * @param view is the view type.
   * @param v the view object that will be used.
   * @param anim is the animator model.
   */
  private static void startView(String view, IView v, IAnimator anim) {
    IBasicController ctrl;
    if (view.equals("visual")) {
      ctrl = new Controller((IVisual) v, anim);
    } else {
      ctrl = new InteractiveController(anim, (IInteractiveVisual) v);
    }
    ctrl.start();
  }
}
package controller;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * FileParser class is used to parse the file commands and interpret the commands. It will
 * read keywords in the file and match them with instructions in the PhotoAlbumBuilder class.
 */

public class FileParser {

  /**
   * Parse the file and match them with commands.
   * @param readable A Readable input file.
   * @param builder A PhotoAlbumBuilder object.
   * @return <T></T> model
   * @throws IllegalStateException if the keyword in the file is invalid.
   */
  public static <T> T parseFile(Readable readable, PhotoAlbumBuilder<T> builder)
          throws IllegalStateException {
    Objects.requireNonNull(readable, "Input cannot be null");
    Objects.requireNonNull(builder, "Builder cannot be null");
    try (Scanner scanner = new Scanner(readable)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }
        String[] parts = line.split("\\s+", 2);
        String command = parts[0].toLowerCase(Locale.ROOT);
        switch (command) {
          case "shape" -> readShape(parts, builder);
          case "move" -> readMove(parts, builder);
          case "color" -> readColor(parts, builder);
          case "resize" -> readResize(parts, builder);
          case "remove" -> readRemove(parts, builder);
          case "snapshot" -> readSnapshot(parts, builder);
          default -> throw new IllegalStateException("Invalid command: " + command);
        }
      }
    }
    return builder.build();
  }

  /**
   * Interpret the add shape command. It should contain 9 parameters and create a new shape.
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   * @throws IllegalArgumentException if the parameter for creating a new shape is missing.
   */
  private static <T> void readShape(String[] wordList, PhotoAlbumBuilder<T> builder)
          throws IllegalArgumentException {
    wordList = wordList[1].split("\\s+");

    if (wordList.length < 9) {
      throw new IllegalArgumentException("The parameter for creating a new shape is missing.");
    }

    String shapeName = wordList[0];
    String shapeType = wordList[1];
    double[] val = new double[7];
    for (int i = 0, j = 2; i < 7; i++, j++) {
      val[i] = Double.parseDouble(wordList[j]);
    }

    builder.addShape(shapeName, shapeType, val[0], val[1], val[2],
            val[3], val[4], val[5], val[6]);
  }

  /**
   * Interpret the move command. It should contain 3 parameters and move the shape.
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   * @throws IllegalArgumentException if the parameter for moving a shape is missing.
   */
  private static <T> void readMove(String[] wordList, PhotoAlbumBuilder<T> builder) {
    wordList = wordList[1].split("\\s+");

    if (wordList.length < 3) {
      throw new IllegalArgumentException("The parameter for moving a shape is missing.");
    }

    String shapeName = wordList[0];
    double[] val = new double[2];
    for (int i = 0, j = 1; i < 2; i++, j++) {
      val[i] = Double.parseDouble(wordList[j]);
    }
    builder.moveShape(shapeName, val[0], val[1]);
  }


  /**
   * Interpret the change color command. It should contain 4 parameters and change the shape color.
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   * @throws IllegalArgumentException if the parameter for changing the shape color is missing.
   */
  private static <T> void readColor(String[] wordList, PhotoAlbumBuilder<T> builder)
          throws IllegalArgumentException {
    wordList = wordList[1].split("\\s+");

    if (wordList.length < 4) {
      throw new IllegalArgumentException("The parameter for changing the shape color is missing.");
    }

    String shapeName = wordList[0];
    double[] val = new double[3];
    for (int i = 0, j = 1; i < 3; i++, j++) {
      val[i] = Double.parseDouble(wordList[j]);
    }
    builder.changeColor(shapeName, val[0], val[1], val[2]);
  }

  /**
   * Interpret the resize command. It should contain 3 parameters and resize the shape.
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   * @throws IllegalArgumentException if the parameter for resizing the shape is missing.
   */
  private static <T> void readResize(String[] wordList, PhotoAlbumBuilder<T> builder) {
    wordList = wordList[1].split("\\s+");

    if (wordList.length < 3) {
      throw new IllegalArgumentException("The parameter for resizing the shape is missing.");
    }

    String shapeName = wordList[0];
    double[] val = new double[2];
    for (int i = 0, j = 1; i < 2; i++, j++) {
      val[i] = Double.parseDouble(wordList[j]);
    }
    builder.resizeShape(shapeName, val[0], val[1]);
  }

  /**
   * Interpret the remove command. It should contain 1 parameter and remove the shape.
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   */
  private static <T> void readRemove(String[] wordList, PhotoAlbumBuilder<T> builder) {
    wordList = wordList[1].split("\\s+");
    if (wordList[0].length() > 0) {
      String shapeName = wordList[0];
      builder.removeShape(shapeName);
    }
  }

  /**
   * Interpret the snapshot command. It should take a snapshot of the current state, the snapshot
   * command could have description (optional).
   * @param wordList A String[] representing the parameters.
   * @param builder A PhotoAlbumBuilder object.
   */
  private static <T> void readSnapshot(String[] wordList, PhotoAlbumBuilder<T> builder) {
    String description = "";
    if (wordList.length > 1) {
      description = wordList[1];
    }
    builder.takeSnapshot(description);
  }

}

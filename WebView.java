package view;

import model.IShape;
import model.Snapshot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * WebView class implementing IView interface is a static web page using html markup and SVG.
 */

public class WebView implements IView {
  private List<Snapshot> snapshotList;
  private static final int SIZE = 1000;
  private int width;
  private int height;
  Appendable output;

  /**
   * The constructor for WebView class.
   * @param width An integer representing the width of the view window.
   * @param height An integer representing the height of the view window.
   */
  public WebView(int width, int height) {
    if (width < 0 && height > 0) {
      this.width = SIZE;
      this.height = height;
    } else if (width > 0 && height < 0) {
      this.width = width;
      this.height = SIZE;
    } else if (width < 0 && height < 0) {
      this.width = SIZE;
      this.height = SIZE;
    } else {
      this.width = width;
      this.height = height;
    }
    this.output = new StringBuilder();
  }

  /**
   * Set up the snapshot list.
   * @param snapshotList A {@code List<Snapshot>} storing the current snapshots.
   * @throws IllegalArgumentException if the snapshot list is null, en error will be thrown.
   */
  public void setSnapshot(List<Snapshot> snapshotList) throws IllegalArgumentException {
    if (snapshotList == null) {
      throw new IllegalArgumentException("The snapshot list cannot be null.");
    }
    this.snapshotList = snapshotList;
  }

  /**
   * Draw a single SVG image based on a snapshot.
   * @param snapshot A Snapshot object.
   * @throws IOException if appendable fails, an error will be thrown.
   */
  public void drawSvg(Snapshot snapshot) throws IOException {
    if (snapshot == null) {
      return;
    }
    // set up the view window and the border
    output.append("<svg width=\"").append(String.valueOf(this.width)).append("\" height=\"")
            .append(String.valueOf(this.height)).append("\" version=\"1.1\"")
            .append("style =\"border: solid 5px red; background-color:rgb(173,216,230)\"\n")
            .append("xmlns=\"http://www.w3.org/2000/svg\">\n");
    output.append("<g>");

    String snapshotID = snapshot.getSnapshotID();
    String description = snapshot.getDescription();
    List<IShape> shapeList = snapshot.getShapelist();

    for (IShape shape: shapeList) {
      String shapeType;
      String format;
      String shapeInfo;

      switch (shape.getShapeType()) {
        case ("rectangle") -> {
          shapeType = "rect"; // SVG rectangle
          format = "<%s id=\"%s\" x=\"%.2f\" y=\"%.2f\" width=\"%.2f\" "
                  + "height=\"%.2f\" fill=\"rgb(%f,%f,%f)\" visibility=\"visible\" /> \n";
          shapeInfo = String.format(format, shapeType, shape.getShapeName(),
                shape.getPosition().getX(), shape.getPosition().getY() + 150,
                shape.getSize()[0], shape.getSize()[1],
                shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue());
        }
        case ("oval") -> {
          shapeType = "ellipse"; // SVG ellipse is a more general form than circle
          format = "<%s id=\"%s\" cx=\"%.2f\" cy=\"%.2f\" rx=\"%.2f\" ry=\"%.2f\" "
                  + "fill=\"rgb(%f,%f,%f)\" visibility=\"visible\"/> \n";
          shapeInfo = String.format(format, shapeType, shape.getShapeName(),
               shape.getPosition().getX(), shape.getPosition().getY() + 150,
               shape.getSize()[0], shape.getSize()[1],
               shape.getColor().getRed(), shape.getColor().getGreen(), shape.getColor().getBlue());
        }
        default -> throw new IllegalStateException("The shape does not exist in the system.");
      }
      output.append(shapeInfo);
    }
    // set up the distance of lines and font
    output.append("<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">")
            .append(snapshotID).append("</text>\n");
    output.append("<text x=\"10\" y=\"100\" font-size=\"18\">" + "Description: ")
            .append(description).append("</text>\n");
    output.append("</g>");
    output.append("""
            </svg>
            <p></p>
            """);
  }

  /**
   * Save the result to a html file.
   * @param filePath A String representing the path of the file.
   */
  public void saveSvg(String filePath) {
    try {
      FileWriter writer = new FileWriter(filePath);
      writer.write(this.output.toString());
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void displaySnapshot() {
    try {
      output.append("<!DOCTYPE html>\n<html>\n<body>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Snapshot each: snapshotList) {
      try {
        drawSvg(each);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    try {
      output.append("</body>\n</html>");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the result in String form for testing.
   * @return A String representing the output.
   */
  public String getOutput() {
    return this.output.toString();
  }

  /**
   * Get the size of the view window.
   * @return An int[] representing the size.
   */
  public int[] getSize() {
    return new int[]{this.width, this.height};
  }
}

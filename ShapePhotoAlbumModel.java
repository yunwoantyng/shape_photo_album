package model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * ShapePhotoAlbumModel class implementing ShapePhotoAlbum interface serves as the model of the
 * Model-View-Controller architecture.
 */
public class ShapePhotoAlbumModel implements ShapePhotoAlbum {
  private String snapshotID;
  private String timestamp;
  private String description;
  private List<IShape> shapeList;
  private List<Snapshot> snapshotList;
  private List<String> snapshotIDlist;
  private Map<String, List<IShape>> snapshotMap;
  // a snapshotID-list of shapes key-value pair
  private Map<String, String> snapshotDetailMap;
  // a snapshotID-snapshot details key-value pair
  private Map<String, String> snapshotDescriptionMap;
  // a snapshotID-snapshot description key-value pair

  /**
   * The constructor for ShapePhotoAlbumModel class.
   */
  public ShapePhotoAlbumModel() {
    this.shapeList = new ArrayList<>();
    this.snapshotIDlist = new ArrayList<>();
    this.snapshotMap = new LinkedHashMap<>();
    this.snapshotDetailMap = new HashMap<>();
    this.snapshotDescriptionMap = new HashMap<>();
    this.snapshotList = new ArrayList<>();
  }

  public List<Snapshot> getSnapshotList() {
    return this.snapshotList;
  }

  @Override
  public void addShape(String shapeName, String shapeType, double xPoint, double yPoint,
                     double firstParameter, double secondParameter,
                     double color1, double color2, double color3) throws IllegalArgumentException {
    if (shapeType == null || shapeType.length() == 0) {
      throw new IllegalArgumentException("The type of the shape cannot be null or empty.");
    }
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The name of the shape cannot be null or empty.");
    }
    if (firstParameter <= 0 || secondParameter <= 0) {
      throw new IllegalArgumentException("The size of the shape cannot be zero or negative.");
    }
    if (checkSameName(shapeName)) {
      throw new IllegalArgumentException("The name of the shape has been occupied in the album.");
    }
    if (!shapeType.equalsIgnoreCase("rectangle")
            && !shapeType.equalsIgnoreCase("oval")) {
      throw new IllegalArgumentException("The type of the shape does not exist in the system.");
    }

    IShape shape = null;
    Point2D position = new Point2D(xPoint, yPoint);
    Color color = new Color(color1, color2, color3);

    if (shapeType.equalsIgnoreCase("rectangle")) {
      shape = new Rectangle(shapeName, firstParameter, secondParameter, position, color);
    }
    if (shapeType.equalsIgnoreCase("oval")) {
      shape = new Oval(shapeName, firstParameter, secondParameter, position, color);
    }

    this.shapeList.add(shape);
  }

  @Override
  public boolean checkSameName(String shapeName) throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The given name of the shape cannot be null or empty.");
    }
    for (IShape each: this.shapeList) {
      if (each.getShapeName().equals(shapeName)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The given name to be removed cannot be null or empty.");
    }
    for (IShape each: this.shapeList) {
      if (shapeName.equals(each.getShapeName())) {
        this.shapeList.remove(each);
        return;
      }
    }
  }

  @Override
  public void moveShape(String shapeName, double newXPoint, double newYPoint)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The given name of the shape cannot be null or empty.");
    }
    for (int i = 0; i < this.shapeList.size(); i++) {
      if (this.shapeList.get(i).getShapeName().equals(shapeName)) {
        this.shapeList.set(i, shapeList.get(i).move(new Point2D(newXPoint, newYPoint)));
      }
    }
  }

  @Override
  public void resizeShape(String shapeName, double newFirstValue, double newSecondValue)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The given name of the shape cannot be null or empty.");
    }
    if (newFirstValue <= 0 || newSecondValue <= 0) {
      throw new IllegalArgumentException("The size of the shape cannot be zero or negative.");
    }
    for (int i = 0; i < this.shapeList.size(); i++) {
      if (this.shapeList.get(i).getShapeName().equals(shapeName)) {
        this.shapeList.set(i, shapeList.get(i).resize(newFirstValue, newSecondValue));
      }
    }
  }

  @Override
  public void changeColor(String shapeName, double newColor1, double newColor2, double newColor3)
          throws IllegalArgumentException {
    if (shapeName == null || shapeName.length() == 0) {
      throw new IllegalArgumentException("The given name of the shape cannot be null or empty.");
    }
    for (int i = 0; i < this.shapeList.size(); i++) {
      if (this.shapeList.get(i).getShapeName().equals(shapeName)) {
        this.shapeList.set(i, shapeList.get(i)
                .changeColor(new Color((int)newColor1, (int)newColor2, (int)newColor3)));
      }
    }
  }

  @Override
  public List<IShape> getShapeList() {
    return this.shapeList;
  }

  @Override
  public List<String> getSnapshotIDlist() {
    return this.snapshotIDlist;
  }

  @Override
  public void takeSnapshot(String description) {
    Instant now = Instant.now();
    ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
    this.snapshotID = zonedDateTime.toLocalDateTime().toString();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.timestamp = zonedDateTime.format(formatter);

    if (description == null) {
      description = "";
    }
    this.description = description;
    this.snapshotIDlist.add(this.snapshotID);

    Snapshot snapshot = new Snapshot(snapshotID, description, new ArrayList<>(shapeList));
    snapshotList.add(snapshot);

    StringBuilder shapeInfo = new StringBuilder();
    for (IShape each: this.shapeList) {
      shapeInfo.append(each.toString()).append("\n\n");
    }

    String snapshotDetail = String.format("""
            Snapshot ID: %s
            Timestamp: %s
            Description: %s
            Shape Information:
            %s""", this.snapshotID, this.timestamp, this.description, shapeInfo.toString().trim());

    this.snapshotDetailMap.put(this.snapshotID, snapshotDetail);

    this.snapshotDescriptionMap.put(this.snapshotID, this.description);
  }

  @Override
  public String getLatestSnapshotID() {
    return this.snapshotID;
  }

  @Override
  public Map<String, List<IShape>> getSnapshotMap() {
    return this.snapshotMap;
  }

  @Override
  public Map<String, String> getDescriptionMap() {
    return this.snapshotDescriptionMap;
  }

  @Override
  public String printSnapshotIDlist() {
    StringBuilder printSnapshotIDs = new StringBuilder();
    for (String each: this.snapshotIDlist) {
      printSnapshotIDs.append(each);
      if (this.snapshotIDlist.indexOf(each) < this.snapshotIDlist.size() - 1) {
        printSnapshotIDs.append(", ");
      }
    }
    return String.format("List of snapshots taken before reset: [%s]", printSnapshotIDs);
  }

  @Override
  public String printSnapshots() {
    StringBuilder printAll = new StringBuilder();
    for (String ID: this.snapshotIDlist) {
      printAll.append(this.snapshotDetailMap.get(ID));
      if (this.snapshotIDlist.indexOf(ID) < this.snapshotIDlist.size() - 1) {
        printAll.append("\n\n");
      }
    }
    return String.format("Printing Snapshots:\n%s", printAll);
  }


  @Override
  public void reset() {
    this.shapeList.clear();
    this.snapshotIDlist.clear();
    this.snapshotMap.clear();
    this.snapshotDetailMap.clear();
  }
}

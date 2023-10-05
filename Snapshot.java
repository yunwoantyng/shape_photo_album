package model;

import java.util.List;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * Snapshot class is used to store all the snapshots and their information in the photo album.
 */
public class Snapshot {
  private String snapshotID;
  private String description;
  private List<IShape> shapeList;

  /**
   * The constructor for Snapshot class.
   * @param snapshotID A String representing the snapshot ID.
   * @param description A String representing the description of a snapshot.
   * @param shapeList A {@code List<IShape>} storing the current shapes.
   */
  public Snapshot(String snapshotID, String description, List<IShape> shapeList) {
    this.snapshotID = snapshotID;
    this.description = description;
    this.shapeList = shapeList;
  }

  /**
   * Gets a list of the shapes corresponding to the snapshot.
   * @return A {@code List<IShape>} storing the current shapes.
   */
  public List<IShape> getShapelist() {
    return shapeList;
  }

  /**
   * Gets the ID of the snapshot.
   * @return A String representing the snapshot ID.
   */
  public String getSnapshotID() {
    return snapshotID;
  }

  /**
   * Gets the description corresponding to the snapshot.
   * @return A String representing the description of a snapshot.
   */
  public String getDescription() {
    return description;
  }


}

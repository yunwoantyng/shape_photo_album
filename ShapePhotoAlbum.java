package model;

import java.util.List;
import java.util.Map;

/**
 * CS5004 Homework08 Wan-Ting Yun
 * ShapePhotoAlbum interface is used for the implementation of ShapePhotoAlbumModel.
 *
 */
public interface ShapePhotoAlbum {
  /**
   * Add a shape to the album.
   * @param shapeName A String representing the name of the shape.
   * @param shapeType A String representing the type of the shape.
   * @param firstParameter A double representing the value of the 1st length of the shape.
   * @param secondParameter A double representing the value of the 2nd length of the shape.
   * @param xPoint A double representing x-cor.
   * @param yPoint A double representing y-cor.
   * @param color1 A double representing the 1st number in the brackets.
   * @param color2 A double representing the 2nd number in the brackets.
   * @param color3 A double representing the 3rd number in the brackets.
   * @throws IllegalArgumentException if the shape type or the shape name is null or empty, any of
   *         the size of the shape is negative, or the shape name is already occupied.
   */
  void addShape(String shapeName, String shapeType, double xPoint, double yPoint,
                double firstParameter, double secondParameter,
                double color1, double color2, double color3) throws IllegalArgumentException;

  /**
   * Check if a name of the shape is occupied in the photo album.
   * @param shapeName A String representing the target name of the shape.
   * @return true if the name exists, otherwise false.
   */
  boolean checkSameName(String shapeName);

  /**
   * Remove a shape from the photo album.
   * @param shapeName A String representing the target name of the shape.
   */
  void removeShape(String shapeName);

  /**
   * Move the current shape to a designated new position.
   * @param shapeName A String representing the target name of the shape.
   * @param newXPoint A double representing a new x-cor.
   * @param newYPoint A double representing a new y-cor.
   */
  void moveShape(String shapeName, double newXPoint, double newYPoint);

  /**
   * Change the size of the shape.
   * @param shapeName A String representing the target name of the shape.
   * @param newFirstValue A double representing the new value of the 1st length of the shape.
   * @param newSecondValue A double representing the new value of the 2nd length of the shape.
   */
  void resizeShape(String shapeName, double newFirstValue, double newSecondValue);

  /**
   * Change the color of the shape.
   * @param shapeName A String representing the target name of the shape.
   * @param newColor1 A double representing the new 1st number in the brackets.
   * @param newColor2 A double representing the new 2nd number in the brackets.
   * @param newColor3 A double representing the 3rd number in the brackets.
   */
  void changeColor(String shapeName, double newColor1, double newColor2, double newColor3);

  /**
   * Get a current list of the shapes in the photo album.
   * @return A {@code List<IShape>} storing the current shapes.
   */
  List<IShape> getShapeList();

  /**
   * Get a current list of the snapshot IDs.
   * @return A {@code List<String>} storing the current snapshot IDs.
   */
  List<String> getSnapshotIDlist();

  /**
   * Take a snapshot that captures all the information of shapes at a moment. Each snapshot is a
   * "freeze-frame" of the current state. The snapshots are historical "save points" that can be
   * retrieved irrespective of the current state.
   * @param description A String representing a comment that comes along with a snapshot.
   */
  void takeSnapshot(String description);

  /**
   * Get the latest snapshot ID after taking a snapshot.
   * @return A String representing the snapshot ID.
   */
  String getLatestSnapshotID();

  /**
   * Get the snapshotID-shape list map.
   * @return A {@code Map<String, List<IShape>>} representing a snapshotID-shape list key-value
   *         pair map.
   */
  Map<String, List<IShape>> getSnapshotMap();

  /**
   * Get the snapshotID-snapshot description map.
   * @return A {@code Map<String, String>} representing a snapshotID-description key-value pair map.
   */

  Map<String, String> getDescriptionMap();

  /**
   * Print out a list of the snapshot IDs that demonstrates all the snapshots taken before reset.
   * @return A String representing a list of the snapshot IDs in a designated format.
   *     e.g. List of snapshots taken before reset: [2022-03-30T11:51:02.174193,
   *     2022-03-30T11:51:02.246661, 2022-03-30T11:51:02.247210, 2022-03-30T11:51:02.247794]
   */
  String printSnapshotIDlist();

  /**
   * Print out all the snapshot details.
   * @return A String representing details of the snapshots in a designated format.
   *     e.g. Printing Snapshots
   *          Snapshot ID: 2022-03-30T11:51:02.174193
   *          Timestamp: 30-03-2022 11:51:02
   *          Description: After first selfie
   *          Shape Information:
   *          Name: R
   *          Type: rectangle
   *          Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
   *
   *          Name: O
   *          Type: oval
   *          Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   */
  String printSnapshots();

  /**
   * Reset the shape photo album. That is, removing all the information of shapes and snapshots
   * from the system. (This method has not been used yet)
   */
  void reset();

  /**
   * Get a current list of the snapshots.
   * @return A {@code List<Snapshot>} storing the current snapshots.
   */
  List<Snapshot> getSnapshotList();

}

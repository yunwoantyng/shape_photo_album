import static org.junit.Assert.assertEquals;

import model.ShapePhotoAlbum;
import model.ShapePhotoAlbumModel;
import model.Snapshot;
import org.junit.Before;
import org.junit.Test;
import view.WebView;

import java.util.List;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * JUnit test for WebView class.
 */
public class WebViewTest {
  private ShapePhotoAlbum model;

  /**
   * Set up the model for testing.
   */
  @Before
  public void SetUp() {
    model = new ShapePhotoAlbumModel();
  }

  /**
   * Test if getSize() returns the correct bounds for the view window.
   */
  @Test
  public void testGetSize() {
    WebView view = new WebView(800, 800);
    assertEquals(800, view.getSize()[0]);
    assertEquals(800, view.getSize()[1]);
  }

  /**
   * Test if bounds for the view window are set to the default value when the parameters passing in
   * are negative.
   */
  @Test
  public void testSizeNegative() {
    WebView view = new WebView(-1, -1);
    assertEquals(1000, view.getSize()[0]);
    assertEquals(1000, view.getSize()[1]);
  }

  /**
   * Test if setSnapshot() raises an error when null is passed in.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullSnapshotList() {
    WebView view = new WebView(1000, 1000);
    view.setSnapshot(null);
  }

  /**
   * Test if getOutput() returns the correct html format when the snapshot list is empty.
   */
  @Test
  public void testNoSnapshot() {
    List<Snapshot> snapshotList = model.getSnapshotList();
    WebView view = new WebView(1000, 1000);
    view.setSnapshot(snapshotList);
    assertEquals(view.getOutput(), "");
    view.displaySnapshot();
    assertEquals(view.getOutput(), """
            <!DOCTYPE html>
            <html>
            <body>
            </body>
            </html>""");
  }

  /**
   * Test output after taking one snapshot.
   */
  @Test
  public void testOneSnapshot() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    String snapshotID = this.model.getLatestSnapshotID();
    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();
    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n" + "</body>\n" + "</html>";
    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after taking many snapshots.
   */
  @Test
  public void testManySnapshots() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.addShape("A", "rectangle", 500, 500, 500, 500, 0, 0, 0);
    this.model.takeSnapshot("Take Two");
    this.model.addShape("B", "oval", 10, 10, 10, 10, 10, 10, 10);
    this.model.takeSnapshot("Take Three");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();
    String snapshotID3 = this.model.getSnapshotList().get(2).getSnapshotID();
    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();

    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<rect id=\"A\" x=\"500.00\" y=\"650.00\" width=\"500.00\" height=\"500.00\""
            + " fill=\"rgb(0.000000,0.000000,0.000000)\" visibility=\"visible\" /> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<rect id=\"A\" x=\"500.00\" y=\"650.00\" width=\"500.00\" height=\"500.00\""
            + " fill=\"rgb(0.000000,0.000000,0.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"B\" cx=\"10.00\" cy=\"160.00\" rx=\"10.00\" ry=\"10.00\""
            + " fill=\"rgb(10.000000,10.000000,10.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID3
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Three</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";

    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after removing a shape and then taking one snapshot.
   */
  @Test
  public void testSnapshotAfterRemove() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.removeShape("R");
    this.model.takeSnapshot("Take Two");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();

    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();

    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";
    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after removing all the shapes and then taking one snapshot.
   */
  @Test
  public void testSnapshotAfterRemoveToEmpty() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.removeShape("R");
    this.model.removeShape("O");
    this.model.takeSnapshot("Take Two");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();

    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();

    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";
    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after moving a shape and then taking one snapshot.
   */
  @Test
  public void testSnapshotAfterMove() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.moveShape("R", 200, 400);
    this.model.takeSnapshot("Take Two");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();

    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();
    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"200.00\" y=\"550.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";

    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after changing the color of a shape and then taking one snapshot.
   */
  @Test
  public void testSnapshotAfterColorChange() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.changeColor("R", 0, 0, 0);
    this.model.takeSnapshot("Take Two");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();

    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();
    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(0.000000,0.000000,0.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";

    assertEquals(expected, view.getOutput());
  }

  /**
   * Test output after resizing a shape and then taking one snapshot.
   */
  @Test
  public void testSnapshotAfterResize() {
    this.model.addShape("R", "rectangle", 100, 200, 100, 100, 200, 100, 10);
    this.model.addShape("O", "oval", 200, 100, 300, 30, 100, 100, 200);
    this.model.takeSnapshot("Take One");
    this.model.resizeShape("R", 500, 500);
    this.model.takeSnapshot("Take Two");

    String snapshotID1 = this.model.getSnapshotList().get(0).getSnapshotID();
    String snapshotID2 = this.model.getSnapshotList().get(1).getSnapshotID();

    WebView view = new WebView(1000, 1000);
    view.setSnapshot(this.model.getSnapshotList());
    view.displaySnapshot();
    String expected = "<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"100.00\" height=\"100.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID1
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take One</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\"style "
            + "=\"border: solid 5px red; background-color:rgb(173,216,230)\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<g><rect id=\"R\" x=\"100.00\" y=\"350.00\" width=\"500.00\" height=\"500.00\""
            + " fill=\"rgb(200.000000,100.000000,10.000000)\" visibility=\"visible\" /> \n"
            + "<ellipse id=\"O\" cx=\"200.00\" cy=\"250.00\" rx=\"300.00\" ry=\"30.00\""
            + " fill=\"rgb(100.000000,100.000000,200.000000)\" visibility=\"visible\"/> \n"
            + "<text x=\"10\" y=\"50\" font-weight=\"bold\" font-size=\"28\">" + snapshotID2
            + "</text>\n" + "<text x=\"10\" y=\"100\" font-size=\"18\">Description: "
            + "Take Two</text>\n" + "</g></svg>\n" + "<p></p>\n"
            + "</body>\n" + "</html>";

    assertEquals(expected, view.getOutput());
  }

}
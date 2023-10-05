import controller.FileParser;
import controller.GraphicalViewController;
import controller.PhotoAlbumBuilderImp;
import controller.WebViewController;
import model.ShapePhotoAlbum;
import model.ShapePhotoAlbumModel;
import view.GraphicalView;
import view.WebView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * This main class is used as a smoke test and generate buildingsOut.html file.
 */
public class Main {
  /**
   * Test if the output of both graphical view and web view are as expected.
   */
  public static void main(String[] args) {

    File file = new File("src/demo_input.txt");
    ShapePhotoAlbum model = new ShapePhotoAlbumModel();
    try {
      model = FileParser.parseFile(new BufferedReader(
              new FileReader(file)), new PhotoAlbumBuilderImp());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }


    GraphicalView graphicalView = new GraphicalView(1000, 1000);
    GraphicalViewController graphicalViewController =
            new GraphicalViewController(model, graphicalView);
    graphicalViewController.go();

    WebView webView = new WebView(1000, 1000);
    WebViewController webViewController =
            new WebViewController(model, webView, "demo_inputOut.html");
    webViewController.go();

  }
}


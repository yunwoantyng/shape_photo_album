import controller.FileParser;
import controller.GraphicalViewController;
import controller.IViewController;
import controller.PhotoAlbumBuilderImp;
import controller.WebViewController;
import model.ShapePhotoAlbum;
import model.ShapePhotoAlbumModel;
import view.GraphicalView;
import view.WebView;

import java.io.FileNotFoundException;
import java.io.StringReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * PhotoAlbumMain class is the entry-point for the application. It should be brief, handling most of
 * the control over to the MVC triad.
 */
public class PhotoAlbumMain {

  /**
   * The entry point for the program which could take inputs as command-line arguments.
   * @param args String[] representing the command-line arguments.
   *             The command line should be as the following: (in one line)
   *             java My Program -in name-of-command-file -view type-of-view
   *             [-out where-output-should-go] [xmax] [ymax]"
   *             (The arguments delimited by [] are optional.)
   */
  public static void main(String[] args) {
    if (args.length < 4) {
      errorMessage("Command line: java My Program -in name-of-command-file -view type-of-view"
              + "[-out where-output-should-go] [xmax] [ymax]");
    }
    ShapePhotoAlbum model = new ShapePhotoAlbumModel();
    String viewType = "";
    String output = "";
    Readable in = new StringReader("");
    int width = 1000;
    int height = 1000;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          try {
            in = new java.io.FileReader(args[++i]);
          } catch (FileNotFoundException e) {
            errorMessage("Error: File not found");
            return;
          } catch (IndexOutOfBoundsException e) {
            errorMessage("Error: File name not found");
            return;
          }
          break;
        case "-v":
        case "-view":
          try {
            viewType = args[++i];
          } catch (IndexOutOfBoundsException e) {
            errorMessage("Error: View type not found");
            return;
          }
          break;
        case "-out":
          try {
            output = args[++i];
          } catch (IndexOutOfBoundsException e) {
            errorMessage("Error: Output file not found");
            return;
          }
          break;
        default:
          try {
            width = Integer.parseInt(args[i]);
            height = Integer.parseInt(args[i + 1]);
            i++;
          } catch (Exception ignored) {
          }
          break;
      }
    }

    try {
      model = FileParser.parseFile(in, new PhotoAlbumBuilderImp());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JFrame(),
              "Fail to process the file in the Photo Album System");
      System.exit(0);
    }

    switch (viewType) {
      case "web":
        WebView webView = new WebView(width, height);
        IViewController webViewController = new WebViewController(model, webView, output);
        webViewController.go();
        break;
      case "graphical":
        GraphicalView view = new GraphicalView(width, height);
        GraphicalViewController controller = new GraphicalViewController(model, view);
        controller.go();
        break;
      default:
        errorMessage("Error: View type invalid");
        break;
    }
  }

  private static void errorMessage(String message) {
    ImageIcon icon = new ImageIcon("src/logo.png");
    JOptionPane.showMessageDialog(null, message,
            "Error Message", JOptionPane.PLAIN_MESSAGE, icon);
    System.exit(1);
  }
}



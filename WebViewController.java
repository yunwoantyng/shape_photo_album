package controller;

import model.ShapePhotoAlbum;
import model.Snapshot;
import view.WebView;

import java.util.List;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * WebViewController class implementing IViewController interface help sending the data from the
 * model to the web view to display.
 */

public class WebViewController implements IViewController {
  private ShapePhotoAlbum model;
  private WebView webView;
  private List<Snapshot> snapshotList;
  private String filePath;

  /**
   * The constructor for WebViewController class.
   * @param model A ShapePhotoAlbum object.
   * @param webView A WebView object.
   * @param filePath A String representing the path of the file.
   */
  public WebViewController(ShapePhotoAlbum model, WebView webView, String filePath) {
    this.model = model;
    this.webView = webView;
    this.filePath = filePath;
  }

  @Override
  public void go() {
    try {
      this.snapshotList = model.getSnapshotList();
      this.webView.setSnapshot(this.snapshotList);
      this.webView.displaySnapshot();
      this.webView.saveSvg(this.filePath);

    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }
}

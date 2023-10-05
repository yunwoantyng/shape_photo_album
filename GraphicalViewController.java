package controller;

import model.ShapePhotoAlbum;
import model.Snapshot;
import view.GraphicalView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * GraphicalViewController class implementing IViewController and ActionListener interface is
 * used to manipulate the model and handle inputs from the graphical view.
 */
public class GraphicalViewController implements IViewController, ActionListener {
  private ShapePhotoAlbum model;
  private GraphicalView graphicalView;
  private int index;
  private List<Snapshot> snapshotList;
  private List<String> snapshotIDlist;

  /**
   * The constructor for GraphicalViewController class.
   * @param model A ShapePhotoAlbum object.
   * @param graphicalView A GraphicalView object.
   */
  public GraphicalViewController(ShapePhotoAlbum model, GraphicalView graphicalView) {
    this.model = model;
    this.graphicalView = graphicalView;
    this.graphicalView.setAction(this);
    this.index = 0;
  }

  @Override
  public void go() {
    try {
      this.snapshotList = model.getSnapshotList();
      this.snapshotIDlist = model.getSnapshotIDlist();
      this.graphicalView.setSnapshot(this.snapshotList);
      this.graphicalView.setSnapshotID(this.snapshotIDlist);
      this.graphicalView.displaySnapshot();
      this.graphicalView.setVisible(true);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "select" -> {
        int answer = this.graphicalView.getUserOption();
        if (answer < 0) {
          break;
        }
        this.index = answer;
        this.graphicalView.setCurrentIndex(this.index);
        this.graphicalView.displaySnapshot();
        this.graphicalView.setVisible(true);
      }
      case "prev" -> {
        this.index -= 1;
        if (this.index < 0) {
          ImageIcon icon = new ImageIcon("src/logo1.png");
          JOptionPane.showMessageDialog(this.graphicalView,
                  "Sorry, Bro! There is no previous picture.",
                  "Message", JOptionPane.PLAIN_MESSAGE, icon);
          this.index = 0;
          break;
        }
        this.graphicalView.setCurrentIndex(this.index);
        this.graphicalView.displaySnapshot();
        this.graphicalView.setVisible(true);
      }
      case "next" -> {
        this.index += 1;
        if (this.index >= this.snapshotList.size()) {
          ImageIcon icon = new ImageIcon("src/logo2.png");
          JOptionPane.showMessageDialog(this.graphicalView,
                  "Hey, you have reached the end of the photo album.",
                  "Message", JOptionPane.PLAIN_MESSAGE, icon);
          this.index -= 1;
          break;
        }
        this.graphicalView.setCurrentIndex(this.index);
        this.graphicalView.displaySnapshot();
        this.graphicalView.setVisible(true);
      }
      case "quit" -> System.exit(0);
    }
  }
}

package view;

import model.Snapshot;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * GraphicalView class implementing IView interface extends JFrame which is a GUI window to add
 * components to. It is an interactive view for the Shape Photo Album application which displays
 * one snapshot at a time inside a window.
 */

public class GraphicalView extends JFrame implements IView {
  private DrawSnapshots drawSnapshots;
  private JPanel topBar;
  private JLabel snapLabel;
  private List<Snapshot> snapshotList;
  private List<String> snapshotIDlist;
  private String[] snapshotIDarray;
  private int index;
  private Button button;
  private static final int SIZE = 1000;

  /**
   * The constructor for GraphicalView class.
   * @param width An integer representing the width of the view window.
   * @param height An integer representing the height of the view window.
   */
  public GraphicalView(int width, int height) {
    super();
    if (width < 0 && height > 0) {
      width = SIZE;
    } else if (width > 0 && height < 0) {
      height = SIZE;
    } else if (width < 0 && height < 0) {
      width = SIZE;
      height = SIZE;
    }

    this.setSize(width, height);
    this.index = 0;
    this.setTitle("CS5004 Shapes Photo Album Viewer");
    this.setLayout(new BorderLayout());
    this.getContentPane().setBackground(Color.orange);

    this.drawSnapshots = new DrawSnapshots();
    setPreferredSize(new Dimension(width, height - 200));

    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // exit out of application

    this.button = new Button();
    this.add(this.button, BorderLayout.SOUTH);

    this.topBar = new JPanel(new BorderLayout());
    this.topBar.setBackground(Color.PINK);
    this.snapLabel = new JLabel();

    this.pack();
  }

  @Override
  public void displaySnapshot() {
    // draw the current shapes that correspond to the snapshot
    drawSnapshots.drawShapes(snapshotList.get(index).getShapelist());
    this.add(drawSnapshots, BorderLayout.CENTER);
    // set up the label that contains the snapshot ID and description
    this.snapLabel.setText("<html><body>" + snapshotList.get(index).getSnapshotID() + "<br/>"
            + snapshotList.get(index).getDescription() + "<body></html>");
    this.topBar.add(snapLabel, BorderLayout.LINE_START);
    this.add(this.topBar, BorderLayout.NORTH);
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
   * Set up the snapshot ID list.
   * @param snapshotIDlist A {@code List<String>} storing the current snapshot IDs.
   * @throws IllegalArgumentException if the snapshot ID list is null, en error will be thrown.
   */
  public void setSnapshotID(List<String> snapshotIDlist) throws IllegalArgumentException {
    if (snapshotIDlist == null) {
      throw new IllegalArgumentException("The snapshot list cannot be null.");
    }
    this.snapshotIDlist = snapshotIDlist;
    // convert the List<String> to String[]
    this.snapshotIDarray = this.snapshotIDlist.toArray(new String[0]);
  }

  /**
   * Update the current index. (The index will change once the button gets clicked.)
   * @param index An integer representing the current position in a list.
   */
  public void setCurrentIndex(int index) {
    this.index = index;
  }

  /**
   * Set up the actions for the buttons.
   * @param listener An ActionListener object.
   */
  public void setAction(ActionListener listener) {
    this.button.setListener(listener);
  }

  /**
   * Get the user option of snapshots.
   * @return An integer representing the current position in a list.
   */
  public int getUserOption() {
    String s = (String)JOptionPane.showInputDialog(null, "Choose", "Menu",
            JOptionPane.PLAIN_MESSAGE, null, snapshotIDarray, snapshotIDarray[0]);
    return this.snapshotIDlist.indexOf(s);
  }
}

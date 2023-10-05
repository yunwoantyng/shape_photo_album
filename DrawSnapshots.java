package view;

import model.IShape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * DrawSnapshots class extends JPanel which is a GUI component that functions as a container to hold
 * other components. This class is used to draw a single snapshot on the graphical view.
 */
public class DrawSnapshots extends JPanel {
  private List<IShape> shapeList;

  /**
   * The constructor for DrawSnapshots class.
   */
  public DrawSnapshots() {
    super();
    this.setBackground(new Color(30, 150, 200)); //set the background color to light blue
  }

  /**
   * Draw the given shapes on the graphical view.
   * @param shapeList The corresponding shape list of a snapshot.
   */
  public void drawShapes(List<IShape> shapeList) {
    this.shapeList = shapeList;
    this.repaint(); // update a pane with a new snapshot
  }

  /**
   * Define how to draw a rectangle and an oval.
   * @param g the <code>Graphics</code> object to protect
   */

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphics2D = (Graphics2D) g;

    if (this.shapeList == null) {
      return;
    }

    for (IShape shape: this.shapeList) {
      graphics2D.setColor(new Color((int) shape.getColor().getRed(),
              (int) shape.getColor().getGreen(), (int) shape.getColor().getBlue()));
      switch (shape.getShapeType()) {
        case ("rectangle") -> graphics2D.fillRect((int) shape.getPosition().getX(),
                (int) shape.getPosition().getY(), (int) shape.getSize()[0],
                (int) shape.getSize()[1]);
        case ("oval") -> graphics2D.fillOval((int) shape.getPosition().getX(),
                (int) shape.getPosition().getY(), (int) shape.getSize()[0],
                (int) shape.getSize()[1]);
        default -> {
        }
      }
    }
  }
}

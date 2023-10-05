package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * CS5004 Homework09 Wan-Ting Yun
 * Button class extending JPanel is used to display the buttons on the graphical view window.
 */
public class Button extends JPanel {
  private JButton prev;
  private JButton select;
  private JButton next;
  private JButton quit;

  /**
   * The Constructor for Button class.
   */
  public Button() {
    this.setBackground(Color.ORANGE);
    this.prev = new JButton("<< Prev <<");
    prev.setActionCommand("prev");

    this.select = new JButton("^^ Select ^^");
    select.setActionCommand("select");

    this.next = new JButton(">> Next >>");
    next.setActionCommand("next");

    this.quit = new JButton("xx Quit xx");
    quit.setActionCommand("quit");

    this.add(prev);
    this.add(select);
    this.add(next);
    this.add(quit);
  }

  /**
   * Add ActionListener to the buttons so that once they get clicked, the controller can react.
   * @param listener An ActionListener object.
   */
  public void setListener(ActionListener listener) {
    this.prev.addActionListener(listener);
    this.select.addActionListener(listener);
    this.next.addActionListener(listener);
    this.quit.addActionListener(listener);
  }
}
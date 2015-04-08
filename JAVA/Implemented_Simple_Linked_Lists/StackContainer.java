import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
/*
 *This class is for the object of the stack to be put on the railcars
 */
public class StackContainer {
  public static final int STARTING_X = 575;
  public static final int STARTING_Y = 350;
  public static final int BOX_WIDTH = 25;
  public static final int BOX_HEIGHT = 25;
  private int x,y;
  private String letter;
  private Rectangle box;
  //check if the stack is currently being used by a railcar
  private boolean used;
  /*
   *Constructor to initialize values to default
   */
  public StackContainer() {
    x = STARTING_X;
    y = STARTING_Y;
    letter = "A";
    box = new Rectangle(x, y, BOX_WIDTH, BOX_HEIGHT);
    used = false;
  }
  /*
   *Constructor to initialize values to specific values defined in the parameters
   * @param x The x value
   * @param y The y value
   * @param letter The number of the railCar
   */
  public StackContainer(int x, int y, String letter) {
    this.x = x;
    this.y = y;
    this.letter = letter;
    box = new Rectangle(x, y, BOX_WIDTH, BOX_HEIGHT);
    used = false;
  }
  /*
   *Getters/Setters for the values defined above
   */
  public int getX() { return x; }
  public int getY() { return y; }
  public Rectangle getBox() { return box; }
  public void setX(int x) { this.x = x; box.setLocation(x,y);}
  public void setY(int y) { this.y = y; box.setLocation(x,y);}
  public boolean isUsed() { return used;}
  public void setUsed(boolean u) { used = u; }
  /*
   *Method used to draw the stack
   * @param g2 The graphics passed in, used to draw.
   */
  public void draw(Graphics2D g2) {
    if(!used)
      g2.setColor(Color.GREEN);
    else
      g2.setColor(Color.RED);
    g2.draw(box);
    g2.setColor(Color.BLACK);
    g2.drawString(letter, x+BOX_WIDTH/3, y+BOX_HEIGHT-BOX_HEIGHT/4) ;
  }
}
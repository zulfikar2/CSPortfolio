import java.awt.* ;
import java.awt.geom.* ;

/**
 This class describes a vehicle that looks like a flatbed 
 railcar.  The railcar should be assigned a unique number 
 displayed on its body. The railcar should have variable and 
 methods to allow it to be linked to another vehicle (consider
 whether this variable and associated methods should be
 inherited). This railcar should also have variables and
 methods so that a storage container can be loaded and unloaded
 Add other variables and methods you think are necessary.
 */

public class RailCar implements Vehicle
{
  int x,y,n;
  public Vehicle next;
  private Noder node;
  private StackContainer stack;
  public static boolean isLead;
  public static final int UNIT = 10 ;
  public static final int U6 = 6 * UNIT ;
  public static final int U5 = 5 * UNIT ;
  public static final int U4 = 4 * UNIT ;
  public static final int U3 = 3 * UNIT ;
  public static final int U2 = 2 * UNIT ;
  public static final int U15 = UNIT + UNIT / 2 ;
  public static final int U05 =  UNIT / 2 ;
  public static final int BODY_WIDTH = U3 ;
  public static final int BODY_HEIGHT = U2 ;
  private static final int STARTING_X = 75;
  private static final int STARTING_Y = 75;
  private Rectangle box;
  private Color col;
  
  public RailCar(int n) {
    x = STARTING_X;
    y = STARTING_Y;
    this.n = n;
    box = new Rectangle(x,y,BODY_WIDTH*2, BODY_HEIGHT + UNIT);
    isLead = true;
    node = new Noder();
    node.add(this, true);
    stack = null;
  }
  
  public RailCar(int n, int x, int y, boolean lead) {
    this.x = x;
    this.y = y;
    this.n = n;
    box = new Rectangle(x,y,BODY_WIDTH*2, BODY_HEIGHT + UNIT);
    isLead = lead;
    node = new Noder();
    node.add(this, true);
    stack = null;
  }  
  /**
   Draw the rail car
   @param g2 the graphics context
   */
  public void draw(Graphics2D g2)
  {
    
    int xLeft = getX() ;
    int yTop = getY() ;
    
    Rectangle2D.Double body 
      = new Rectangle2D.Double(getX(), yTop + UNIT, U6, UNIT);      
    Ellipse2D.Double frontTire 
      = new Ellipse2D.Double(getX() + UNIT, yTop + U2, UNIT, UNIT);
    Ellipse2D.Double rearTire
      = new Ellipse2D.Double(getX() + U4, yTop + U2, UNIT, UNIT);
    
    // the bottom of the front windshield
    Point2D.Double r1 
      = new Point2D.Double(getX() + UNIT, yTop + UNIT);
    // the front of the roof
    Point2D.Double r2 
      = new Point2D.Double(getX() + U2, yTop);
    // the rear of the roof
    Point2D.Double r3 
      = new Point2D.Double(getX() + U4, yTop);
    // the bottom of the rear windshield
    Point2D.Double r4 
      = new Point2D.Double(getX() + U5, yTop + UNIT);
    
    // the right end of the hitch
    Point2D.Double r5 
      = new Point2D.Double(getX() + U6, yTop + U15);
    // the left end of the hitch
    Point2D.Double r6 
      = new Point2D.Double(getX() + U6 + U05, yTop + U15);
    
    Line2D.Double frontWindshield 
      = new Line2D.Double(r1, r2);
    Line2D.Double roofTop 
      = new Line2D.Double(r2, r3);
    Line2D.Double rearWindshield
      = new Line2D.Double(r3, r4);
    Line2D.Double hitch
      = new Line2D.Double(r5, r6);
    
    g2.setColor(col);
    g2.draw(body);
    g2.draw(hitch);
    g2.draw(frontTire);
    g2.draw(rearTire);
    g2.draw(body) ;
    g2.drawString("" + getNumber(), getX() + U2, yTop + U2) ;
    //recursive drawing
    if(next != null && next != this) {
      next.draw(g2);
    }
    
    //g2.draw(box);
  }
  /*
   *Method to get the x value of railCar
   * @return x value
   */
  public int getX() {
    return x;
  }
  /*
   *Method to get the y value of railCar
   * @return y value
   */
  public int getY() {
    return y;
  }
  /*
   *Method to set the x value of railCar
   * @param x the value to set it to
   */
  public void setX(int x) {
    this.x = x;
    box.setLocation(x,y);
  }
  /*
   *Method to set the y value of railCar
   * @param y the value to set it to
   */
  public void setY(int y) {
    this.y = y;
    box.setLocation(x,y);
  }
  /*
   *Method to get the number of the railcar
   * @return number of railCar
   */
  public int getNumber() {
    return n;
  }
  /*
   *Method to get the bounding box of the railCar
   * @return rectangle bounding box
   */
  public Rectangle getBoundingBox(){
    return box;
  }
  /*
   *Method to check if the railCar is a leading car
   * @return boolean which is true or false depending on if its leading
   */
  public boolean isLead() {
    return isLead;
  }
  /*
   *Method to set the lead status of the raiLCar
   * @param lead the boolean to set lead status to
   */
  public void setLead(boolean lead) {
    isLead = lead;
  }
  /*
   *Method to set the Color of the railCar
   * @param col Color to set it to
   */
  public void setCol(Color col) {
    this.col = col;
  }
  /*
   *Method to set the next vehicle in the sequence to
   * @param next the Vehicle next in sequence
   */
  public void setNext(Vehicle next) {
    this.next = next;
  }
  /*
   *Method to get the next vehicle
   * @return next vehicle
   */
  public Vehicle getNext() {
    return next;
  }
  /*
   *Method to get the next node in sequence
   * @return node
   */
  public Noder getNode() {
    return node;
  }
  /*
   *Method to deselect the railcar(Sets its color back to black)
   */
  public void deSelect() {
    col = Color.BLACK;
  }
  /*
   *Method to select the railCar(sets its color to red)
   */
  public void select() {
    col = Color.RED;
  }
  /*
   *Method to get the container on railcar
   * @return container
   */
  public StackContainer getStack() {
    return stack;
  }
  /*
   *method to set the container on the railCar
   * @param s the StackContainer to add
   */
  public void setStack(StackContainer s) {
    stack = s;
    if(s!=null) {
      stack.setX(x + 18);
      stack.setY(y - 15);
    }
  }
}
import java.awt.geom.* ;
import java.awt.* ;

/**
 Train Engine is a vehicle that can pull a chain of railcars
 */
public class TrainEngine implements Vehicle
{
  int x, y;
  /**
   Constants
   */
  private Vehicle next;
  private Noder node;
  private StackContainer stack;
  private static boolean isLead;
  private static final double WIDTH = 35 ;
  private static final double UNIT = WIDTH / 5 ;
  private static final double LENGTH_FACTOR = 14 ; // length is 14U
  private static final double HEIGHT_FACTOR = 5 ; // height is 5U
  private static final double U_3 = 0.3 * UNIT ; 
  private static final double U2_5 = 2.5 * UNIT ; 
  private static final double U3 = 3 * UNIT ; 
  private static final double U4 = 4 * UNIT ; 
  private static final double U5 = 5 * UNIT ; 
  private static final double U10 = 10 * UNIT ; 
  private static final double U10_7 = 10.7 * UNIT ; 
  private static final double U12 = 12 * UNIT ; 
  private static final double U13 = 13 * UNIT ; 
  private static final double U14 = 14 * UNIT ; 
  private static final int STARTING_X = 75;
  private static final int STARTING_Y = 75;
  private Rectangle box;
  private Color col;
  /*
   *Constructor for the trainEngine. Initializes values defined above
   */
  public TrainEngine() {
    isLead = true;
    x = STARTING_X;
    y = STARTING_Y;
    box = new Rectangle(x,y, (int)LENGTH_FACTOR*(int)UNIT - (int)UNIT, (int)HEIGHT_FACTOR*(int)UNIT);
    next = null;
    node = new Noder();
    node.add(this, true);
    stack = null;
  }
  /*
   *Constructor for the trainEngine. Initializes values to specific values as passed in
   * @param x
   * @param y
   * @param lead The boolean value to check if this is a leading car or not
   */
  public TrainEngine(int x, int y, boolean lead) {
    isLead = lead;
    this.x = x;
    this.y = y;
    box = new Rectangle(x,y, (int)LENGTH_FACTOR*(int)UNIT-(int)UNIT , (int)HEIGHT_FACTOR*(int)UNIT);
    next = null;
    node = new Noder();
    node.add(this, true);
    stack = null;
  }
  /**
   Draws the train engine
   @param g2 the graphics context
   */
  
  public void draw(Graphics2D g2)
  {
    // decide whether to implement getX() and getY() in this
    // class or in superclass
    int x1 = getX() ;
    int y1 = getY() ;
    Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
                                                     U3, U3 ) ;
    g2.setColor(Color.blue) ;
    g2.fill(hood) ;
    
    Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
                                                     y1,
                                                     U10, U4) ;
    g2.setColor(Color.blue) ;
    g2.fill(body) ;
    
    Line2D.Double hitch = new Line2D.Double(x1 + U13,
                                            y1 + U2_5,
                                            x1 + U14, 
                                            y1 + U2_5) ;
    g2.setColor(Color.black) ;
    g2.draw(hitch) ;
    
    Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
                                                   y1 + U4, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel1) ;
    
    Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT, 
                                                   y1 + U4, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel2) ;
    
    Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT, 
                                                   y1 + 4 * UNIT, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel3) ;
    
    Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
                                                   y1 + U4, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel4) ;
    
    Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
                                                   y1 + U4, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel5) ;
    
    Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT, 
                                                   y1 + U4, 
                                                   UNIT, UNIT) ;
    g2.setColor(col) ;
    g2.fill(wheel6) ;
    
    if(isLead() && next != null) {
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
    return 0;
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

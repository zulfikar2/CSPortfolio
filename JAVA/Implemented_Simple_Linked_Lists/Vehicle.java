import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
/*
 *This class is an abstract class for the train engine and its railcars
 * It contains methods and variables in common
 */
public interface Vehicle {
  boolean isLead = false;
  int getX();
  int getY();
  void setX(int x);
  void setY(int y);
  void draw(Graphics2D g2);
  boolean isLead();
  public void setLead(boolean lead);
  int getNumber();
  Rectangle getBoundingBox();
  void setCol(Color col);
  public void setNext(Vehicle next);
  public Vehicle getNext();
  public Noder getNode();
  public void deSelect();
  public void select();
  public StackContainer getStack();
  public void setStack(StackContainer s);
}
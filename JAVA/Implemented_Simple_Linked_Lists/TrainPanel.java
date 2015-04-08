import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
/*
 *Panel class which controls the logic of the simulation
 */
public class TrainPanel extends JPanel{
  
  static boolean simStart;
  final int NUMBER_CARS = 3;
  private static int count;
  private static Vehicle selectedTrain;
  private static ArrayList<Vehicle> train;
  private static ArrayList<StackContainer> stack;
  /*
   *Constructor for the panel
   * Initializes the arraylists holding the train and stack
   * Starts the timer which repaints the window
   */
  public TrainPanel() {
    train = new ArrayList<Vehicle>();
    stack = new ArrayList<StackContainer>();
    simStart = false;
    repaintWin();
    count = 0;
    /*
     *Inner class which controls mouse clicks
     */
    class MyListener extends MouseAdapter {
      //Listener class for the panel
      /*
       *Selects the car clicked as well as spawns the train parts when first clicked
       */
      public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
          if(simStart) {
            //Left Button
            count++;
            if(count == 1)
              train.add(new TrainEngine(e.getX(), e.getY(), true));
            if(count > 1 && count < 7) {
              RailCar r = new RailCar(count-1, e.getX(), e.getY(), true);
              Boolean possible = false;
              for(Vehicle v : train) {
                if(v.getBoundingBox().intersects(r.getBoundingBox()) || v.getBoundingBox().contains(r.getBoundingBox())) {
                  possible = false; break;}
                else
                  possible = true;
              }
              if(possible)
                train.add(r);
              else
                count--;
            }
            if (count==7) {
              while(count < 12) {
                String letter = "";
                switch(count) {
                  case 7:
                    letter = "E";
                    break;
                  case 8:
                    letter = "D";
                    break;
                  case 9:
                    letter = "C";
                    break;
                  case 10:
                    letter = "B";
                    break;
                  case 11:
                    letter = "A";
                    break;
                  default:
                    letter = "broken";
                    break;
                }
                stack.add(new StackContainer(StackContainer.STARTING_X, StackContainer.STARTING_Y - (count-6)*StackContainer.BOX_HEIGHT, letter));
                count++;
              }
            }
            //if everything is spawned, then select
            if(count > 12) {
              for(Vehicle t : train) {
                if(!t.getBoundingBox().contains(e.getX(), e.getY())) {
                  t.deSelect();
                }
                else {
                  if(t.isLead()) {
                    t.select();
                    selectedTrain = t;
                  }
                }
              }
              repaint();
            }
          }
        }
      }
      /*
       *Method to control the mouse drag
       * Move the train currently being selected as well as its linked cars and stacks
       */
      public void mouseDragged(MouseEvent e) {
        if(simStart && count > 7) {
          for(Vehicle t : train) {
            if(t.getBoundingBox().contains(e.getX(), e.getY()) && t.isLead()) {
              t.setX(e.getX()-25);
              t.setY(e.getY()-10);
              t.getBoundingBox().setLocation(e.getX()-25, e.getY()-10);
              break;
            }
          }
          for(StackContainer s : stack) {
            if(s.getBox().contains(e.getX(), e.getY()) && !s.isUsed()) {
              s.setX(e.getX()-10);
              s.setY(e.getY()-10);
              break;
            }
          }
        }
      }
      /*
       *Method to control the mouse release
       * Links cars which intersects
       */
      public void mouseReleased(MouseEvent e) {
        if(simStart && count > 7) {
          for(Vehicle t : train) {
            if(selectedTrain != null && selectedTrain != t && selectedTrain != train.get(0) && selectedTrain.isLead()) {
              if(t.getBoundingBox().intersects(selectedTrain.getBoundingBox()) 
                   || t.getBoundingBox().contains(selectedTrain.getBoundingBox())) {
                Vehicle temp = t;
                Boolean exists = false;
                if(temp.getNext() != null) {
                  while(temp.getNext() != null) {
                    temp = temp.getNext();
                    if(temp == selectedTrain)
                      exists = true;
                  }
                }
                if(!exists) {
                  temp.setNext(selectedTrain);
                  selectedTrain.deSelect();
                  break;
                }
              }
            }
          }
        }
      }
    }
    //adds the actionslisteners to the panel
    MouseAdapter mList = new MyListener();
    addMouseListener(mList);
    addMouseMotionListener(mList);
  }
  /*
   *Method to start/stop simulation
   */
  public static void startSim() {
    System.out.println("Sim start!");
    if(simStart == false)
      simStart = true;
    else {
      simStart = false;
      count = 0;
    }
  }
  /*
   *Method to add to the start of the linked list
   */
  public static void addFirst() {
    Boolean exists = false;
    Vehicle temp = train.get(0);
    if(selectedTrain != train.get(0) && selectedTrain != null) {
      Vehicle guy = train.get(0).getNext();
      train.get(0).setNext(selectedTrain);
      selectedTrain.setNext(guy);
      selectedTrain.deSelect();
    }
  }
  /*
   *Method to add to the end of the linked list
   */
  public static void addLast() {
    Boolean exists = false;
    Vehicle temp = train.get(0);
    if(selectedTrain != train.get(0) && selectedTrain != null) {
      selectedTrain.setLead(false);
      if(temp.getNext() != null) {
        while(temp.getNext() != null) {
          temp = temp.getNext();
          if(temp == selectedTrain)
            exists = true;
        }
        //if the car is not in the list already, add it and deselect it
        if(!exists) {
          temp.setNext(selectedTrain);
          selectedTrain.deSelect();
          selectedTrain = null;
        }
      }
      else {
        train.get(0).setNext(selectedTrain);
        selectedTrain.deSelect();
        selectedTrain = null;
      }
    }
  }
  /*
   *Method to remove the first linked car
   */
  public static void remFirst() {
    if(train.get(0).getNext() != null) {
      Vehicle t = train.get(0).getNext();
      train.get(0).setNext(t.getNext());
      t.setX(200); t.setY(200);
      t.setNext(null);
      t.setLead(true);
    }
  }
  /*
   *Method to remove the last linked car
   */
  public static void remLast() {
    Vehicle temp = train.get(0);
    if(temp.getNext() != null) {
      while(temp.getNext() != null) {
        if(temp.getNext().getNext() != null)
          temp = temp.getNext();
        else
          break;
      }
      Vehicle t = temp.getNext();
      temp.setNext(null);
      t.setX(200); t.setY(200);
      t.setLead(true);
    }
  }
  /*
   *Method to add the stack to an available car
   */
  public static void pop() {
    Vehicle t = null;
    if(selectedTrain == null)
      t = train.get(0);
    else
      t = selectedTrain;
    while(t != null) {
      if(t == train.get(0) || t != selectedTrain) {
        if (t.getNext() != null && t.getStack() != null)
          t = t.getNext(); 
      }
      if(t.getStack() == null) {
        for( StackContainer st : stack) {
          if(!st.isUsed() && t != train.get(0)) {
            st.setUsed(true);
            t.setStack(st);
            break;
          }
        }
        break;
      }
      if(t.getNext() == null)
        break;
    }
  }
  /*
   *Method to remove the stack from selected railcar
   */
  public static void push() {
    Vehicle t = null;
    if(train.get(0).getNext() != null && selectedTrain == null)
      t = train.get(0).getNext();
    else
      t = selectedTrain;
    int counter = 1;
    if(t != null) {
      if(t.getNext() == null && t.getStack() != null) {
        t.getStack().setUsed(false);
        t.getStack().setX(t.getStack().STARTING_X);
        t.getStack().setY(t.getStack().STARTING_Y - counter*t.getStack().BOX_HEIGHT);
        t.setStack(null);
      }
    }
    while(t != null) {
      if(t.getStack() != null) {
        t.getStack().setUsed(false);
        t.getStack().setX(t.getStack().STARTING_X);
        t.getStack().setY(t.getStack().STARTING_Y - counter*t.getStack().BOX_HEIGHT);
        t.setStack(null);
        break;
      }
      t = t.getNext();
      counter++;
    }
  }
  /*
   *Method to continue refreshing the panel and control linked movement
   */
  public void repaintWin() {
    int repaintTime = 50;
    Timer timer = new Timer(repaintTime, new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        for(Vehicle t : train) {
          Vehicle temp = t;
          int pX = t.getX(), pY = t.getY();
          while(temp.getNext() != null) {
            if(t == train.get(0))
              pX += 99;
            else
              pX += 66;
            temp = temp.getNext();
            temp.setX(pX);
            temp.setY(pY);
            if(temp.getStack() != null) {
              if(temp.getStack().isUsed()) {
                temp.getStack().setX(pX + 18);
                temp.getStack().setY(pY - 15);
              }
            }
          }
        }
        if(selectedTrain != null) {
          if(selectedTrain.getNext() != null && selectedTrain.isLead()) {
            Vehicle temp = selectedTrain;
            while(temp.getNext() != null) {
              temp = temp.getNext();
              temp.select();
              //temp.setLead(false);
            }
          }
        }
        repaint();
      }
    });
    timer.setRepeats(true);
    timer.start();
  }
  
  /*
   *Main drawing of the panel
   * Draws all objects in the panel
   * @param g Graphics context
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    if(simStart) {
      for(Vehicle t : train) {
        if(t.isLead())
          t.draw(g2);
      }
      for(StackContainer st : stack) {
        st.draw(g2);
      }
      g2.fill(new Rectangle(550,350,70,25));
    }
    else {
      //if the sim has not started, clear the panel and arraylists of objects
      g.clearRect(0, 0, getWidth(), getHeight());
      train.clear();
      stack.clear();
    }
  }
}
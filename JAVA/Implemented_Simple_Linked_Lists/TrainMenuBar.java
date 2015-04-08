import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
/*
 *Class used for the menu bar addded to the frame
 */
public class TrainMenuBar extends JMenuBar{
  
  private int fontStyle;
  private int fontSize;
  /*
   *Constructor which adds the options of the menubar
   */
  public TrainMenuBar() {
    add(createFileMenu());
    add(createListMenu());
    add(createStackMenu());
    
    fontSize = 24;
    fontStyle = Font.PLAIN;
  }
  /**
   Creates the File menu.
   @return the menu
   */
  public JMenu createFileMenu()
  {
    JMenu menu = new JMenu("File");
    JMenuItem startNew = new JMenuItem("Start/Stop Sim");
    JMenuItem exitItem = new JMenuItem("Exit");      
    ActionListener listener = new ExitItemListener();
    ActionListener listener_start = new StartItemListener();
    exitItem.addActionListener(listener);
    startNew.addActionListener(listener_start);
    menu.add(startNew);
    menu.add(exitItem);
    return menu;
  }
  /*
   *ActionsListener for the exit option
   */
  class ExitItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      System.exit(0);
    }
  }      
  /*
   *ActionListener for the start/stop simulation option
   */
  class StartItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      System.out.println("Starting New Simulation");
      TrainPanel.startSim();
    }
  }
  /*
   *Method to create the list menu
   * @return menu created
   */
  public JMenu createListMenu()
  {
    JMenu menu = new JMenu("List");
    JMenuItem addFirst = new JMenuItem("Add First");
    JMenuItem addLast = new JMenuItem("Add Last");
    JMenuItem rFirst = new JMenuItem("Remove First");
    JMenuItem rLast = new JMenuItem("Remove Last");   
    ActionListener listener_first = new addFirstItemListener();
    ActionListener listener_last = new addLastItemListener();
    ActionListener listener_rfirst = new rFirstItemListener();
    ActionListener listener_rlast = new rLastItemListener();
    addFirst.addActionListener(listener_first);
    addLast.addActionListener(listener_last);
    rFirst.addActionListener(listener_rfirst);
    rLast.addActionListener(listener_rlast);
    menu.add(addFirst);
    menu.add(addLast);
    menu.add(rFirst);
    menu.add(rLast);
    return menu;
  }
  /*
   *Actionlistener for the add first option
   */
  class addFirstItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      System.out.println("Add First");
      TrainPanel.addFirst();
    }
  }
  /*
   *ActionListener for the add last option
   */
  class addLastItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      System.out.println("Add Last");
      TrainPanel.addLast();
    }
  }
  /*
   *ActionListener for the remove first option
   */
  class rFirstItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      TrainPanel.remFirst();
      System.out.println("Remove First");
    }
  }
  /*
   *ActionListener for the remove last option
   */
  class rLastItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      TrainPanel.remLast();
      System.out.println("Remove Last");
    }
  }
  /*
   *Method to create the stack menu to control the container
   * @return the menu created
   */
  public JMenu createStackMenu()
  {
    JMenu menu = new JMenu("Stack");
    JMenuItem stackPop = new JMenuItem("Pop");
    JMenuItem stackPush = new JMenuItem("Push");
    ActionListener listener_pop = new stackPopItemListener();
    ActionListener listener_push = new stackPushItemListener();
    stackPop.addActionListener(listener_pop);
    stackPush.addActionListener(listener_push);
    menu.add(stackPop);
    menu.add(stackPush);
    return menu;
  }  
  /*
   *ActionListener for the stack pop option
   */
  class stackPopItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      TrainPanel.pop();
      System.out.println("POP IT!");
    }
  }
  /*
   *ActionListener for the stack push option
   */
  class stackPushItemListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      TrainPanel.push();
      System.out.println("PUSH IT!");
    }
  }
}
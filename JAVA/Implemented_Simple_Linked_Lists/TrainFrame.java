import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;

/**
 This frame has a menu with commands to start
 a simulation of a train and its linked cars.
 */
public class TrainFrame extends JFrame
{
  private static final int FRAME_WIDTH = 700;
  private static final int FRAME_HEIGHT = 500; 
  /**
   Constructs the frame.
   */
  public TrainFrame()
  {
    //Construct panel
    JPanel panel = new TrainPanel();
    add(panel);
    //Construct menu
    JMenuBar menuBar = new TrainMenuBar();     
    setJMenuBar(menuBar);
    
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
  }
}
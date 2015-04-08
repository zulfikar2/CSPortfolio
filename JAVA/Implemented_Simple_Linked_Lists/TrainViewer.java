
import javax.swing.JFrame;

/*
 *This program controls a linked list with a train and containers as a stack
 */
public class TrainViewer
{  
  public static void main(String[] args)
  {  
    JFrame frame = new TrainFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("TrainViewer");
    frame.setVisible(true);      
  }
}
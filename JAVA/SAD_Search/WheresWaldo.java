import java.awt.*;
import java.io.*;

public class WheresWaldo {
  String type;
  /** 
  * Method to set calculate the absolute intensities of the pixels 2 pictures
  * @param pTemp the template of the picture to find
  * @param pScene the scene where to find the template in
  * @return a 2d array of the intensity of the SAD of the two pictures' intensities
  */ 
  public int[][] sadMatch(Picture pTemp, Picture pScene) {
    //creates variables to store the height and width of the template
    int hTemp = pTemp.getHeight();
    int wTemp = pTemp.getWidth();
    //creates a new 2d array with the size of the height and width of the template
    int[][] temp = new int[wTemp][hTemp];
    //creates variables to store the height and width of the scene
    int hScene = pScene.getHeight();
    int wScene = pScene.getWidth();
    //creates a new 2d array with the size of the height and width of the scene
    int[][] scene = new int[wScene][hScene];
    //creates a new 2d array with the size of the height and width of the scene
    //this array is used to store the intensities of the pixels
    int[][] search = new int[wScene][hScene];
    
    int sadSum;
    //checks if the picture is greyscale or color
    if (pTemp.getPixel(5,5).getRed() == pTemp.getPixel(5,5).getGreen()) {
      type = "Grey";
      //goes through the scene to find the red value intensities and stores it in a 2d array
      for (int x = 0; x < wScene; x++) {
        for (int y = 0; y < hScene; y++) {
          scene[x][y] = pScene.getPixel(x,y).getRed();
        }
      }
      //goes through the template to find the red value intensities and stores it in a 2d array
      for (int x = 0; x < wTemp; x++) {
        for (int y = 0; y < hTemp; y++) {
          temp[x][y] = pTemp.getPixel(x,y).getRed();
        }
      }
    }
    //if check fails, it determines the picture was colored
    else {
      type = "Col";
      //goes through the scene to find the rgb value intensities and stores it in an array
      for (int x = 0; x < wScene; x++) {
        for (int y = 0; y < hScene; y++) {
          scene[x][y] = (int)(pScene.getPixel(x,y).getRed()*0.2126 + pScene.getPixel(x,y).getGreen()*0.7152 + pScene.getPixel(x,y).getBlue()*0.0722);
        }
      }
      //goes through the template to find the rgb value intensities and stores it in an array
      for (int x = 0; x < wTemp; x++) {
        for (int y = 0; y < hTemp; y++) {
          temp[x][y] = (int)(pTemp.getPixel(x,y).getRed()*0.2126 + pTemp.getPixel(x,y).getGreen()*0.7152 + pTemp.getPixel(x,y).getBlue()*0.0722);
        }
      }
    }
    //goes through both 2d arrays previously initialized
    for (int x = 0; x < wScene; x++) {
      for (int y = 0; y < hScene; y++) {
        
        sadSum = 0;
        
        for (int a = 0; a < wTemp; a++) {
          for (int b = 0 ; b < hTemp; b++ ) {
            //makes the SAD value at the edges a high number as it would be undefined otherwise
            if (x+a >= wScene || y+b >= hScene) {
              sadSum = 100000;
              break;
            }
            //calculated SAD value at point
            sadSum+= Math.abs(temp[a][b] - scene[x+a][y+b]);
          }
          //skips if value is over threshold
          if (sadSum > 10000)
            break;
        }
        search[x][y] = sadSum;
      }
    }
    
    return search;
    
  }
  /** 
  * Method to find the lowest SAD value
  * @param search the double array which has the SAD intensities
  * @return a point where the lowest SAD value is seen
  */ 
  public Point find2DMin(int[][] search) {
    
    int min = 0, minX =0, minY = 0;
    //search through double array
    for (int x =0; x < search.length; x++) {
      //initialized the min value
      if (x == 0)
        min = search[0][0];
      
      for (int y = 0; y < search[0].length; y++) {
        //check if min is larger than the SAD
        if (min > search[x][y]) {
          //sets the min to the new value and sets the minX and minY to the point where it was found
          min = search[x][y];
          minX = x;
          minY = y;
          
        }
      }
    }
    Point z = new Point(minX,minY);
    //prints out the point where lowest SAD is found
    System.out.println("Waldo Pt for " + type + " " + minX + " " + minY);
    return z;
  }
  /** 
  * Method to draw the rectangle at the point where the lowest SAD value was found
  * @param search the picture to draw onto
  * @param match the point where the lowest SAD value was found
  */ 
  public void displayMatch(Picture search, Point match) {
    
   Graphics2D g2d = search.createGraphics();
   //sets thickeness of lines made and color
    g2d.setStroke(new BasicStroke(3));
    g2d.setColor(Color.WHITE);
    //eliminates magic numbers
    final int WALDO_TEMP_WIDTH = 57;
    final int WALDO_TEMP_HEIGHT = 110;
    //draws rectangle at point match
    g2d.drawRect((int)match.getX(), (int)match.getY(), WALDO_TEMP_WIDTH, WALDO_TEMP_HEIGHT);
    //writes modified file
    search.write("WaldoBoxed" + type + ".jpg");
    
  }
}
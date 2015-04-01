import java.awt.Point;
public class WheresWaldoTester {
  public static void main(String[] args) {
    
    //sets pictures for greyscale of template and scene
    Picture sGry = new Picture("waldoScene.png");
    Picture wGry = new Picture("waldo.png");
    //sets pictures for color of template and scene
    Picture sCol = new Picture("waldoSceneCol.png");
    Picture wCol = new Picture("waldoCol.png");
    
    //constructor
    WheresWaldo findHim = new WheresWaldo();
    //creates 2d array of intensitites
    int[][] sadMatch1 = findHim.sadMatch(wGry, sGry);
    //finds point where intensities match the most
    Point minMatch1 = findHim.find2DMin(sadMatch1);
    //draw a box at point
    findHim.displayMatch(sGry, minMatch1);
    
    //creates 2d array of intensitites for color
    int[][] sadMatch2 = findHim.sadMatch(wCol, sCol);
    //finds point where intensities match the most for color
    Point minMatch2 = findHim.find2DMin(sadMatch2);
    //draw a box at point for color
    findHim.displayMatch(sCol, minMatch2);
    
  }
}
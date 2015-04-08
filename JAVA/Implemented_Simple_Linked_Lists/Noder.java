/**
 * The class containing 
 * @author  Zulfikar Yusufali
 * @version 1.0, April 2015
 */
import java.util.* ;
/*
 *This class is for the node list
 */
public class Noder {
  
  private Node head,tail;
  private int size;
  private final boolean FIRST = true;
  /*
   *Inner class for each node
   */
  class Node {
    
    public Node next ;
    public Vehicle train;
    /*
     *Constructor for the inner node class
     * @param train the vehicle data for the node
     * @param next the next node in the sequence
     */
    public Node(Vehicle train, Node next)
    {
      this.train = train ;
      this.next = next ;
    }
  }
  /*
   *Constructor for the node list
   */
  public Noder(){
    
    head = tail = null ;
    size = 0 ;
  }
  /*
   * Method to remove a node from front or back depending on parameter
   * @param place the place to remove the node from
   * @return Vehicle returns the vehicle which was removed
   */
  public Vehicle remove(boolean place){
    
    if (size == 0) throw new NoSuchElementException() ;
    Vehicle rem = null;
    if (size == 1) {
      rem = head.train;
      head = tail = null;
    }
    if (place==FIRST) {
      if(head != null) {
        rem = head.train ;
        head = head.next ;
      }
    }
    else {
      if(tail != null) {
        Node guy = head;
        while(guy.next!=null){ 
          if(guy.next.next == null) {
            tail = guy;
          }
          guy = guy.next;
        }
        rem = guy.train;
      }
    }
    size -- ;
    return rem ;
  }
  /*
   *Method to look at the first vehicle in the train
   * @return Vehicle first in sequence
   */
  public Vehicle peek() {
    return head.train;
  }
  /*
   *Method to add a vehicle to the front or back
   * @param v The vehicle to be added
   * @param place the the vehicle is to be added(front or back)
   */
  public void add(Vehicle v, boolean place){
    if (v == null) throw new IllegalArgumentException() ;
    //if the place to add is to the front
    if(place==FIRST) {
      if(head==null)
        head = new Node(v,null);
      Node temp = new Node(v, null);
      temp.next = head;
      head = temp;
    }
    //if the place to add is the back
    else {
      Node guy = head;
      if(size() == 0) {
        head = new Node(v, null);
      }
      else {
        while(guy.next!=null) guy = guy.next;
        guy.next = new Node(v, null);
        tail = guy.next;
      }
    }
    size++;
  }
  /*
   *Method to return whether the node list is empty or not
   */
  public boolean isEmpty(){
    
    return size == 0 ;
  }
  /*
   *Method to return the size of nodes
   * @return the size of node(int)
   */
  public int size(){
    
    return size ;
  }
}
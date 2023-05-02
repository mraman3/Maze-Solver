import java.util.Scanner;
import java.io.File;
import java.util.Stack;
/*COSC 1P03
 *ASSIGNMENT #4
 *Student #: 6511679
 *
 *This class coordinates the loading and parsing of a 'maze' datafile, the creation of
 *a Maze object (which you'll be writing), and the invocation of the Maze's solve()
 *method (which you'll obviously also be writing).
 *It also handles basic IO and tells the Maze object to output the maze status and
 *solution (again, tasks you'll be implementing yourself).
 *
 *sample maze is included.
 *maze1.txt solves easily
 */

public class MazeSolver {
  char[][] charmap=null;
  int startRow=-1;//Starting row
  int startCol=-1;//Starting column
  //You can add extra stuff here if you like!
  
  public MazeSolver() {  
  }
  
  private void loadMaze() {
    int height,width;
    String filename;
    Scanner console=new Scanner(System.in);
    Scanner file;
    String temp;
    System.out.print("Enter maze filename: ");
    filename=console.nextLine();
    try {
      file=new Scanner(new File(filename));
      height=file.nextInt();
      width=file.nextInt();
      charmap=new char[height][width];
      file.nextLine();
      for (int i=0;i<height;i++) {
        temp=file.nextLine();
        charmap[i]=temp.toCharArray();
        if (temp.indexOf('S')>=0) {
          startRow=i;startCol=temp.indexOf('S');
          System.out.println("Start at "+startRow+","+startCol+".");
        }
      }
      
      System.out.println("File transcription complete!\n");
    }
    catch (Exception exn) {
      System.out.println("\nFile transcription problem. Exiting now.");
      System.exit(0);
    }
    
    solve();
  }
  
  private void solve() {
    boolean solved=false;
    System.out.println("Initial State:");
    printMap();
    if (recursiveSolve()) {
      System.out.println("\nFinal Maze:");
      printMap();
      System.out.print("Solution Path: ");
      //Display solution path here
      makeGps(startCol,startRow);
    }
    else System.out.println("Oops! No solution found!");  
  }
  
  //Put your recursive solution here.
  //Feel free to add a parameter if you'd like it to keep track of your solution path
  private boolean recursiveSolve() {
    MazePath location; 
    MazePath next;
    Stack<MazePath> spots = new Stack<MazePath>();
    if (solve(new MazePath(startCol, startRow))){ 
      return true; 
    }else{ 
      return false;
    }
  }
  public boolean solve(MazePath location) {
    //base case
    if (!inbound(location)) return false;
    if (isLast(location)) return true; 
    if (!isClear(location)) return false; 
    
    //current position must be clear
    assert(isClear(location)); 
    //first mark this  location 
    placeAstrisk(location, '*'); 
    
    //try to go south 
    if (solve(location.south())) {
      return true;  }
    //else west 
    if (solve(location.west())) {
      return true; 
    }    
    //else north 
    if (solve(location.north())) { 
      return true; 
    }
    //else east 
    if (solve(location.east())) { 
      return true; 
    }  
    //unmark all dead ends; since it was marked, the position must
    //have been clear
    placeAstrisk(location, ' '); 
    
    //if none of the above returned, then there is no solution
    return false;
  }
  
  //checks if the location is blank or has char in it 
  public boolean isClear(MazePath location) {
    int i = location.i();
    int j = location.j();
    assert(inbound(location)); 
    return (charmap[i][j] != 'X' && charmap[i][j]!= '#' && charmap[i][j] != '*');
  }
  
  //checks if the location is alwasy inside the boundaries of the maze
  public boolean inbound(MazePath location) {
    int i = location.i();
    int j = location.j();
    if (i >= 0 && i<charmap[0].length && j>= 0 && j<charmap.length) return true; 
    else return false;
  }
  
  //method that marks the Astrisk in the path that it is following 
  public char placeAstrisk(MazePath location, char value) {
    int i = location.i();
    int j = location.j();
    assert(inbound(location)); 
    char tmp = charmap[i][j];
    if (charmap[i][j] == 'S'){
      charmap[i][j] = '#';
    }else {
      charmap[i][j] = value;
    }
    return tmp;
  }
  
  //Method that checks if the location is the End char
  public boolean isLast(MazePath location){
    int i = location.i();
    int j = location.j();
    if (charmap[i][j] == 'E'){
      charmap[i][j] = 'O';
      return true;
    }else{
      return false;
    }
  }
  
  //Method to print the map
  private void printMap() {
    for (char[] row:charmap) {
      for (char c:row) System.out.print(c);
      System.out.println();
    }
  }
  
  //prints out the soulotion path to get to the exit 
  public void makeGps(int col, int row){
    int i =col;
    int j =row;
    for(;;){
      if (charmap[i-1][j] == '*'){
        i= i-1;
        charmap[i][j] = ' ';
        System.out.print('N'+", "); 
      }
      if (charmap[i][j+1] == '*'){
        j = j+1;
        charmap[i][j] = ' ';
        System.out.print('E'+", "); 
      }
      if (charmap[i+1][j] == '*'){
        i = i+1;
        charmap[i][j] = ' ';
        System.out.print('S'+", "); 
      }
      if (charmap[i][j-1] == '*'){
        j = j-1;
        charmap[i][j] = ' ';
        System.out.print('W'+", "); 
      }
    }
  }
  //Main method 
  public static void main(String args[]) {new MazeSolver().loadMaze();}
}

//Type of Stack which hold the i and j coordinates of point in the maze
class MazePath {
  int i, j; 
  
  public MazePath(int i, int j) {
    this.i = i; 
    this.j = j;
  };
  
  public int i() { return i;}
  public int j() { return j;}
  
  public void print() {
    System.out.println("(" + i + "," + j + ")");
  }
  public MazePath north() {
    return new MazePath(i-1, j);
  }
  public MazePath south() {
    return new MazePath(i+1, j);
  }
  public MazePath east() {
    return new MazePath(i, j+1);
  }
  public MazePath west() {
    return new MazePath(i, j-1);
  }
} 
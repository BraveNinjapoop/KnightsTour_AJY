/**
 * Knights Tour Project
 * 09SEPT2019_v3
 * Author, Austin Young
 */

import java.util.Random;

public class NonHeuristic {

public NonHeuristic(int sizeOfChessBoard, int startX, int startY) { //Creates the method for NonHeuristic
	int[][] chessboard = new int[sizeOfChessBoard][sizeOfChessBoard]; // initialize an  chessboard where "sizeOfChessboard" = "N			  
    for (int i = 0; i<sizeOfChessBoard; i++)						  // initialize chessboard with -1 values in all squares.
      for (int j = 0; j<sizeOfChessBoard; j++)						  // -1 represents "not visited", therefore != -1 means visited
        chessboard[i][j] = -1;
    tryToMove(startX, startY, 0 , 0, 0, chessboard, sizeOfChessBoard, startX, startY); //first method that is ran for the first move
    
  }

static void shuffleArray(int[][] ar) //shuffle array to randomly shuffle the first move
  {
    Random rnd = new Random();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      int[] a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
  
public boolean tryToMove (int currentX, int currentY, int moveCount, int xDirection, int yDirection, int[][] chessboard, int sizeOfChessBoard, int startX, int startY) {
	int newX = currentX + xDirection; //creates a new x based on the current-direction
    int newY = currentY + yDirection; //creates a new y based on the current-direction
    int[][] startPos = new int [startX][startY]; //intializes the multidememsemional start position array
    
    if (newX < 0 || newX > chessboard.length - 1) //check and make sure that the move is within the bounds of the chessboard
      return false;
    
    if (newY < 0 || newY > chessboard.length - 1) 
      return false;
          
    if (chessboard[newX][newY] != -1) //check and make sure that we haven't already visited the spot we're moving to
      return false;
   
    chessboard[newX][newY] = moveCount; //sets this spot as visited
    moveCount++;
    
    System.out.println("Move " + moveCount + " is to (" + newX + ", "+ newY +")"); //print out the current position
	
    shuffleArray(startPos); //shuffles startPos to start at a new position
    
    //System.out.println(startPos); Displays the startPos after the shuffle. I couldn't get this working properly so I'm scrapping it
    
    // knight can move:
    /**
     * RIGHT, UP
     * UP, RIGHT
     * UP, LEFT
     * LEFT, UP
     * LEFT, DOWN
     * DOWN, LEFT
     * DOWN, RIGHT
     * RIGHT, DOWN
     */
    
    tryToMove(newX, newY, moveCount, 2, 1, chessboard, sizeOfChessBoard, startX, startY); //Methods that are ran to move the Knight and multiple different possibilities
    tryToMove(newX, newY, moveCount, 1, 2, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, -1, 2, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, -2, 1, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, -2, -1, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, -1, -2, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, 1, -2, chessboard, sizeOfChessBoard, startX, startY);
    tryToMove(newX, newY, moveCount, 2, -1, chessboard, sizeOfChessBoard, startX, startY);
    
    if (moveCount == sizeOfChessBoard * sizeOfChessBoard) { //If the knight fails on one of the methods above,
    	for (int i = 0; i<sizeOfChessBoard; i++) {			//it will either display it's move counts 
    		
        for (int j = 0; j<sizeOfChessBoard; j++) {
       
          //System.out.print(chessboard[i][j] + ", ");  Will display a grid of where the knight has gone based on chessboard and dimmsiones, i.e. 8x8 would be 64
        	System.out.print(moveCount+ ",");		   //My work in progress of trying to get the starting position array working properly and to randomly generate numbers, 
        											   //as well as display them in the [2,3], 47,[5,8] format
        }
        System.out.println();
      }
      System.exit(0); // if we don't exit here, it will continue to try and find
                      // additional solutions (which could take a VERY long time)
      return true;
    }
    else
    {
      chessboard[newX][newY] = -1;
      return false;
    }
      
  }
}
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class NoHeuristic {
	
	public int[][] board = new int[8][8];
	public int[][] moves = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2}};
	public boolean isRunning = true;
	public Knight knight;
	public int ammountofMoves = 0;
	public int tempIndex = 0;
	public File file = new File("NHOutput.txt");
	
	public NoHeuristic() {
		for (int[] i : board){
			for (int j : i) {
				j = 0;
			}
		}
	}
	
	static void shuffleArray(int[][] ar)
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
	
	public void write(String tw) throws IOException {
		file.createNewFile();
		Path path = Paths.get("NHOutput.txt");
		Files.write(path, tw.getBytes(), StandardOpenOption.APPEND);
	}
	
	public void run(int times) throws IOException {
		write("Starting new run!\n");
		for(int i = 0; i < times; i++) {
			shuffleArray(moves);
			knight = new Knight();
			ammountofMoves = 0;
			isRunning = true;
			
			for (int u = 0; u < 8; u++){
				for (int o = 0; o < 8; o++) {
					board[u][o] = 0;
				}
			}
			tempIndex = 0;
			while(isRunning) {
				int[] tempMove = moves[tempIndex];
				if(knight.getX()+tempMove[0] > -1 && knight.getX()+tempMove[0] < 8) { //checks if x is in bounds
					if(knight.getY() + tempMove[1] > -1 && knight.getY() + tempMove[1] < 8) { //checks if y is in bounds
						if(board[knight.getX() + tempMove[0]][knight.getY() + tempMove[1]] == 0) { //checks if we have not moved to new spot
							//so move now
							knight.setX(knight.getX() + tempMove[0]);//sets X
							knight.setY(knight.getY() + tempMove[1]);//sets Y
							board[knight.getX()][knight.getY()] = ammountofMoves;
							ammountofMoves++;
							tempIndex = 0;
						}
						else {
							//We have already moved to this spot
							tempIndex++;
						}
					}else {
						//Y is OOB
						tempIndex++;
					}
				}else {
					//X is OOB
					tempIndex++;
				}
				if(tempIndex == 8)
					isRunning = false;
			}
			if(knight.startingPos.equals(knight.location())) {
				write("[" + knight.getStartingPos()[0] + "," + knight.getStartingPos()[1] + "], " + ammountofMoves + ", [" + knight.location()[0]+ "," + knight.location()[1] + "]*");
			}else {
			write("[" + knight.getStartingPos()[0] + "," + knight.getStartingPos()[1] + "], " + ammountofMoves + ", [" + knight.location()[0]+ "," + knight.location()[1] + "]\n");
			}
		}
	}
	
}

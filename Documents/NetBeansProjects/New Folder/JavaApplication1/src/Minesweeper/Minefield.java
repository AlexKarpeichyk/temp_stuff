package Minesweeper;
import java.util.Random;
/**
 *
 * @author ak581
 */
public class Minefield 
{
    private boolean[][] minefield;
    private int[][] minedNeighbours;
    private final int rows;
    private final int columns;
    private final int mines;
    private int currMines;
    
    public Minefield(int nRows, int nColumns, int nMines)
    {
        rows = nRows + 2;
        columns = nColumns + 2;
        mines = nMines;
        currMines = 0;
        minefield = new boolean[rows][columns];
        minedNeighbours = new int[rows][columns];       
    }
    
    private boolean mineTile(int row, int column)
    {
        if(minefield[row][column] == false && currMines < mines && row > 0 && column > 0 && row < rows && column < columns) {
            minefield[row][column] = true;
            currMines++;
            for(int i = -1; i <= 1; i++) {
                for(int n = -1; n <= 1; n++) {
                    minedNeighbours[row + i][column + n]++;
                }
            }  
            return true;
        } else {
            return false;
        }
    }
    
    public void populate()
    {        
        Random r = new Random();
        while(currMines < mines) {
            int row = r.nextInt(rows - 2) + 1;
            int column = r.nextInt(columns - 2) + 1;
            if(row > 1 && column > 1) {
                mineTile(row, column);
            }
        }
    }    
        
    @Override
    public String toString()
    {
        String result = "";
        for(int n = 1; n < rows-1; n++) {
            for(int i = 1; i < columns-1; i++) {
                if(minefield[n][i] == true) {
                    result += "*";
                } else {
                    result += minedNeighbours[n][i];
                }               
            }
            result += "\n";
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        //int r = Integer.parseInt(args[0]);
        //int c = Integer.parseInt(args[1]);
        //int m = Integer.parseInt(args[2]);
        //Minefield ms = new Minefield(r, c, m);
        Minefield ms = new Minefield(10, 15, 40);
        ms.populate();
        System.out.println(ms.toString());
    }
}
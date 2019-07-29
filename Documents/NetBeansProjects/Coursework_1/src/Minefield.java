import java.util.Random;
/**
 * This class sets up a field of mines for the Minesweeper game, where the size
 * of the field and the number of mines on are is defined by the user. 
 * 
 * @version 1.0;
 */
public class Minefield 
{   
    private boolean[][] minefield;
    private int[][] minedNeighbours;
    private final int rows, columns, mines;
    private int mineCount = 0;
    
    /**
     * Constructor for class Minefield, sets up an empty minefield.
     * 
     * @param nRows     number of rows defined by the user.
     * @param nColumns  number of columns defined by the user.
     * @param nMines    maximum number of mines defined by the user.
     */

    public Minefield(int nRows, int nColumns, int nMines) 
    {
        //Adding 2 extra rows and columns to create a 'dummy' frame around 
        //the main filed to avoid ArrayIndexOutOfBounds error when mining.
        rows = nRows + 2;
        columns = nColumns + 2;
        mines = nMines;
        minefield = new boolean[rows][columns];
        minedNeighbours = new int[rows][columns];                
    }
    
    /**
     * Method returning the total number of tiles in the main field.
     * 
     * @return the size of the minefield.
     */
    public int getSize() 
    {
        //Removing extra rows and columns to get the size of the main field.
        return (rows - 2)*(columns - 2);
    }
    
    /**
     * Method returning the maximum number of mines allowed on the field.
     * 
     * @return mines    number of mines allowed.
     */
    public int getMaxMines() {
        return mines;
    }

    /**
     * Method that mines a single tile on the field at a coordinate, 
     * defined by the parameters.
     * 
     * @param row       coordinate component for the tile to be mined.
     * @param column    coordinate component for the tile to be mined.
     * 
     * @return true if successfully mined a tile, false otherwise.
     */
    public boolean mineTile(int row, int column) 
    {
        //Restrictions introduced for the method to mine only the tiles 
        //on the main field and to not go out of bounds of the field.
        if((minefield[row][column] == false) && (mineCount < mines) && (row > 0) && (column > 0) && (row < rows - 1) && (column < columns - 1)) {
            minefield[row][column] = true;
            mineCount++;
        //Incrementing the neighbouring mine totals of the surrounding tiles.
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

    /**
     * Method that fills the field with the required number of mines by picking
     * random positions to be mined.
     */
    public void populate() 
    {
        Random r = new Random();
        while(mineCount < mines) {
            int row = r.nextInt(rows);
            int column = r.nextInt(columns);
            //The condition doesn't allow the top left corner to be mined.
            if(!(row == 1 && column == 1)) {
                mineTile(row, column);
            }
        }
    }
    
    /**
     * Method that tells if a specific tile contains a mine or not. 
     * 
     * @param row       coordinate component for the tile to be checked.
     * @param column    coordinate component for the tile to be checked.
     * 
     * @return true if the tile is mined, false otherwise
     */
    public boolean isMined(int row, int column) 
    {
        return minefield[row][column];
    }

    /**
     * Method counting the mines on the minefield based on the data
     * from the minefield[][] array.
     * 
     * @return count    actual number of mines on the minefield.
     */
    public int countMines() 
    {
        int count = 0;
        for(int i = 1; i < rows-1; i++) {
            for(int n = 1; n < columns-1; n++) {
                if(minefield[i][n] == true) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Method tells the number of mines on the field based on the the 
     * mineCount variable value which was incremented in the mineTile method.
     *  
     * @return mineCount    number of mines on the minefield.
     */
    public int getCurrentMines() 
    {
        return mineCount;
    }
    
    /**
     * Method returns the filed of mines as a String with '*' representing
     * a tile with a mine in it and with integers representing 
     * the number of mines surrounding a tile.
     * 
     * @return result   array minedNeighbours as a String.
     */
    @Override
    public String toString()
    {
        String result = "";
        for(int n = 1; n < rows-1; n++) {
            for(int i = 1; i < columns-1; i++) {
                //If the tile in the boolean array has a mine, the integer 
                //in the tile with the same coordinates in the int array is replaced with '*'.
                if(minefield[n][i] == true) {
                    result += "*";
                } else {
                    result += minedNeighbours[n][i];
                }               
            }
            //Breaks the string when one row is printed.
            result += "\n";
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        Minefield test = new Minefield(10, 15, 40);
        test.populate();
        System.out.println(test.toString()); 
        System.out.println(test.countMines());
    }
}
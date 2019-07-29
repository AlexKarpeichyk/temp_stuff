import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for the class Minefield.
 * 
 * @version 1.0
 */
public class MinefieldTest 
{   
    Minefield big, small;
    
    /**
     * Constructor for the class MinefieldTest.
     */
    public MinefieldTest() 
    {
    }
    
    /**
     * Setting up mine fields to test.
     */
    @Before
    public void setUp() 
    {
        big = new Minefield(10, 8, 25);
        small = new Minefield(5, 5, 2);
    }
    
    /**
     * Test for the Minefield constructor, checking the size of the minefield 
     * and the maximum number of mines allowed.
     */
    @Test
    public void testCreation()
    {
        int s = big.getSize();
        int m = big.getMaxMines();
        assertEquals(10*8, s); 
        assertEquals(25, m);
    }
    
    /**
     * Test for the mineTile() method, checking the cases of successfully 
     * mined tile, trying to mine the same tile twice and exceeding
     * the maximum number of mines.
     */
    @Test
    public void testMining()
    {
        boolean exp1 = small.mineTile(2, 1); 
        boolean exp2 = small.mineTile(2, 1);
        assertEquals(true, exp1);        
        assertEquals(false, exp2);
        small.mineTile(2, 4);
        boolean exp3 = small.mineTile(2, 3);
        assertEquals(false, exp3);
    }
    
    /**
     * Test for the populate() method, checks that the field was filled with
     * the correct number of mines and that the top left tile of the field is avoided.
     */
    @Test
    public void testPopulating()
    {
        big.populate();
        boolean exp1 = big.isMined(1, 1);
        int exp2 = big.countMines();
        int exp3 = big.getCurrentMines();
        assertEquals(false, exp1);
        assertEquals(25, exp2);
        assertEquals(25, exp3); 
    }
    
    /**
     * Test for the toString() method, making sure that the populated field
     * is appropriately represented with a string.
     */
    @Test
    public void testToString()
    {
        small.mineTile(2, 2);
        small.mineTile(4, 2);
        String exp = small.toString();
        String s = "11100\n1*100\n22200\n1*100\n11100\n";
        assertEquals(s, exp);
    }
    
}


































/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ak581
 */
public class SterlingTest {
    
    public SterlingTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testCreation()
    {
        Sterling s = new Sterling(7);
        int v = s.getValue();
        assertEquals(7, v);
    }
    
    @Test
    public void testAddition()
    {
        Sterling s = new Sterling(7);
        Sterling s1 = new Sterling(3);
        Sterling s2 = s.addToValue(s1);        
        assertEquals(7+3, s2.getValue());
    }
    
    @Test
    public void changeByPercentage()
    {
        Sterling s = new Sterling(7);
        Sterling s1 = s.changeByPercentage(90.0);
        assertEquals(7*90/100, s1.getValue());
    }
    
    @Test
    public void testMultiply()
    {
        Sterling s = new Sterling(5);
        Sterling s1 = s.multiplyBy(4);
        assertEquals(20, s1.getValue());
        
    }
    
    @Test 
    public void testEquals()
    {
        Sterling s1 = new Sterling(7);
        Sterling s2 = new Sterling(4);
        Sterling s3 = new Sterling(7);
        assertEquals(s1, s1);
        assertTrue(!s1.equals(null));
        assertTrue(!s1.equals(s2)); 
        assertEquals(s1, s3);
    }
    
    
}

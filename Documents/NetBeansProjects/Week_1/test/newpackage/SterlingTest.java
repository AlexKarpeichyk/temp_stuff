/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ak581
 */
public class SterlingTest {
    
    public SterlingTest() {
    }

    public void testCreation()
    {
        Sterling s = new Sterling(7);
        int v = s.getValue();
        assertEquals(6, v);
    }
    /**
     * Test of addToValue method, of class Sterling.
     */
    @Test
    public void testAddToValue() {
        Sterling v2 = new Sterling(7);
        Sterling instance = new Sterling(3);
        instance.addToValue(v2);
        assertEquals(7+3, instance.getValue());
    }

    /**
     * Test of changeByPercentage method, of class Sterling.
     */
    @Test
    public void testChangeByPercentage() {
        double p = 5.0;
        Sterling instance = new Sterling(100);
        instance.changeByPercentage(p);
        assertEquals(500, instance.getValue());
    }

    /**
     * Test of getValue method, of class Sterling.
     */
    @Test
    public void testGetValue() {
        Sterling instance = new Sterling(12);
        int expResult = 12;
        int result = instance.getValue();
        assertEquals(expResult, result);
    }
    
}

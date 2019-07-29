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
public class ShareHoldingTest 
{
    ShareHolding hmv;
    public ShareHoldingTest() 
    {
    }
    
    @Before
    public void setUp() 
    {
        hmv = new ShareHolding("HMV", new Sterling(1), 10);
    }
    
    @Test
    public void testShareHolding()
    {
        assertEquals("HMV", hmv.getName());
        assertEquals(1, hmv.getPrice().getValue());
        assertEquals(10, hmv.getQuantity());
        hmv.setPrice(new Sterling(7));
        assertEquals(7, hmv.getPrice().getValue());
        hmv.setQuantity(11);
        assertEquals(11, hmv.getQuantity());
    }
    
}

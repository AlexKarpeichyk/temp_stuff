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
public class PortfolioTest {
    
    ShareHolding google, apple, ibm;
    Portfolio p;
    
    public PortfolioTest() {
    }
    
    @Before
    public void setUp() {
        google = new ShareHolding("GOOGLE", new Sterling(1000), 70);        
        ibm = new ShareHolding("IBM", new Sterling(500), 50);
        apple = new ShareHolding("APPLE", new Sterling(5), 10);
        p = new Portfolio();
        p.add(google);
        p.add(ibm);
        p.add(apple);
    }
    
    @Test 
    public void testAddShare()
    {
        assertEquals(70, p.getByName("GOOGLE").getQuantity());
        assertEquals(10, p.getByName("APPLE").getQuantity());
        assertEquals(50, p.getByName("IBM").getQuantity());
    }
    
    @Test
    public void testValue()
    {
        assertEquals(1000*70 + 500*50 + 5*10, p.getTotalValue().getValue());
    }
}

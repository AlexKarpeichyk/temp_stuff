/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ak581
 */
public class ShareHoldingTest {
    
    public ShareHoldingTest() {
    }

    /**
     * Test of getPrice method, of class ShareHolding.
     */
    @Test
    public void testGetPrice() {

        ShareHolding instance = new ShareHolding("SH", , 7);
        Sterling expResult = null;
        Sterling result = instance.getPrice();
        assertEquals(expResult, result);

    }

    /**
     * Test of getQuantity method, of class ShareHolding.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        ShareHolding instance = null;
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ShareHolding.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ShareHolding instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class ShareHolding.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        Sterling newPrice = null;
        ShareHolding instance = null;
        instance.setPrice(newPrice);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQty method, of class ShareHolding.
     */
    @Test
    public void testSetQty() {
        System.out.println("setQty");
        int newQty = 0;
        ShareHolding instance = null;
        instance.setQty(newQty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testSetPrice() {
        Sterling newPrice = new Sterling(5);
        ShareHolding instance = new ShareHolding();
        instance.setPrice(newPrice);
    }

    /**
     * Test of setQty method, of class ShareHolding.
     */
    @Test
    public void testSetQty() {
        int newQty = 3;
        ShareHolding instance = new ShareHolding();
        instance.setQty(newQty);
    }
    
}

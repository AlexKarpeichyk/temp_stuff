/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Minesweeper;

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
public class MinefieldTest {
    
    public MinefieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of populate method, of class Minefield.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        Minefield instance = null;
        instance.populate();
    }

    /**
     * Test of toString method, of class Minefield.
     */
    @Test
    public void testToString() {
        Minefield instance = new Minefield(10, 10, 25);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Minefield.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Minefield.main(args);
    }
    
}

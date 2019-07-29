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
public class CashAccountTest {
    
    final static String CHEQUE = "Cheque";
    final static String ACCOUNT = "000001";
    final static String SORTCODE = "01-02-03";    
    private CashAccount acc1;
    
    public CashAccountTest() {
    }
    
    @Before
    public void setUp()
    {
        acc1 = new CashAccount(CHEQUE, ACCOUNT, SORTCODE);
    }
    
    @Test
    public void testAccount() {
        assertEquals(new Sterling(0),acc1.getValue());
        assertEquals(CHEQUE,acc1.getName());
        assertEquals(ACCOUNT,acc1.getAccountNumber());
        assertEquals(SORTCODE,acc1.getSortCode());
        acc1.deposit(new Sterling(20));
        assertEquals(new Sterling(20),acc1.getValue());
        Sterling s1 = acc1.withdraw(new Sterling(5));
        assertEquals(new Sterling(5),s1);
        assertEquals(new Sterling(20-5),acc1.getValue());
    }
    
    
    
    
}

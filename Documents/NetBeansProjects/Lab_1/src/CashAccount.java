/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ak581
 */
class CashAccount {

    private String accountNum;
    private String sortCode;
    
    public CashAccount(String name, String accNumber, String sort) {
        super(name, new Sterling(0));
        this.accountNum = accNumber;
        this.sortCode = sort;
    }
    
    public String getAccountNumber() {
        return accountNum;
    }

    public String getSortCode() {
        return sortCode;
    }
    
    public void deposit(Sterling s) {
        setValue(getValue().addToValue(s));
    }

    Sterling withdraw(Sterling s) {
        setValue(getValue().subtract(s));
        return new Sterling(s.getValue());
    }
    
}

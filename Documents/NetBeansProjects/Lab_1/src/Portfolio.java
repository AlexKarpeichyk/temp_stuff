
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ak581
 */
class Portfolio {
    private HashMap<String, ShareHolding> shares = new HashMap();
    
    public void add(ShareHolding s)
    {
        if(shares.containsKey(s.getName())) {
            s.setQuantity(s.getQuantity()+shares.get(s.getName()).getQuantity());
            shares.put(s.getName(), s);
        } else {
            shares.put(s.getName(), s);
        }
    }

    public ShareHolding getByName(String s) {
        return shares.get(s);
    }

    public Sterling getTotalValue() {
        Sterling total = new Sterling(0);
        for(ShareHolding s: shares.values()) {
            total = total.addToValue(s.getCurrentValue());
        }
        return total;
    }
}

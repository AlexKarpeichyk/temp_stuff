
package newpackage;

/**
 *
 * @author ak581
 */
public class ShareHolding {
    private Sterling price;
    private int quantity;
    private String name;
    
    public ShareHolding(String name, int quantity, Sterling price)
    {
        this.name = name;
        this.quantity = quantity;
        this.price = price;        
    }
    
    public Sterling getPrice()
    {
        return price;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setPrice(Sterling newPrice) {
        price = newPrice;
    }
    
    public void setQty(int newQty) {
        quantity = newQty;
    }
}

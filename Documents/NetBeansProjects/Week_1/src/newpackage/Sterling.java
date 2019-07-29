
package newpackage;

/**
 *
 * @author ak581
 */
public class Sterling {
    private int value;
    
    public Sterling(int value) {
        this.value = value;
    }
    
    public void addToValue(Sterling v2) {;
        value += v2.getValue();
    }
    
    public void changeByPercentage(double p) {
        value = (int) (value*p);
    }
    
    public int getValue() {
        return value;
    }
}

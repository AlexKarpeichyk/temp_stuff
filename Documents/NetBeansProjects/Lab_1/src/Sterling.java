/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ak581
 */
public class Sterling {
    
    private int value;
    
    public Sterling(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public Sterling addToValue(Sterling s1) {
        return new Sterling(this.value += s1.getValue());
    }

    public Sterling changeByPercentage(double d) {
        return new Sterling((int)(value*d/100));
    }

    public Sterling multiplyBy(int i) {
        return new Sterling(getValue()*i);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sterling other = (Sterling) obj;
        if (this.value != other.value) {
            return false;
        }
        return true;
    }
    
    

}

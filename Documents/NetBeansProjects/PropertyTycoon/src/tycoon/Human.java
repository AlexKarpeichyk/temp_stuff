package tycoon;

import java.util.ArrayList;
import java.util.HashMap;

public class Human 
{
    private String token;
    private int cash, penalty, jail_safe;
    private HashMap<String, ArrayList<Property>> properties_owned;
    private Cell position;

    public Human(String token) {
        this.token = token;
        this.cash = 1500;
        this.jail_safe = 0;
        this.penalty = 0;
        properties_owned = new HashMap<>();
        String[] prop_types = new String[] {"brown", "blue", "purple", "orange", "red", "yellow", "green", "deep blue", "station", "utilities"};
        for(String prop_type: prop_types) {
            properties_owned.put(prop_type, new ArrayList<>());
        }
    }    
    
    public Cell getPosition() {
        return position;
    }

    public String getToken() {
        return token;
    }

    public int getCash() {
        return cash;
    }

    public int getPenalty() {
        return penalty;
    }

    public int getJailSafe() {
        return jail_safe;
    }

    public HashMap<String, ArrayList<Property>> getProperties() {
        return properties_owned;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public void setJail_safe(int jail_safe) {
        this.jail_safe = jail_safe;
    }

    public void setProperties(HashMap<String, ArrayList<Property>> properties_owned) {
        this.properties_owned = properties_owned;
    }
}
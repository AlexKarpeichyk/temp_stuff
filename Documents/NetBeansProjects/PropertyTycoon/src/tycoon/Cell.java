package tycoon;

public class Cell {
    
    public int boardPosition;
    
    public Cell(int pos) {        
        boardPosition = pos;
    }

    public int getBoardPosition() {
        return boardPosition;
    }
      
}

class CardCell extends Cell {
    
    public int type;
    
    public CardCell(int pos, int type) {
        super(pos); 
        this.type = type;
    }      
}

class Property extends Cell {
    
    public int price;
    public Player ownerToken;
    public int rent;
    public String name;

    public int getPrice() {
        return price;
    }

    public Player getOwnerToken() {
        return ownerToken;
    }

    public int getRent() {
        return rent;
    }

    public String getName() {
        return name;
    }
    
    public Property(int pos, int _price, Player _ownerToken, int _rent, String _name) {
        super(pos);
        price = _price;
        ownerToken = _ownerToken;
        rent = _rent;
        name = _name;
    }
}
class Utility extends Property{
    public Utility(int pos,int _price,Player _ownerToken,int _rent,String _name){
        super(pos,_price,_ownerToken,_rent,_name);
    }
}
class Station extends Property{
    public Station(int pos,int _price,Player _ownerToken,int _rent,String _name){
        super(pos,_price,_ownerToken,_rent,_name);
    }
}
class Building extends Property{
    public int level;
    public String colour;

    public Building(int pos, int _price, Player _ownerToken, int _rent, String _name, String colour) {
        super(pos, _price, _ownerToken, _rent, _name);
        this.colour = colour;
    }

    public int getLevel() {
        return level;
    }

    public String getColour() {
        return colour;
    }
    
}
class FreeParking extends Cell{
    public FreeParking(int pos){
         super(pos); 
      }
      
}
class GO extends Cell{
    public GO(int pos){
         super(pos); 
      }
      
}
class Tax extends Cell{
    public Tax(int pos){
         super(pos); 
      }      
}

class VisitJail extends Cell{
    public VisitJail(int pos){
         super(pos); 
      }      
}

class Jail extends Cell{
    public Jail(int pos){
         super(pos); 
      }      
}

class SuperTax extends Cell{
    public SuperTax(int pos){
         super(pos); 
      }
      
}
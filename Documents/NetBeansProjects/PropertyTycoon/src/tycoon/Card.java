package tycoon;

public class Card {
    public String card_pile;
    public String instuctions;
    public int id;
    
    public Card(String card_pile,String instuctions,int id) {
        this.card_pile = card_pile;
        this.instuctions = instuctions;
        this.id = id;
    }

    public String getCard_pile() {
        return card_pile;
    }

    public String getInstuctions() {
        return instuctions;
    }

    public int getId() {
        return id;
    }
}

class MoneyChangeType extends Card{
    public int amount;

    public int getAmount() {
        return amount;
    }
    public MoneyChangeType(String _card_pile,String _instuctions,int _id,int _amount){
        super(_card_pile,_instuctions,_id);
        amount=_amount;
}
    
}
class MoveToCell extends Card{
    public String name;
    public int idToMove;

    public String getName() {
        return name;
    }

    public int getIdToMove() {
        return idToMove;
    }
    public MoveToCell(String _card_pile,String _instuctions,int _id,String _name,int _idToMove){
        super(_card_pile,_instuctions,_id);
        name=_name;
        idToMove=_idToMove;
}
}
class MoveByFields extends Card{
    public int numberOfFields;

    public int getNumberOfFields() {
        return numberOfFields;
    }
    public MoveByFields(String _card_pile,String _instuctions,int _id,int _numberOfFields){
        super(_card_pile,_instuctions,_id);
        int numberOfFields=_numberOfFields;
}
}
class GoToJail extends Card{
    public GoToJail(String _card_pile,String _instuctions,int _id,int _amount){
        super(_card_pile,_instuctions,_id);
}
}
class PayRepairs extends Card{
    public int forHouse;
    public int forHotel;

    public int getForHouse() {
        return forHouse;
    }

    public int getForHotel() {
        return forHotel;
    }
    public PayRepairs(String _card_pile,String _instuctions,int _id,int _amount,int _forHouse,int _forHotel){
        super(_card_pile,_instuctions,_id);
        forHouse=_forHouse;
        forHotel=_forHotel;
    }
}
class GetOutFoJail extends Card{
    public GetOutFoJail(String _card_pile,String _instuctions,int _id,int _amount){
        super(_card_pile,_instuctions,_id);
}
}
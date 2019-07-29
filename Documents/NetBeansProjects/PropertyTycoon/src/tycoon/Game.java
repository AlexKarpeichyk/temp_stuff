package tycoon;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author ak581
 */
public class Game 
{
    private ArrayList<Property> list_of_properties = new ArrayList<>();
    private ArrayList<Cell> board = new ArrayList<>(40);
    //public int current_dice;
    public ArrayList<Card> pot_luck_data;
    public Queue<Card> pot_luck_pile;
    public ArrayList<Card> opportunity_knocks_data;
    public Queue<Card> opportunity_knocks_pile;
    private int[] dice = new int[2];
    private Random rand = new Random();    
            
    public Game() {
        for(int i = 0; i < 40; i++) {
            board.add(null);
        }       
        buildPropertyList();
        buildBoard();
    }
    
    public void buildPropertyList() {
        list_of_properties.add(new Building(1, 60, null, 2, "Crapper Street", "brown"));
        list_of_properties.add(new Building(3, 60, null, 4, "Gangsters Paradise", "brown"));
        list_of_properties.add(new Station(5, 200, null, 25, "Brighton Station"));
        list_of_properties.add(new Building(6, 100, null, 6, "Weeping Angel", "blue"));
        list_of_properties.add(new Building(8, 100, null, 6, "Potts Avenue", "blue"));
        list_of_properties.add(new Building(9, 120, null, 8, "Nardole Drive", "blue"));
        list_of_properties.add(new Building(11, 140, null, 10, "Skywalker Drive", "purple"));
        list_of_properties.add(new Utility(12, 150, null, 4, "Tesla Power Co"));
        list_of_properties.add(new Building(13, 140, null, 10, "Wookie Hole", "purple"));
        list_of_properties.add(new Building(14, 160, null, 12, "Rey Lane", "purple"));
        list_of_properties.add(new Station(15, 200, null, 25, "Hove Station"));
        list_of_properties.add(new Building(16, 180, null, 14, "Cooper Drive", "orange"));
        list_of_properties.add(new Building(18, 180, null, 14, "Wolowitz Street", "orange"));
        list_of_properties.add(new Building(19, 200, null, 16, "Penny Lane", "orange"));
        list_of_properties.add(new Building(21, 220, null, 18, "Yue Fei Square", "red"));
        list_of_properties.add(new Building(23, 220, null, 18, "Mulan Rouge", "red"));
        list_of_properties.add(new Building(24, 240, null, 20, "Han Xin Gardens", "red"));
        list_of_properties.add(new Station(25, 200, null, 25, "Falmer Station"));
        list_of_properties.add(new Building(26, 260, null, 22, "Kirk Close", "yellow"));
        list_of_properties.add(new Building(27, 260, null, 22, "Picard Avenue", "yellow"));
        list_of_properties.add(new Utility(28, 150, null, 4, "Edison Water"));
        list_of_properties.add(new Building(29, 280, null, 22, "Crusher Creek", "yellow"));
        list_of_properties.add(new Building(31, 300, null, 26, "Sirat Mews", "green"));
        list_of_properties.add(new Building(32, 300, null, 26, "Chengis Crecent", "green"));
        list_of_properties.add(new Building(34, 320, null, 28, "Ibis Close", "green"));
        list_of_properties.add(new Station(35, 200, null, 25, "Lewes Station"));
        list_of_properties.add(new Building(37, 350, null, 35, "Ibis Close", "deep blue"));
        list_of_properties.add(new Building(39, 400, null, 50, "Ibis Close", "deep blue"));
    }
    
    public void buildBoard() {
        board.set(0, new GO(0));
        board.set(2, new CardCell(2, 0));
        board.set(4, new Tax(4));
        board.set(7, new CardCell(7, 1));
        board.set(10, new VisitJail(10));
        board.set(17, new CardCell(17, 0));
        board.set(20, new FreeParking(20));
        board.set(22, new CardCell(22, 1));
        board.set(30, new Jail(30));
        board.set(33, new CardCell(33, 0));
        board.set(36, new CardCell(36, 1));
        board.set(38, new SuperTax(38));
        for(Cell property: list_of_properties) {
            board.set(property.getBoardPosition(), property);
        }
    }
    
    public ArrayList<Cell> fullBoard() {
        return board;
    }
    
    public void rollDemDice() {
        int D1 = rand.nextInt(7 - 1) + 1;
        int D2 = rand.nextInt(7 - 1) + 1;
        dice[0] = D1;
        dice[1] = D2;
    }
    
    public int[] getDice() {
        return dice;
    }
    
    public static void main(String[] args) {
        Game g = new Game();
        for(Cell c: g.board) {
            System.out.println(g.board.indexOf(c) + " - " + c);
        }
    }   
}

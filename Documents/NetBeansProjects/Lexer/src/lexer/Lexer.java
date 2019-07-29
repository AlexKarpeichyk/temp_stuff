/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 *
 * @author ak581
 */
public class Lexer {
    
    private boolean accept;
    private String chars;
    private String c_chars;
    
    public Lexer() {
        chars = "abcdefghigklmnopqrstuvwxyz";
        c_chars = "ABCDEFGHIGKLMNOPQRSTUVW";
        accept = false;
    }
    
    public boolean scan(String s) {
        if(chars.contains(s.substring(0, 1))) {
            
        }
        return accept;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

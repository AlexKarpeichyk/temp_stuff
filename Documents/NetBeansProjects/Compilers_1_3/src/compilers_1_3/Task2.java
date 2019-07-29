package compilers_1_3;

import java.util.ArrayList;
import java.util.List;

class Task2 
{    
    public static Lexer create() { 
        return (String input) -> {
            String digit_pattern = "\\d+"; //regular expression for digits (as integers)
            String identifier_pattern = "[a-z](_|[a-z]|[A-Z]|[0-9])*"; //regular expression for identifiers
            String special_notations = ";()=<>,{}:+*-/"; //list of special notations for the given language
            String temp_token = "";
            List<Token> tokens = new ArrayList<>(); //list of tokens to be returned by the method
            ArrayList<String> tokenised_input = new ArrayList<>(); //list to store relevant tokens of the input
            ArrayList<Character> input_chars = new ArrayList<>(); //list of all characters in the input to be used by the tokeniser
            for(char ch: input.toCharArray()) {
                input_chars.add(ch);
            }
            try {
                //tokeniser, analyses the input character by character splittig it into relevant tokens(according to the lexical description of the language)
                for(char c: input_chars) {
                    if(special_notations.indexOf(c) != -1) {
                        if(c == '=' || c == '<' || c == '>' || c == ':') {
                            switch (temp_token) {
                                case "":
                                    temp_token = temp_token + c;
                                    break;
                                case "=":
                                case "<":
                                case ">":
                                case ":":
                                    temp_token = temp_token + c;
                                    tokenised_input.add(temp_token);
                                    temp_token = "";
                                    break;
                                default:
                                    tokenised_input.add(temp_token);
                                    tokenised_input.add(Character.toString(c));
                                    break;
                            }
                        } else {
                            if(temp_token.equals("")) {
                                tokenised_input.add(Character.toString(c));
                            } else {
                                tokenised_input.add(temp_token);
                                temp_token = "";
                                tokenised_input.add(Character.toString(c));
                            }          
                        }
                    } else if(c == ' ') {
                        if(!temp_token.equals("")) {
                            tokenised_input.add(temp_token);
                            temp_token = "";
                        }
                    } else {
                        if(c != ' ') {
                            temp_token += c;
                        }
                    }            
                }
                if(!temp_token.equals("")) {
                    tokenised_input.add(temp_token); 
                }
            } catch(Exception e) {
                throw new Task2Exception("");
            }
            //for each token in the tokenised input create a token object of relevant class for the final list of tokens
            for(String token: tokenised_input) {
                Token t = null;
                if(token.equals("def")) {
                    t = new T_Def();               
                } else if(token.equals("if")) {
                    t = new T_If();
                } else if(token.equals("then")) {
                    t = new T_Then();
                } else if(token.equals("else")) {
                    t = new T_Else();
                } else if(token.equals("skip")) {
                    t = new T_Skip();
                } else if(token.equals("while")) {
                    t = new T_While();
                } else if(token.equals("do")) {
                    t = new T_Do();
                } else if(token.equals("repeat")) {
                    t = new T_Repeat();
                } else if(token.equals("until")) {
                    t = new T_Until();
                } else if(token.equals("break")) {
                    t = new T_Break();
                } else if(token.equals("continue")) {
                    t = new T_Continue();
                } else if(token.equals(";")) {
                    t = new T_Semicolon();
                } else if(token.equals("(")) {
                    t = new T_LeftBracket();
                } else if(token.equals(")")) {
                    t = new T_RightBracket();
                } else if(token.equals("=")) {
                    t = new T_EqualDefines();
                } else if(token.equals("==")) {
                    t = new T_Equal();
                } else if(token.equals("<")) {
                    t = new T_LessThan();
                } else if(token.equals(">")) {
                    t = new T_GreaterThan();
                } else if(token.equals("<=")) {
                    t = new T_LessEq();
                } else if(token.equals(">=")) {
                    t = new T_GreaterEq();
                } else if(token.equals(",")) {
                    t = new T_Comma();
                } else if(token.equals("{")) {
                    t = new T_LeftCurlyBracket();
                } else if(token.equals("}")) {
                    t = new T_RightCurlyBracket();
                } else if(token.equals(":=")) {
                    t = new T_Assign();
                } else if(token.equals("+")) {
                    t = new T_Plus();
                } else if(token.equals("*")) {
                    t = new T_Times();
                } else if(token.equals("-")) {
                    t = new T_Minus();
                } else if(token.equals("/")) {
                    t = new T_Div();
                } else if(token.matches(digit_pattern)) {
                    t = new T_Integer(Integer.parseInt(token));
                } else if(token.matches(identifier_pattern)) {
                    t = new T_Identifier(token);
                } else {
                    throw new LexicalException("");
                }
                tokens.add(t);
                if(t instanceof T_Identifier || t instanceof T_Integer) {
                    System.out.println(t.getClass().getSimpleName() + "(" + token + ")");
                } else {
                    System.out.println(t.getClass().getSimpleName());
                }
                
            }
            return tokens;            
        }; 
    }
    /*public static void main(String[] args) throws LexicalException, Task2Exception {
        String s = "{10;skip}";
        create().lex(s);        
    }*/
}
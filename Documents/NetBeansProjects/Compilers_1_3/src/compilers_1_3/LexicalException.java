package compilers_1_3;

public class LexicalException extends Exception
{
    public String msg;
    
    public LexicalException(String _msg) {
        msg = _msg; 
    }
}

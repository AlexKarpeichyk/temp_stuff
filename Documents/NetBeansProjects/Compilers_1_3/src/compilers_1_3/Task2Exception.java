package compilers_1_3;

public class Task2Exception extends Exception
{
    public String msg;
    
    public Task2Exception(String _msg) { 
        msg = _msg; 
    }
}

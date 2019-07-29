interface Matrix2D 
{
 public int initialState();
 public int terminalState();
 public int nextState(int currentState, int character);
}

class Task1Exception extends Exception
{
    public String msg;
    
    public Task1Exception(String _msg) {
        msg = _msg;
    }
}

interface Language 
{
    public boolean decide(int[] input) throws Task1Exception;
}

class LanguageClass implements Language {
    public Matrix2D mat2D;
    public int currentState;
    public int terminalState;
    
    public LanguageClass(Matrix2D matrix2D) {
        mat2D = matrix2D;
        currentState = matrix2D.initialState();
        currentState = matrix2D.terminalState();
    }
    
    @Override
    public boolean decide(int[] input) throws Task1Exception {
        try {
            for(int i = 0; i < input.length; i++) {
                currentState = mat2D.nextState(currentState, input[i]);
            }            
        } catch(Exception e) {
            throw new Task1Exception("");
        } 
        return currentState == terminalState;
    }    
}

class Task1 {    
    public static Language create(Matrix2D matrix2D) {       
        return new LanguageClass(matrix2D);
    } 
}
  

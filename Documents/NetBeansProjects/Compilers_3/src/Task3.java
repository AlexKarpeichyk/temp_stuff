import java.util.List;

class CodegenException extends Exception 
{
    public String msg;
    public CodegenException(String _msg) { 
        msg = _msg; 
    } 
}

interface Codegen {
    public String codegen(Program p) throws CodegenException; 
}

class Task3 
{       
    public static Codegen create() throws CodegenException {
        CodegenClass cgen = new CodegenClass();
        return cgen;
    }
}

class CodegenClass implements Codegen
{
    String mips_prog = "";
    int if_increment = 0;
    int while_increment = 0;
    int repeat_increment = 0;
    
    @Override
    public String codegen(Program p) throws CodegenException {
        mips_prog = "";
        for(Declaration dec: p.decls) {
            codegenDeclaration(dec);
        }
        return mips_prog;
    }

    public void codegenDeclaration(Declaration d) throws CodegenException {
        mips_prog += "\n" + ".data";
        int sizeAR = (2 + d.numOfArgs)*4;
        mips_prog += "\n" + ".text";
        mips_prog += "\n" + d.id + "_entry:";
        mips_prog = mips_prog + "\n\t" + "move $fp $sp"
                              + "\n\t" + "sw $ra 0($sp)"
                              + "\n\t" + "addiu $sp $sp -4";
        codegenExp(d.body);
        mips_prog = mips_prog + "\n\t" + "lw $ra 4($sp)"
                              + "\n\t" + "addiu $sp $sp " + sizeAR
                              + "\n\t" + "lw $fp 0($sp)"
                              + "\n\t" + "li $v0, 10" 
                              + "\n\t" + "syscall";
    }

    public void codegenExp(Exp e) throws CodegenException {
        if(e instanceof IntLiteral) {
            mips_prog = mips_prog + "\n\t" + "li $a0 " + ((IntLiteral) e).n;
        } else if(e instanceof Variable) {
            int offset = 4*((Variable) e).x;
            mips_prog = mips_prog + "\n\t" + "lw $a0 " + offset + "($fp)";
        } else if(e instanceof If) {
            if_increment++;
            String elseBranch = "else_" + if_increment;
            String thenBranch = "then_" + if_increment;
            String exitLabel = "exit_" + if_increment;
            codegenExp(((If) e).l);
            mips_prog = mips_prog + "\n\t" + "sw $a0 0($sp)"
                                  + "\n\t" + "addiu $sp $sp -4";
            codegenExp(((If) e).r);
            mips_prog = mips_prog + "\n\t" + "lw $t1 4($sp)"
                                  + "\n\t" + "addiu $sp $sp 4";
            codegenComp(((If) e).comp);
            mips_prog += thenBranch;
            mips_prog = mips_prog + "\n" + elseBranch + ":";
            codegenExp(((If) e).elseBody);
            mips_prog = mips_prog + "\n\t" + "b " + exitLabel
                                  + "\n" + thenBranch + ":";
            codegenExp(((If) e).thenBody);
            mips_prog = mips_prog + "\n" + exitLabel + ":";
        } else if(e instanceof Binexp) {
            codegenExp(((Binexp) e).l);
            mips_prog = mips_prog + "\n\t" + "sw $a0 0($sp)"
                                  + "\n\t" + "addiu $sp $sp -4";
            codegenExp(((Binexp) e).r);
            mips_prog = mips_prog + "\n\t" + "lw $t1 4($sp)"
                                  + "\n\t" + "addiu $sp $sp 4";
            codegenBinop(((Binexp) e).binop);
        } else if(e instanceof Invoke) {
            codegenInvoke(((Invoke) e).args, ((Invoke) e).name);
        } else if(e instanceof Skip) {
            mips_prog += "\n\t nop";
        } else if(e instanceof Seq) {
            codegenExp(((Seq) e).l);
            codegenExp(((Seq) e).r);
        } else if(e instanceof While) {
            while_increment++;
            String loop = "loop_" + while_increment;
            String loop_body = "loop_body_" + while_increment;
            String loop_exit = "loop_exit_" + while_increment;
            mips_prog = mips_prog + "\n" + loop + ":";
            mips_prog += "\n\t" + "la $t2, " + loop_exit;
            mips_prog += "\n\t" + "sw $t2, 0($sp)";
            mips_prog += "\n\t" + "addiu $sp $sp -4";
            mips_prog += "\n\t" + "la $t3, " + loop;
            mips_prog += "\n\t" + "sw $t3, 0($sp)";
            mips_prog += "\n\t" + "addiu $sp $sp -4";
            codegenExp(((While) e).l);
            mips_prog = mips_prog + "\n\t" + "sw $a0 0($sp)"
                                  + "\n\t" + "addiu $sp $sp -4";
            codegenExp(((While) e).r);
            mips_prog = mips_prog + "\n\t" + "lw $t1 4($sp)"
                                  + "\n\t" + "addiu $sp $sp 4";
            codegenComp(((While) e).comp);
            mips_prog += loop_body;
            mips_prog += "\n\t" + "j " + loop_exit;
            mips_prog += "\n" + loop_body + ":";
            codegenExp(((While) e).body);
            mips_prog += "\n\t" + "j " + loop;
            mips_prog = mips_prog + "\n" + loop_exit + ":";
            mips_prog = mips_prog + "\n\t" + "lw $t2 4($sp)";
            mips_prog = mips_prog + "\n\t" + "addiu $sp $sp 4";
            mips_prog = mips_prog + "\n\t" + "lw $t3 4($sp)";
            mips_prog = mips_prog + "\n\t" + "addiu $sp $sp 4";
        } else if(e instanceof RepeatUntil) {
            repeat_increment++;
            String repeat = "repeat_" + repeat_increment;
            String repeat_exit = "repeat_exit_" + repeat_increment;
            mips_prog = mips_prog + "\n" + repeat + ":";
            mips_prog += "\n\t" + "la $t2, " + repeat_exit;
            mips_prog += "\n\t" + "sw $t2, 0($sp)";
            mips_prog += "\n\t" + "addiu $sp $sp -4";
            mips_prog += "\n\t" + "la $t3, " + repeat;
            mips_prog += "\n\t" + "sw $t3, 0($sp)";
            mips_prog += "\n\t" + "addiu $sp $sp -4";
            codegenExp(((While) e).body);
            codegenExp(((While) e).l);
            mips_prog = mips_prog + "\n\t" + "sw $a0 0($sp)"
                                  + "\n\t" + "addiu $sp $sp -4";
            codegenExp(((While) e).r);
            mips_prog = mips_prog + "\n\t" + "lw $t1 4($sp)"
                                  + "\n\t" + "addiu $sp $sp 4";
            codegenComp(((While) e).comp);
            mips_prog += repeat_exit;
            mips_prog += "\n\t" + "j " + repeat;
            mips_prog = mips_prog + "\n" + repeat_exit + ":";
            mips_prog = mips_prog + "\n\t" + "lw $t2 4($sp)";
            mips_prog = mips_prog + "\n\t" + "addiu $sp $sp 4";
            mips_prog = mips_prog + "\n\t" + "lw $t3 4($sp)";
            mips_prog = mips_prog + "\n\t" + "addiu $sp $sp 4";
        } else if(e instanceof Assign) {
            int offset = 4*((Assign) e).x;
            codegenExp(((Assign) e).e);
            mips_prog = mips_prog + "\n\t" + "sw $a0 " + offset + "($fp)";
        } else if(e instanceof Break) {            
            mips_prog = mips_prog + "\n\t" + "jr $t2";
        } else if(e instanceof Continue) {
            mips_prog = mips_prog + "\n\t" + "jr $t3";
        } else {
            throw new CodegenException("");
        }
    }
    
    public void codegenComp(Comp c) {
        if(c instanceof Equals) {
            mips_prog += "\n\t" + "beq $a0 $t1 ";
        } else if(c instanceof Less) {
            mips_prog += "\n\t" + "blt $t1 $a0 ";
        } else if(c instanceof LessEq) {
            mips_prog += "\n\t" + "ble $t1 $a0 ";
        } else if(c instanceof Greater) {
            mips_prog += "\n\t" + "bgt $t1 $a0 ";
        } else if(c instanceof GreaterEq) {
            mips_prog += "\n\t" + "bge $t1 $a0 ";
        }
    }

    public void codegenBinop(Binop b) {
        if(b instanceof Plus) {
            mips_prog += "\n\t" + "add $a0 $t1 $a0";
        } else if(b instanceof Minus) {
            mips_prog += "\n\t" + "sub $a0 $t1 $a0";
        } else if(b instanceof Times) {
            mips_prog += "\n\t" + "mul $a0 $t1 $a0";
        } else if(b instanceof Div) {
            mips_prog = mips_prog + "\n\t" + "div $t1, $a0" 
                                  + "\n\t" + "mflo $a0";
        }
    }

    private void codegenInvoke(List<Exp> args, String name) throws CodegenException {
        mips_prog = mips_prog + "\n\t" + "sw $fp 0($sp)"
                              + "\n\t" + "addiu $sp $sp -4";
        if(args.isEmpty()) {            
            mips_prog = mips_prog + "\n\t" + "jal " + name + "_entry";
        } else {
            for(int i = args.size() - 1; i >= 0; i--) {
                codegenExp(args.get(i));
                mips_prog = mips_prog + "\n\t" + "sw $a0 0($sp)"
                                      + "\n\t" + "addiu $sp $sp -4";                
            }
            mips_prog = mips_prog + "\n\t" + "jal " + name + "_entry";
        }
    }
}

// Do not modify the code below!  Don't add anything (including
// "public" declarations), don't remove anything. Don't wrap it in a
// package, don't make it an innner class of some other class.
// If your IDE suggsts to change anything below, ignore your IDE.

class Program { // Used in Tasks 1, 2, 3.
    // Leaves in the accumulator what running the first declaration leaves in the accumulator.
    public List <Declaration> decls;
    public Program ( List <Declaration> _decls ) { 
	assert ( _decls.size () > 0 ); // ensures that we have at least one declaration the
                                       // first element in decls is the the procedure executed
                                       // at startup. We assume that this initial procedure takes
                                       // 0 arguments. NOTE: the entry procedure does NOT have
	                               // have to be called "main". It can have ANY valid identifier
                                       // as name.	   
	assert ( _decls.get ( 0 ).numOfArgs == 0 ); // ensures that the first declaration takes
	                                            // 0 arguments.
	decls = _decls; } }


class Declaration { // Used in Tasks 1, 2, 3.
    public String id;
    public int numOfArgs; // Used for computing the size of the AR. 
    public Exp body;

    public Declaration ( String _id, int _numOfargs, Exp _body ) { 
	assert ( _numOfargs >= 0 );
	id = _id;
	numOfArgs = _numOfargs;
	body = _body; } }


abstract class Exp {} // Used in Tasks 1, 2, 3.

class IntLiteral extends Exp { // Used in Tasks 1, 2, 3.
    // Leaves n in the accumulator.
    public int n;
    IntLiteral ( int _n ) { n = _n; } }

class Variable extends Exp { // Used in Tasks 1, 2, 3.
    // Leaves the content of the variables x in the accumulator.  Note
    // that the integer x represents the OFFSET of the variable relative
    // to the encompassing procedure declaration (which is used by the
    // code generator to compute, at compile time, the place, relative
    // to the frame pointer, where the variable is stored at
    // run-time). It does NOT hold the values the variable takes at
    // run-time. E.g. in 
    //
    //      def f ( a, b, c, d ) = ...
    //
    // the variable a has offset 1, b has offset 2, c has 3 and d has
    // 4. There are no other offsets for this procedure.
    public int x;
    public Variable ( int _x ) { 
	assert ( _x > 0 ); // ensures that 1 is the smallest possible offset.
	x = _x; } }

class If extends Exp { // Used in Tasks 1, 2, 3.
    // If the comparison is true, executes the thenBody.
    // Otherwise the elseBody is executed.
    public Exp l;
    public Comp comp;
    public Exp r;
    public Exp thenBody;
    public Exp elseBody;
    public If ( Exp _l, Comp _comp, Exp _r, Exp _thenBody, Exp _elseBody ) {
        l = _l;
	comp = _comp;
	r = _r;
	thenBody = _thenBody;
	elseBody = _elseBody; } }

class Binexp extends Exp { // Used in Tasks 1, 2, 3.
    // Evaluates l, then r, and applies binop to both.
    public Exp l;
    public Binop binop;
    public Exp r;
    public Binexp ( Exp _l, Binop _binop, Exp _r ) {
	l = _l;
	binop = _binop; 
	r = _r; } }

class Invoke extends Exp { // Used in Tasks 1, 2, 3.
    // Evaluates the arguments right-to-left. Then calls the procedure name with
    // these arguments.
    public String name;
    public List<Exp> args;
    public Invoke ( String _name, List<Exp> _args ) {
	name = _name;
	args = _args; } }

class While extends Exp { // Used in Tasks 2, 3.
    // Implements the standard while-loop, 
    // Can return anything in accumulator
    public Exp l;
    public Comp comp;
    public Exp r;
    public Exp body;
    public While ( Exp _l, Comp _comp, Exp _r, Exp _body ) {
        l = _l;
	comp = _comp;
	r = _r;
	body = _body; } }

class RepeatUntil extends Exp { // Used in Tasks 2, 3.
    // Implements the standard repeat-unitl loop, 
    // Can return anything in accumulator.
    public Exp body;
    public Exp l;
    public Comp comp;
    public Exp r;
    public RepeatUntil ( Exp _body, Exp _l, Comp _comp, Exp _r ) {
	body = _body; 
        l = _l;
	comp = _comp;
	r = _r; } }

class Assign extends Exp { // Used in Tasks 2, 3.
    // Evaluates the right-hand side e, and stores the result
    // Can return anything in accumulator. 
    public int x;
    public Exp e;
    public Assign ( int _x, Exp _e ) {
	assert ( _x > 0 );
	x = _x;
	e = _e; } }

class Seq extends Exp { // Used in Tasks 2, 3.
    // Runs l first, then r.
    // Returns the result of r in the accumulator.
    public Exp l;
    public Exp r;
    public Seq ( Exp _l, Exp _r ) {
	l = _l;
	r = _r; } }

class Skip extends Exp { // Used in Tasks 2, 3.
    // Does nothing.
    // Can return anything in accumulator. 
    public Skip () {} }

class Break extends Exp { // Used in Task 3 only.
    // Exits the nearest enclosing loop. Leaves
    // Can return anything in accumulator.
    public Break () {} }

class Continue extends Exp { // Used in Task 3 only.
    // Abandons the current iteration of the  nearest enclosing
    // loop, jumping straight to the checking of the conditional.
    // Can return anything in accumulator.
    public Continue () {} }


abstract class Comp {} // Used in Tasks 1, 2, 3.

class Less extends Comp { // Used in Tasks 2, 3.
    public Less () {} }

class LessEq extends Comp { // Used in Tasks 2, 3.
    public LessEq () {} }

class Equals extends Comp { // Used in Tasks 1, 2, 3.
    public Equals () {} }

class Greater extends Comp { // Used in Tasks 2, 3.
    public Greater () {} }

class GreaterEq extends Comp { // Used in Tasks 2, 3.
    public GreaterEq () {} }


abstract class Binop {}

class Plus extends Binop { // Used in Tasks 1, 2, 3.
    public Plus () {} }

class Minus extends Binop { // Used in Tasks 1, 2, 3.
    public Minus () {} }

class Times extends Binop { // Used in Tasks 2, 3.
    public Times () {} }

class Div extends Binop { // Used in Task 3 only. 
    public Div () {} }

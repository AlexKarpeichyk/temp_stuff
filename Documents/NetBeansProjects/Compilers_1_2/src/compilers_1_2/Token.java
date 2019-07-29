package compilers_1_2;

public interface Token {}


class T_Semicolon implements Token {} // represents ;
class T_LeftBracket implements Token {} // represents (
class T_RightBracket implements Token {} // represents )
class T_EqualDefines implements Token {} // represents =
class T_Equal implements Token {} // represents ==
class T_LessThan implements Token {} // represents < 
class T_GreaterThan implements Token {} // represents >
class T_LessEq implements Token {} // represents <=
class T_GreaterEq implements Token {} // represents >=
class T_Comma implements Token {} // represents ,
class T_LeftCurlyBracket implements Token {} // represents {
class T_RightCurlyBracket implements Token {} // represents }
class T_Assign implements Token {} // represents :=
class T_Plus implements Token {} // represents +
class T_Times implements Token {} // represents *
class T_Minus implements Token {} // represents -
class T_Div implements Token {} // represents /
class T_Identifier implements Token { // represents names like x, i, n, numberOfNodes ...
    public String s;
    public T_Identifier ( String _s ) { s = _s; } }
class T_Integer implements Token { // represents non-negative numbers like 0, 1, 2, 3, ...
    public int n;
    public T_Integer ( int _n ) { n = _n; } }
class T_Def implements Token {} // represents def
class T_Skip implements Token {} // represents skip
class T_If implements Token {} // represents if
class T_Then implements Token {} // represents then
class T_Else implements Token {} // represents else
class T_While implements Token {} // represents while
class T_Do implements Token {} // represents do
class T_Repeat implements Token {} // represents repeat
class T_Until implements Token {} // represents until
class T_Break implements Token {} // represents break
class T_Continue implements Token {} // represents continue
class T_EOF implements Token {} 
// The next two token classes are auxiliary. They do NOT represent
// language syntax. They may be used in lexer construction, but they
// do not have to be used in the implementation. If you use them, make
// sure they do not appear in the token list that your lexer returns.
// Even if you don't use them, DO NOT REMOVE THEIR CLASS DEFINITIONS
// FROM YOUR SUBMISSION EVEN IF YOU DONT USE THEM.
class T_Error implements Token {
    public String msg;
    public T_Error ( String _msg ) { msg = _msg; } }
package compilers_1_3;

import java.util.List;

class Block {
    public List <Exp> exps;
    public Block ( List <Exp> _exps ) {
	assert ( _exps.size () > 0 );
	exps = _exps; } }

interface Exp {} 

class IntLiteral implements Exp { 
    public int n;
    IntLiteral ( int _n ) { n = _n; } }

class Skip implements Exp { 
    public Skip () {} }

class BlockExp implements Exp { 
    public Block b;
    public BlockExp ( Block _b ) { b = _b; } }

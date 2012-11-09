package comint01.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import comint01.interpreter.*;

import comint01.tokenizer.Token;
import comint01.tokenizer.Token.Type;
import comint01.tokenizer.Tokenizer;
import comint01.tokenizer.TokenizerException;


public class Parser {

	private Tokenizer tokenizer;
	private Token tok;


	public Parser(Reader in){
		tokenizer = new Tokenizer(in);
	}

	/**
	 * @throws IOException
	 * @throws TokenizerException
	 */
	private void read() throws IOException, TokenizerException {
		tok = tokenizer.next();
	}

	public Expr parse() throws Exception {
		Expr expr = null;

		// Expr ::= SeqExpr Eos .

		while (tok.getType() != Type.EOS) {
			read();
			expr = seqExpr();
		}
		return expr;
	}

	/**
	 * @return
	 * @throws Exception 
	 */
	private Expr seqExpr() throws Exception {
		// SeqExpr ::= ";"* AssignExpr (";" AssignExpr?)* .

		ArrayList<Expr> list = new ArrayList<Expr>();

		while (tok.getType() == Type.Semicolon)
			read();
		list.add(assignExpr());

		read();
		while (tok.getType() == Type.Semicolon){
			list.add(assignExpr());
			read();
		}

		return ExprSeq._(list.toArray(new Expr[list.size()]));
	}

	/**
	 * @throws TokenizerException 
	 * @throws IOException 
	 * 
	 */
	private Expr assignExpr() throws Exception {
		// AssignExpr ::= Id AssignOp AssignExpr
		//				| AddExpr .

		if (tok.getType() == Type.Id) {
			Id id = id();
			read();
			assignOp();
			read();
			assignExpr();

		} else {
			AddExpr();
		}

		return null;
	}

	/**
	 * 
	 */
	private Id id() {
		return Id._(tok.getId());
	}

	/**
	 * 
	 */
	private void assignOp() {
		/*		
		AssignOp ::= "="
		           | "+="
		           | "-="
		           | "*="
		           | "/=" .
		 */

		switch(tok.getType()){
		case Assign :
			break;
		case AssignPlus :
			break;
		case AssignMinus :
			break;
		case AssignMultiply :
			break;
		case AssignDivide : 
			break;
		default:
			break;
		
		}

		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	private void AddExpr() {
		// AddExpr ::= MulExpr (AddOp MulExpr)* .
		
		// TODO Auto-generated method stub

	}


}

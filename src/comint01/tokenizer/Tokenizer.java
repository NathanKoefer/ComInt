/**
 * @comint01_trunk, comint01.tokenizer
 * @author DNA, Rea
 */

package comint01.tokenizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.math.BigDecimal;
import comint01.tokenizer.Token.Type;


public class Tokenizer {

	private static final int EOS = -1;
	private static final int CR = '\r';
	private static final int LF = '\n';

	private BufferedReader stream;
	private final StringBuilder builder = new StringBuilder();
	private boolean stepBack = false;
	private int c;
	private Token currToken;
	private Token prevToken;

	public Tokenizer(Reader in){
		this.stream = new BufferedReader(in);
	}

	private void markAndRead(int n) throws IOException{
		stream.mark(n);
		read();
	}

	private void read() throws IOException {
		c = stream.read();
	}

	private void reset() throws IOException {
		stream.reset();
	}

	private void skipBlank() throws IOException{
		read();
		while(isWhite()){
			read();
		}
	}

	private boolean isWhite() {
		return Character.isWhitespace(c);
	}

	private boolean isNumeric(){
		return (Character.isDigit((char)c) || c == 'e' || c == 'E');
	}

	private boolean isDigit(){
		return ((Character.isDigit(c)) || c == '.');
	}

	private boolean isLetter(){
		return Character.isLetter((char)c);
	}

	private boolean isOp() {
		return(c == '='  || c == '+'  || c == '-'  || c == '/'  || c == '*');
	}

	private Object endOfStream() {
		if(c == EOS){
			return foundToken(Type.EOS);
		}
		return null;
	}

	private Token nested() throws IOException, TokenizerException {
		while(true){
			read();
			if(c=='*'){
				read();
				if(c=='/'){
					return null;
				}
			}else if(c== '/'){
				read();
				if(c == '*'){
					return nested();
				}
			}else if(c == EOS){
				throw new TokenizerException("unclosed nesting comment");
			}
		}
	}

	private Token foundToken(Token.Type type) {
		prevToken._(currToken);
		return this.currToken = new Token(type);
	}

	private Token foundToken(Token.Type type, String string) {
		prevToken._(currToken);
		return this.currToken = new Token(type, string);
	}

	private Token foundToken(BigDecimal dec) {
		prevToken._(currToken);
		return this.currToken = new Token(Type.Num, dec);
	}

	private Token slashOp() throws IOException, TokenizerException {
		markAndRead(1);
		switch (c) {
		case '/':
			while(c != LF && c != CR){
				read();
			}
			return null; //trattasi di commenti inline come questo.
		case '*':
			return nested(); /* commento di questo tipo */
		default:
			reset();
			return foundToken(Type.Divide);
		}

	}

	private Token checkAssign() throws IOException {
		markAndRead(1);
		switch (c) {
		case '+':
			return foundToken(Type.AssignPlus);		
		case '-':
			return foundToken(Type.AssignMinus);	
		case '/':
			return foundToken(Type.AssignDivide);		
		case '*':
			return foundToken(Type.AssignMultiply);		
		default:
			reset();
			return foundToken(Type.Assign);		
		}
	}



	private Token opStart() throws IOException, TokenizerException{

		if(isOp())
			switch (c) {
			case '+':
				return foundToken(Type.Plus);		
			case '-':
				return foundToken(Type.Minus);
			case '*':
				return foundToken(Type.Multiply);		
			case '/':
				return slashOp();
			case '=':
				return checkAssign();
			default:
				break;
			}

		return null;
	}




	private Token idStart() throws IOException{
		if(isLetter()){
			while(isLetter()){
				builder.append(c);
				markAndRead(1);
			}
			reset();
			return foundToken(Type.Id, builder.toString());
		}
		return null;
	}

	private Token numStart() throws IOException{
		if(isDigit()){
			builder.append(c);
			if(c == '.'){
				numbers();
				return foundToken(new BigDecimal(builder.toString()));
			}else
		}
		return null;
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private void numbers() throws IOException {
		markAndRead(1);
		while(isDigit()){
			builder.append(c);
			markAndRead(1);
		}
		reset();
	}


private Token semicolon(){
	if(c == ';'){
		return foundToken(Type.Semicolon);
	}
	return null;
}



public Token next() throws IOException, TokenizerException{

	if(stepBack){
		stepBack = false;
		return currToken;
	}
	skipBlank();
	if(semicolon() != null){
		return currToken;
	}else if(numStart() != null){
		return currToken;
	}else if(opStart() != null){
		return currToken;
	}else if(idStart() != null){
		return currToken;	
	}else if(endOfStream() != null){
		return currToken;
	}
	throw new TokenizerException("no token found");
}




public Token prev() throws TokenizerException{
	if(stepBack) throw new TokenizerException("already in stepback");

	stepBack = true;
	return prevToken;
}



}

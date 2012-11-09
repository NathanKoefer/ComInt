package comint01.tokenizer;

import java.math.BigDecimal;

/**
 * 
 * @author dna
 *
 */
public class Token {

	public static enum Type {
		EOS, 
		Num, 
		Id,
		Plus, 
		Minus, 
		Multiply,
		Divide,
		Assign,
		AssignPlus,
		AssignMinus,
		AssignMultiply,
		AssignDivide,
		OpenBracket,
		CloseBracket,
		Equals,
		Differs,
		Semicolon,		
	};

	private Type type;
	private BigDecimal num;
	private String id;

	public Token(Type t){
		this.type = t;
		this.num = null;
		this.id = null;
	}

	public Token(Type t, String s){
		this.type = t;
		this.num = null;
		this.id = s;
	}

	public Token(Type t, BigDecimal n){
		this.type = t;
		this.num = n;
		this.id = null;
	}

	public void _(Token tok){
		this.id = tok.id;
		this.num = tok.num;
		this.type = tok.type;
	}


	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the num
	 */
	public BigDecimal getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(BigDecimal num) {
		this.num = num;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}

package comint01.interpreter;

import java.math.BigDecimal;
import java.lang.Exception;

/**
 * 
 * 
 * @author dna
 *
 */

public abstract class Expr {
	
	public static Expr _(String str) throws Exception {
		throw new Exception("Unimplemented method.");
	}
	
	public BigDecimal eval() throws Exception{
		return evaluate(new Environment());
	}
	
	protected abstract BigDecimal evaluate(Environment env) throws Exception;
}

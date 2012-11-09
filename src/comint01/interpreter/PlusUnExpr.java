package comint01.interpreter;
import java.math.BigDecimal;
import java.math.MathContext;


public class PlusUnExpr extends UnExpr {
	
	public PlusUnExpr(Expr expr) {
		super(expr);
	}

	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		return expr.evaluate(env).plus(MathContext.DECIMAL128);
	}
}

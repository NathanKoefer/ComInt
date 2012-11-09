package comint01.interpreter;
import java.math.BigDecimal;
import java.math.MathContext;


public class MinusUnExpr extends UnExpr {

	private MinusUnExpr(Expr expr) {
		super(expr);
	}
	
	public static MinusUnExpr _(Expr value){
		return new MinusUnExpr(value);
	}

	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		return expr.evaluate(env).negate(MathContext.DECIMAL128);
	}
}

package comint01.interpreter;
import java.math.BigDecimal;


public class TimesBinExpr extends BinExpr {
	
	public TimesBinExpr(Expr left, Expr right) {
		super(left, right);
	}

	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		return left.evaluate(env).multiply(right.evaluate(env));
	}
}

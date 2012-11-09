package comint01.interpreter;
import java.math.BigDecimal;


public class DivideBinExpr extends BinExpr {

	private DivideBinExpr(Expr left, Expr right) {
		super(left, right);
	}

	public static Expr _(Expr divisor, Expr dividend){
		return new DivideBinExpr(divisor, dividend);
	}

	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		BigDecimal bd = null;
		try {
			bd = left.evaluate(env).divide(right.evaluate(env));
		} catch (ArithmeticException e){
			throw new Exception("Division by zero!");
		}
		return bd;
	}
}

package comint01.interpreter;
import java.math.BigDecimal;


public class MinusBinExpr extends BinExpr {

	public MinusBinExpr(Expr left, Expr right) {
		super(left, right);
	}

	public static Expr _(Expr left, Expr right){
		return new MinusBinExpr(left, right);
	}
	
	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		return left.evaluate(env).subtract(right.evaluate(env));
	}
}

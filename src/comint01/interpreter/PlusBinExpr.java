package comint01.interpreter;
import java.math.BigDecimal;


public class PlusBinExpr extends BinExpr{
	
	public PlusBinExpr(Expr left, Expr right) {
		super(left, right);
	}

	public static Expr _(Expr left, Expr right){
		return new PlusBinExpr(left, right);
	}
	
	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		return left.evaluate(env).add(right.evaluate(env));
	}
}

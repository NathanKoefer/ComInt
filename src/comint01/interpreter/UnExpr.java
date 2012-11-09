package comint01.interpreter;
import java.math.BigDecimal;


public abstract class UnExpr extends Expr {

	protected Expr expr;
	
	public UnExpr(Expr expr){
		this.expr = expr;
	}
	
	@Override
	protected abstract BigDecimal evaluate(Environment env) throws Exception;

}

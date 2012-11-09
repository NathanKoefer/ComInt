package comint01.interpreter;
import java.math.BigDecimal;


public abstract class BinExpr extends Expr {

	protected Expr left;
	protected Expr right;
	
	public BinExpr(Expr left, Expr right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public abstract BigDecimal evaluate(Environment env) throws Exception;

}

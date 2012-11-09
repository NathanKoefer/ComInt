package comint01.interpreter;
import java.math.BigDecimal;


public class ExprSeq extends Expr {

	private Expr[] seq;
	private Exception LenghtExpr;

	private ExprSeq(Expr ... seq) throws Exception{
		if (seq.length == 0)
			throw this.LenghtExpr;
		this.seq = seq;
	}

	public static ExprSeq _(Expr ... seq) throws Exception{
		return new ExprSeq(seq);
	}

	@Override
	protected BigDecimal evaluate(Environment env) throws Exception{
		for (int i=0; i<seq.length-1; i++){
			seq[i].evaluate(env);
		}
		return seq[seq.length-1].evaluate(env);
	}

}

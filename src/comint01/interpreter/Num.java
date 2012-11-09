package comint01.interpreter;
import java.math.BigDecimal;

public class Num extends Expr{

	private BigDecimal value = null;
	
	/**
	 * Pattern used: Factory Method
	 * @param value
	 */
	private Num(BigDecimal value){
		this.value = value;
	}
	
	public static Expr _(BigDecimal dec){
		return new Num(dec);
	}
	
	@Override
	public BigDecimal evaluate(Environment env) {
		return this.value;
	}

}

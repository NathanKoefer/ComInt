package comint01.interpreter;
import java.math.BigDecimal;

public class Id extends Expr {

	private String c;

	private Id(String c){
		this.c = c;
	}

	public static Id _(String d){
		return new Id(d);
	}
	
	public String getChar(){
		return c;
	}

	@Override
	public BigDecimal evaluate(Environment env) throws Exception {
		BigDecimal temp = null;
		try {
			temp = env.get(this.c).evaluate(env);
		} catch (Exception e){
			throw new Exception("Invalid value for Id '" + this.c + "'");
		}
		return temp;
	}

}

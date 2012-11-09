package comint01.interpreter;
import java.math.BigDecimal;

public class AssignExpr extends Expr{

	private Id id;
	private Expr value;
	
	private AssignExpr(Id id, Expr value){
		this.id = id;
		this.value = value;
	}
	
	public static Expr _(Id id, Expr num){
		return new AssignExpr(id, num);
	}
	
	@Override
	public BigDecimal evaluate(Environment env) throws Exception{
		try {
			value.evaluate(env);
		} catch (Exception e) {
			throw new Exception("Circular assignement for variable " + id.getChar());
		}
		env.put(id.getChar(),value);
		return id.evaluate(env);
	}
}

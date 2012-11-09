package comint01.interpreter;
import java.util.HashMap;

public class Environment {

	private HashMap<String, Expr> env;

	public Environment(){
		this.env = new HashMap<String, Expr>();
	}
	
	public void put(String c, Expr v){
		env.put(c, v);
	}
	
	public Expr get(String c){
		return env.get(c);
	}
}

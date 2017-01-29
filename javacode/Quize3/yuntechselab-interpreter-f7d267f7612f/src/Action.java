
public class Action extends SyntaxItem {
	String action;
	
	public Action(String action) {
		this.action = action;
	}
	
	public boolean eval(Context c) {
		return true;
	}
}

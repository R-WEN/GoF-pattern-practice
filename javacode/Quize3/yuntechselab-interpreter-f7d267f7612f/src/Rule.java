
public class Rule extends Nonterminal{
	public Rule(SyntaxItem left , SyntaxItem right) {
		super(left, right);
	}
	
	public boolean eval(Context c){
		if(left.eval(c))
			return right.eval(c);
		return false;
	}
	
}

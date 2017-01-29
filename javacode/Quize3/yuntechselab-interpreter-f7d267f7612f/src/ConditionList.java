public class ConditionList extends Nonterminal {
	public ConditionList(SyntaxItem left , SyntaxItem right) {
		super(left, right);
	}
	public boolean eval(Context c) {
		if (right == null)
			return left.eval(c);
		return left.eval(c) && right.eval(c);
	}
}

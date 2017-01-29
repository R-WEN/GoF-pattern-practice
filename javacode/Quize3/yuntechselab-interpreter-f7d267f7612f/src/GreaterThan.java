public class GreaterThan extends Condition {
	public GreaterThan(SyntaxItem left , SyntaxItem right) {
		super(left, right);
	}
	
	public boolean eval(Context c) {
		if (left.val(c) > right.val(c))
			return true;
		return false;
	}
}

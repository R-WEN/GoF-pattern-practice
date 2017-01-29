
public class LessThanEqualTo extends Condition{
	public LessThanEqualTo(SyntaxItem left , SyntaxItem right) {
		super(left, right);
	}
	
	public boolean eval(Context c) {
		if (left.val(c) <= right.val(c))
			return true;
		return false;
	}
}

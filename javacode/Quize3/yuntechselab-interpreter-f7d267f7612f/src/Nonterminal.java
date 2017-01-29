public class Nonterminal extends SyntaxItem {
	SyntaxItem left;
	SyntaxItem right;
	
	public Nonterminal(SyntaxItem left, SyntaxItem right) {
		this.left = left;
		this.right = right;
	}
}

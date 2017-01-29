import java.util.HashMap;
import java.util.Map;


public class Main {

	public static void main(String[] args) {
		SyntaxItem girthRule = new LessThanEqualTo(new Var("pkg", "girth"), new Integer(130));		
		SyntaxItem girthCond = new ConditionList(girthRule, null);

		SyntaxItem lengthRule = new LessThanEqualTo(new Var("pkg", "length"), new Integer(108));
		SyntaxItem lengthCond = new ConditionList(lengthRule, girthCond);
		
		SyntaxItem wtRule = new LessThanEqualTo(new Var("pkg", "wt"), new Integer(150));
		SyntaxItem wtCond = new ConditionList(wtRule, lengthCond);
		
		SyntaxItem actionList = new ActionList(new Action("RateByWeight"), null);
		
		Rule rule = new Rule(wtCond, actionList);
		
		Context c = new Context();
		c.addPackage("pkg", new Package(200, 50, 120));
		boolean result = rule.eval(c);
		if (result) {
			System.out.println("R B W");
		} else {
			System.out.println("no RBW");
		}
	
	}

}

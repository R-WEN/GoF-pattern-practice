import java.util.Map;

public class Var extends Terminal {
	String key;
	String attr;
	
	public Var(String key, String attr) {
		this.key = key;
		this.attr = attr;
	}

	public int val(Context c) {
		// if (c.getMap().get(key) != null) {
		try {

			Package p = c.getMap().get(key);
			return p.getAttr(attr);
		} catch (Exception e) {
		}
		// }
		return 0;
	}
}

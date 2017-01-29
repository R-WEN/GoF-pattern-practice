import java.util.HashMap;
import java.util.Map;


public class Context {

	Map<String,Package> map = new HashMap<String, Package>();

	public Map<String, Package> getMap() {
		return map;
	}
	
	public void addPackage(String key, Package pkg) {
		map.put(key, pkg);
	}

	public void setMap(Map<String, Package> map) {
		this.map = map;
	}

	

}

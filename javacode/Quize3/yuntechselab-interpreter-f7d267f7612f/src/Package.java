public class Package {
	int weight;
	int length;
	int girth;
	
	public Package(int weight, int length, int girth) {
		this.weight = weight;
		this.length = length;
		this.girth = girth;
	}


	public int getAttr(String attr) {
		// TODO Auto-generated method stub
		if (attr.equals("wt")) {
			return weight;
		} else if (attr.equals("length")) {
			return length;
		} else if (attr.equals("girth")) {
			return girth;
		} else {
			return 0;
		}
	}
}

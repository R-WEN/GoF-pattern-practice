
public class LSP_Aggregation {
 public static void main(String[] args) {
     Bird C = new Crow(new C_Fly());
     Bird D = new Duck(new D_Fly());
     C.fly();
     D.fly();
}
}

abstract class FlyComponent {
	public abstract void fly();

}
class C_Fly extends FlyComponent {

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("I can fly");
	}
}
class D_Fly extends FlyComponent {

	@Override
	public void fly() {
		System.out.println("I can not fly");
	}
}


class Bird {
	
	private FlyComponent flycomponent;
	
	public Bird(FlyComponent flycomponent){
		this.flycomponent = flycomponent;
	}
	
	public void fly(){
		this.flycomponent.fly();
	}
}
class Crow extends Bird {
	public Crow(FlyComponent flycomponent){
        super(flycomponent);
        System.out.println("I'm Crow");
    }
}
class Duck extends Bird {
	public Duck(FlyComponent flycomponent){
        super(flycomponent);
        System.out.println("I'm Duck");
    }

}





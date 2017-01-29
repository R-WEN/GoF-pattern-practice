public class LSP_Delegation{
    public static void main(String[] args){
        Crow c=new Crow(new C_fly());
        c.fly();
        Duck d=new Duck(new D_fly());
        d.fly();
    }
}
interface Fly {
    public abstract void fly();
    
}
class D_fly implements Fly{
    @Override
    public void fly(){
        System.out.println("I can't fly.");
    }
}
class C_fly implements Fly{
    @Override
    public void fly(){
        System.out.println("I can fly.");
    }
}
abstract class Bird{
    private Fly flymethod;
    public Bird(Fly flymethod){
        this.flymethod=flymethod;
    }
    public void fly(){
        flymethod.fly();
    }
}
class Crow extends Bird{
    public Crow(Fly flymethod){
        super(flymethod);
        System.out.println("I'm Crow");
    }
}
class Duck extends Bird{
    public Duck(Fly flymethod){
        super(flymethod);
        System.out.println("I'm Duck.");
    }
}
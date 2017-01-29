import java.util.*;
public class Client{
    public static void main(String[] args){
        Supervisor[] s=new Supervisor[4];
        s[0]=new RESupervisor(new ClassDiagramBuilder());
        s[1]=new RESupervisor(new StateDiagramBuilder());
        s[2]=new BeautifySupervisor(new ClassDiagramBuilder());
        s[3]=new BeautifySupervisor(new StateDiagramBuilder());
        Diagram d;
        for (Supervisor o:s){
            o.construct();
            d=o.getProduct();
            System.out.println(d.product);
        }
        
    }
}
interface Supervisor{
    public void construct();
    public Diagram getProduct();
}
class RESupervisor implements Supervisor{
    private Builder b;
    private Diagram d;
    public RESupervisor(Builder b){
        this.b=b;
    }
    public void construct(){
        b.parse(new Code("RE code"));
        b.layout();
        b.display();
        d=b.getProduct();
    }
    public Diagram getProduct(){
        return d;
    }
}
class BeautifySupervisor implements Supervisor{
    private Builder b;
    private Diagram d;
    public BeautifySupervisor(Builder b){
        this.b=b;
    }
    public void construct(){
        b.parse(new Code("Beautify code"));
        b.display();
        b.layout();
        d=b.getProduct();
    }
    public Diagram getProduct(){
        return d;
    }
}
interface Builder{
    public void parse(Code c);
    public void layout();
    public void display();
    public Diagram getProduct();
}
class ClassDiagramBuilder implements Builder{
    private Diagram d=new Diagram();
    public Diagram getProduct(){
        return d;
    }
    public void parse(Code c){
        d.product +=(c.code);
    }
    public void layout(){
        d.product += " ClassLayout";
    }
    public void display(){
        d.product += " ClassDisplay";
    }
}
class StateDiagramBuilder implements Builder{
    private Diagram d=new Diagram();
    public Diagram getProduct(){
        return d;
    }
    public void parse(Code c){
        d.product +=(c.code);
    }
    public void layout(){
        d.product += " StateLayout";
    }
    public void display(){
        d.product += " StateDisplay";
    }
}
class Diagram{
    public String product="";
}

class Code{
    public String code;
    public Code(String s){
        code=s;
    }
}
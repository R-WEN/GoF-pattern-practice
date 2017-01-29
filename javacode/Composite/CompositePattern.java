import java.util.*;

abstract class componet{
    public abstract void get();
    public abstract void add(componet e);
    public abstract void remove(componet e);
}
class composite extends componet{

    private List<componet> elements=new ArrayList<componet>();
    @Override
    public void add(componet e){
        elements.add(e);
    }
    public void get(){
        for (componet e : elements){
            e.get();
        }
    }
    public void remove(componet e){
        elements.remove(e);
    }
}
class leaf extends componet{
    private String element;
    
    public leaf(String e){
        element=e;
    }
    public void add(componet e){}
    
    public void get(){
        System.out.println(element);
    }
    public void remove(componet e){}
    
}
public class CompositePattern{
    public static void main(String[] atgs){
        
        componet A=new composite();
        A.add(new leaf("A1"));
        A.add(new leaf("A2"));
        A.get();
        componet B=new composite();
        componet B1=new leaf("B1");
        componet B2=new leaf("B2");
        B.add(B1);
        B.add(B2);
        A.add(B);
        A.get();
        System.out.println();
        B.remove(B1);
        A.get();
        A.remove(B);
        A.get();
    }
}
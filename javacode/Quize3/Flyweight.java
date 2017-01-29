import java.util.*;
public class Flyweight{
    public static void main(String[] args){
        fwf factory=new fwf();
        fw f=factory.factory('A');
        f.operation("RED");
        
        
    }
}
interface fw{
    public void operation(String state);
}
class flw implements fw{
    private Character c=null;
    flw(Character c){
        this.c=c;
    }
    public void operation(String state){
        System.out.println("Extrinsic :"+state);
        System.out.println("Intrinsic :"+c);
    }
}
class fwf{
    private Map<Character,fw> files=new HashMap<Character,fw>();
    public fw factory(Character c){
        fw f=files.get(c);
        if (f==null){
            f=new flw(c);
            files.put(c,f);
        }
        return f;
    }
}
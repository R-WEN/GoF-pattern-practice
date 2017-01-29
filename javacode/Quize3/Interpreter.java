import java.util.*;
public class Interpreter{
    public static void main(String[] args){
        
        Evaluator evaluator=new Evaluator();
        Experssion Handle=evaluator.evaluate();
        Map<String,String> context=new HashMap<String,String>();
        Scanner scan=new Scanner(System.in);
        String s="";
        System.out.println("請輸入決策：(Ex:Y N - -) 輸入0結束");
        s=scan.nextLine();
        while((!s.equals("0"))){
        
        String[] s2=s.split(" ");
        context.put("promoted",s2[0]);
        context.put("raise",s2[1]);
        context.put("workinterest",s2[2]);
        context.put("ownoffice",s2[3]);
        result(Handle.interpret(context));
        s=scan.nextLine();
        }
    }
    public static void result(boolean b){
        if (b){
            System.out.println("Stay.");
        }else{
            System.out.println("Quit.");
        }
    }
}
interface Experssion{
    public boolean interpret(Map<String,String> context);
}
class promoted implements Experssion{
    private String name="promoted";
    private Experssion y;
    private Experssion n;
    promoted(Experssion y,Experssion n){
        this.y=y;
        this.n=n;
    }
    public boolean interpret(Map<String,String> context){
        
        if (("Y").equals(context.get(name))){
            return y.interpret(context);
        }else{
            return n.interpret(context);
        }
    }
}
class raise implements Experssion{
    private String name="raise";
    private Experssion y;
    private Experssion n;
    raise(Experssion y,Experssion n){
        this.y=y;
        this.n=n;
    }
    public boolean interpret(Map<String,String> context){
        if (("Y").equals(context.get(name))){
            return y.interpret(context);
        }else{
            return n.interpret(context);
        }
    }
}
class workinterest implements Experssion{
    private String name="workinterest";
    private Experssion y;
    private Experssion n;
    workinterest(Experssion y,Experssion n){
        this.y=y;
        this.n=n;
    }
    public boolean interpret(Map<String,String> context){
        if (("Y").equals(context.get(name))){
            return y.interpret(context);
        }else{
            return n.interpret(context);
        }
    }
}
class ownoffice implements Experssion{
    private String name="ownoffice";
    private Experssion y;
    private Experssion n;
    ownoffice(Experssion y,Experssion n){
        this.y=y;
        this.n=n;
    }
    public boolean interpret(Map<String,String> context){
        if (("Y").equals(context.get(name))){
            return y.interpret(context);
        }else{
            return n.interpret(context);
        }
    }
}
class stay implements Experssion{
    public boolean interpret(Map<String,String> context){
        return true;
    }
}
class quit implements Experssion{
    public boolean interpret(Map<String,String> context){
        return false;
    }
}
class Evaluator{
    public Experssion evaluate(){
        Experssion r = new raise(new stay(),new quit());
        Experssion wi=new workinterest(new raise(new stay(),new quit()),new ownoffice(new stay(),new quit()));
        Experssion p=new promoted(r,wi);
        return p;
    }
}
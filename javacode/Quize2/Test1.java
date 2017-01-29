import java.util.*;
import java.lang.*;
public class Test1 {
    public static void main(String[] args){
        DiagramElement a=new StateDiagram();
        a.add(new State());
        a.add(new Transition());
        System.out.println(a.getElement());
        DiagramElement b=new State();
        b.add(new State());
        b.get();
        DiagramElement c=new Transition();
        SyntaxCheck sc=new SyntaxCheck();
        RelationCheck rc=new RelationCheck();
        a.accept(sc);
        a.accept(rc);
    }
}
abstract class DiagramElement{
    abstract void accept(Checker c);
    public ArrayList<DiagramElement> getElement(){
        try{
            throw new UnsupportedOperationException("This Element can't do that.");}
        catch(UnsupportedOperationException e){
            System.out.println("This Element can't do that.");
        }
        return null;
    }
    public void add(DiagramElement d){
        try{
            throw new UnsupportedOperationException("This Element can't do that.");}
        catch(UnsupportedOperationException e){
            System.out.println("This Element can't do that.");
        }
        
    }
    public void remove(DiagramElement d){
        try{
            throw new UnsupportedOperationException("This Element can't do that.");}
        catch(UnsupportedOperationException e){
            System.out.println("This Element can't do that.");
        }
        
    }
    public void get(){
        try{
            throw new UnsupportedOperationException("This Element can't do that.");}
        catch(UnsupportedOperationException e){
            System.out.println("This Element can't do that.");
        }
        
    }
}
class StateDiagram extends DiagramElement{
    private ArrayList<DiagramElement> e=new ArrayList<DiagramElement>();
    
    public void accept(Checker c){
        c.check(this);
        Iterator<DiagramElement> itr=e.iterator();
        while(itr.hasNext()){
            DiagramElement td=itr.next();
            td.accept(c);
        }
        
    }
    public ArrayList<DiagramElement> getElement(){
        return e;
    }
    public void add(DiagramElement d){
        e.add(d);
    }
    public void remove(DiagramElement d){
        e.remove(d);
    }
    
}
class State extends DiagramElement{
    public void accept(Checker c){
        c.check(this);
    }
    public void get(){
        System.out.println(this);
    }
    
}
class Transition extends DiagramElement{
    public void accept(Checker c){
        c.check(this);
    }
    public void get(){
        System.out.println(this);
    }

}
interface Checker{
    public void check(State s);
    public void check(Transition t);
    public void check(StateDiagram sd);
}
class SyntaxCheck implements Checker{
    public void check(State s){
        System.out.println("SyntexCheck State "+s);
    }
    public void check(Transition t){
        System.out.println("SyntexCheck Transition "+t);
    }
    public void check(StateDiagram sd){
        System.out.println("SyntexCheck StateDiagram "+sd);
    }
}
class RelationCheck implements Checker{
    public void check(State s){
        System.out.println("RelationCheck State "+s);
    }
    public void check(Transition t){
        System.out.println("RelationCheck Transition "+t);
    }
    public void check(StateDiagram sd){
        System.out.println("RelationCheck StateDiagram "+sd);
    }
}
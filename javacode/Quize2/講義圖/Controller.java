import java.util.*;
import java.lang.*;
public class Controller {
    public static void main(String[] args){
        DiagramElement a=new StateDiagram();
        a.add(new State());
        a.add(new Transition());
        SyntaxCheck sc=new SyntaxCheck();
        RelationCheck rc=new RelationCheck();
        a.accept(sc);
        a.accept(rc);
    }
}
abstract class DiagramElement{
    abstract void accept(Checker c);
    public ArrayList<DiagramElement> getElement(){
        throw new UnsupportedOperationException("This Element can't do that.");
    }
    public void add(DiagramElement d){
        throw new UnsupportedOperationException("This Element can't do that.");
    }
    public void remove(DiagramElement d){
        throw new UnsupportedOperationException("This Element can't do that.");
        
    }
    public void get(){
        throw new UnsupportedOperationException("This Element can't do that.");
        
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
abstract class Checker{
    abstract void check(State s);
    abstract void check(Transition t);
    abstract void check(StateDiagram sd);
}
class SyntaxCheck extends Checker{
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
class RelationCheck extends Checker{
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
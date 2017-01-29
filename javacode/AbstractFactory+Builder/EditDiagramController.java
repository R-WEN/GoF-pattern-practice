import java.awt.*;
import java.lang.*;
import java.util.*;
public class EditDiagramController {
    public static void main(String[] args) {
        AbstractFactory a=new UML1Factory();
        AbstractFactory b=new UML2Factory();
        DiagramElement sd1=a.createDiagram();
        DiagramElement sd2=b.createDiagram();
        
        sd1.draw();
  
        sd2.draw();
    }
}
interface Supervisor{
    public DiagramElement getDiagram();
    public void construct();
}
class UMLSupervisor implements Supervisor{
    private Builder b;
    public UMLSupervisor(Builder b){
        this.b=b;
    }
    public void construct(){
        b.State();
        b.Transition();
    }
    public DiagramElement getDiagram(){
        return b.getDiagram();
    }
}
interface Builder {
    public void StateDiagram();
    public void State();
    public void Transition();
    public DiagramElement getDiagram();
}
class UML1Builder implements Builder{
    private DiagramElement d=new UML1StateDiagram();
    public void StateDiagram(){
        d.add(new UML1StateDiagram());
    }
    public void State(){
        d.add(new UML1State());
    }
    public void Transition(){
        d.add(new UML1Transition());
    }
    public DiagramElement getDiagram(){
        return d;
    }
}
class UML2Builder implements Builder{
    private DiagramElement d=new UML2StateDiagram();
    public void StateDiagram(){
        d.add(new UML2StateDiagram());
    }
    public void State(){
        d.add(new UML2State());
    }
    public void Transition(){
        d.add(new UML2Transition());
    }
    public DiagramElement getDiagram(){
        return d;
    }
}

abstract class DiagramElement{
    abstract void draw();
    public void add(DiagramElement e){
        throw new UnsupportedOperationException();
    }
    public void remove(DiagramElement e){
        throw new UnsupportedOperationException();
    }
    
}
abstract class StateDiagram extends DiagramElement{
    protected ArrayList<DiagramElement> elements=new ArrayList<DiagramElement>();
    public void add(DiagramElement e){
        elements.add(e);
    }
    public void remove(DiagramElement e){
        elements.remove(e);
    }
}
class UML1StateDiagram extends StateDiagram{
    
    public void draw(){
        System.out.println("I'm UML1 StateDiagram: "+this);
        Iterator<DiagramElement> itr=elements.iterator();
        while(itr.hasNext()){
            DiagramElement te=itr.next();
            te.draw();
        }
    }

}
class UML2StateDiagram extends StateDiagram{
    public void draw(){
        System.out.println("I'm UML2 StateDiagram: "+this);
        Iterator<DiagramElement> itr=elements.iterator();
        while(itr.hasNext()){
            DiagramElement te=itr.next();
            te.draw();
        }
    }

}
abstract class State extends DiagramElement{

}
class UML1State extends State{
    public void draw(){
        System.out.println("Draw a UML1State: "+this);
    }
}
class UML2State extends State{
    public void draw(){
        System.out.println("Draw a UML2State: "+this);
    }
}
abstract class Transition extends DiagramElement{

}
class UML1Transition extends Transition{
    public void draw(){
        System.out.println("Draw a UML1Transition: "+this);
    }
}
class UML2Transition extends Transition{
    public void draw(){
        System.out.println("Draw a UML2Transition: "+this);
    }
}

interface AbstractFactory{
    public State createState();
    public Transition createTransition();
    public StateDiagram createDiagram();
}
class UML1Factory implements AbstractFactory{
    private Supervisor s =new UMLSupervisor(new UML1Builder());

    public State createState(){
        return new UML1State();
    }
    public Transition createTransition(){
        return new UML1Transition();
    }
    public StateDiagram createDiagram(){
        s.construct();
        return (StateDiagram)s.getDiagram();
    }
}
class UML2Factory implements AbstractFactory{
    private Supervisor s =new UMLSupervisor(new UML2Builder());
    public State createState(){
        return new UML2State();
    }
    public Transition createTransition(){
        return new UML2Transition();
    }
    public StateDiagram createDiagram(){
        s.construct();
        return (StateDiagram)s.getDiagram();
    }
}
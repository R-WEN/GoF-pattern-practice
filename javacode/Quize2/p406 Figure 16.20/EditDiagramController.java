import java.awt.*;
import java.lang.*;
import java.util.*;
public class EditDiagramController {
    public static void main(String[] args) {
        AbstractFactory a=new UML1Factory();
        AbstractFactory b=new UML2Factory();
        DiagramElement sd1=a.createDiagram();
        DiagramElement sd2=b.createDiagram();
        sd1.add(a.createState());
        sd1.add(a.createTransition());
        sd1.draw();
        sd2.add(b.createState());
        sd2.add(b.createTransition());
        sd2.draw();
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
    public State createState(){
        return new UML1State();
    }
    public Transition createTransition(){
        return new UML1Transition();
    }
    public StateDiagram createDiagram(){
        return new UML1StateDiagram();
    }
}
class UML2Factory implements AbstractFactory{
    public State createState(){
        return new UML2State();
    }
    public Transition createTransition(){
        return new UML2Transition();
    }
    public StateDiagram createDiagram(){
        return new UML2StateDiagram();
    }
}
import java.util.*;
public class ConpositeDecorator{
    public static void main(String[] args){
        DiagramElement s=new State();
        DiagramElement sd=new StateDiagram();
        DiagramElement t=new Transition();
        ((StateDiagram)sd).add(s);
        ((StateDiagram)sd).add(new Border(new Scroller(t)));
        sd.draw();
        
    }
}
interface DiagramElement {
    public void draw();
}
class State implements DiagramElement{
    public void draw(){
        System.out.println("Draw State.");
    }
}
class Transition implements DiagramElement{
    public void draw(){
        System.out.println("Draw Transition.");
    }
}
class StateDiagram implements DiagramElement{
    private ArrayList<DiagramElement> elements=new ArrayList<DiagramElement>();
    public void add(DiagramElement e){
        elements.add(e);
    }
    public void remove(DiagramElement e){
        elements.remove(e);
    }
    public void draw(){
        System.out.println("Draw StateDiagram.");
        Iterator<DiagramElement> t=elements.iterator();
        while (t.hasNext()){
            DiagramElement e=t.next();
            e.draw();
        }
    }
}
abstract class Decorator implements DiagramElement{
    private DiagramElement e;
    Decorator(DiagramElement e){
        this.e=e;
    }
    public void draw(){
        e.draw();
    }
}
class Scroller extends Decorator{
    Scroller(DiagramElement e){
        super(e);
    }
    public void draw(){
        super.draw();
        drawScroller();
    }
    public void drawScroller(){
        System.out.println("Draw Scroller.");
    }
}
class Border extends Decorator{
    Border(DiagramElement e){
        super(e);
    }
    public void draw(){
        super.draw();
        drawBorder();
    }
    public void drawBorder(){
        System.out.println("Draw Border.");
    }
}
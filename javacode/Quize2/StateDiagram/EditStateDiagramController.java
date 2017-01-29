import java.awt.*;
import java.util.*;


abstract class DiagramElement{
    public String name;
    public Point loc;
    abstract void draw(Graphics g);
    public boolean intersect(Point p){
        return true;
    }
    public void add(DiagramElement e){
        System.out.println("I can't do that.");
    }
    public DiagramElement get(){
        System.out.println("I can't do that.");
        return null;
    }
    public void remove(DiagramElement e){
        System.out.println("I can't do that.");
    }
    public DiagramElement get(String name){
        System.out.println("I can't do that.");
        return null;
    }
}
class StateDiagram extends DiagramElement{
    public String Path;
    private ArrayList<DiagramElement> des=new ArrayList<DiagramElement>();
    public void draw(Graphics g){
        //g.drawString(name,....);
        Iterator<DiagramElement> itr = des.iterator();
        while (itr.hasNext()){
            DiagramElement e=itr.next();
            e.draw(g);
        }
    }
    public boolean instrsect(Point p){
        //do StateDiagram instrsect decide 
        //we presume it is true
        return true;
    }
    public void add(DiagramElement e){
        des.add(e);
    }
    public void remove(DiagramElement e){
        des.remove(e);
    }
    public DiagramElement get(String name){
        Iterator<DiagramElement> itr = des.iterator();
        DiagramElement oute;
        boolean checknull=true;
        while (itr.hasNext()){
            DiagramElement e=itr.next();
            if (name.equals(e.name)){
                oute=e;
                checknull=false;
            }
        }
        if ( checknull){
                System.out.println("Can't find this Element.");
        }
        return oute;
    }
}
class State extends DiagramElement{
    public void draw(Graphics g){
        //draw State
        System.out.println("Draw a State.");
    }
    public boolean intersect(Point p){
        //do State instrsect decide 
        //we presume it is true
        return true;
    }

}
class Transition extends DiagramElement{
    public void draw(Graphics g){
        //draw Transition
        System.out.println("Draw a Transition.");
    }
    public boolean instrsect(Point p){
        //do Transition instrsect decide 
        //we presume it is true
        return true;
    }

}

public class EditStateDiagramController {
    public static void main(String[] args){
        DiagramElement a=new StateDiagram();
        a.add(new State());
        a.add(new Transition());
    }
}

import java.util.*;
public class EditController{
    public static void main(String[] args){
        StateDiagram sd=new StateDiagram();
        sd.setMemento(new Memento());
        sd.lastStep();
        sd.BackUp();
        System.out.println(sd.state);
        sd.setState("!!!!!!!!!!!!!!");
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
        sd.setState("111");
        sd.BackUp();
        sd.setState("222");
        sd.BackUp();
        sd.setState("333");
        sd.BackUp();
        sd.lastStep();
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
        sd.lastStep();
        System.out.println(sd.state);
    }
}
class StateDiagram{
    public String state="Default";
    private Memento memento;
    public void BackUp(){
        memento.backup(new DiagramState(state));
    }
    public void setMemento(Memento m){
        memento=m;
    }
    public void setState(String s){
        state=s;
    }
    public void lastStep(){
        DiagramState d=memento.getbackup();
        if (d==null){
            System.out.println("no last step!");
        }else{
            setState(d.getState());
        }
    }
}
class DiagramState{
    private String state;
    public DiagramState(String state){
        this.state=state;
    }
    public String getState(){
        return state;
    }

    
}
class Memento{
    ArrayList<DiagramState> bklist=new ArrayList<DiagramState>();
    public void backup(DiagramState ds){
        bklist.add(ds);
    }
    public DiagramState getbackup(){
        DiagramState d=null;
        if (!bklist.isEmpty()){
            d=bklist.get(bklist.size()-1);
            bklist.remove(d);
        }
        return d;
    }
    
}
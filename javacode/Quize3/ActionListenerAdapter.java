import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class ActionListenerAdapter{
    public static void main(String[] args){
        EditorGUI view=new EditorGUI();
        EditDiagramController controller=new EditDiagramController(view,new StateDiagram());
        view.setVisible(true);
    }
}
class EditorGUI extends JFrame implements Observer{
    private JButton stateButton=new JButton("State");
    private JButton transButton=new JButton("Trans");
    private JTextArea diagram=new JTextArea();
    public EditorGUI(){
        JPanel EditorPanel=new JPanel();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100,100,668,453);
        stateButton.setActionCommand("drawState");
        EditorPanel.add(stateButton);
        transButton.setActionCommand("drawTrans");
        EditorPanel.add(transButton);
        EditorPanel.add(diagram);
        this.add(EditorPanel);
    }
    public void addStateButtonListener(ActionListener l){
        stateButton.addActionListener(l);
    }
    public void addTransButtonListener(ActionListener l){
        transButton.addActionListener(l);
    }
    public void update(Observable obs,Object obj){
        diagram.setText(((StateDiagram)obs).draw());   
    }
}

class EditDiagramController{
    private EditorGUI view;
    private DiagramElement sd;
    public EditDiagramController(EditorGUI v,DiagramElement sd){
        view=v;
        this.sd=sd;
        sd.addObserver(v);
        view.addStateButtonListener(new StateButtonListener(this));
        view.addTransButtonListener(new TransButtonListener(this));
    }
    public void stateBtnClicked(ActionEvent e){
        System.out.println(e.getActionCommand());
        DiagramElement s=new State();
        sd.add(s);
    }
    public void transBtnClicked(ActionEvent e){
        System.out.println(e.getActionCommand());
        DiagramElement t=new Trans();
        sd.add(t);
    }
}

class StateButtonListener implements ActionListener{
    private EditDiagramController controller;
    public StateButtonListener(EditDiagramController controller){
        this.controller=controller;
    }
    public void actionPerformed(ActionEvent e){
        controller.stateBtnClicked(e);
    }
}
class TransButtonListener implements ActionListener{
    private EditDiagramController controller;
    public TransButtonListener(EditDiagramController controller){
        this.controller=controller;
    }
    public void actionPerformed(ActionEvent e){
        controller.transBtnClicked(e);
    }
}
abstract class DiagramElement extends Observable{
    public abstract String draw();
    public void add(DiagramElement e){
        throw new UnsupportedOperationException();
    }
    public void remove(DiagramElement e){
        throw new UnsupportedOperationException();
    }
    
}
class State extends DiagramElement{
    public String draw(){
        return "Draw State.";
    }
}
class Trans extends DiagramElement{
    public String draw(){
        return "Draw Trans.";
    }
}
class StateDiagram extends DiagramElement{
    private ArrayList<DiagramElement> elements=new ArrayList<DiagramElement>();
    public void add(DiagramElement e){
        elements.add(e);
        this.setChanged();
        this.notifyObservers();
    }
    public void remove(DiagramElement e){
        elements.remove(e);
    }
    public String draw(){
        String s="";
        Iterator<DiagramElement> itr=elements.iterator();
        while(itr.hasNext()){
            DiagramElement e=itr.next();
            s+=e.draw()+"\n";
        }
        return s;
    }
}
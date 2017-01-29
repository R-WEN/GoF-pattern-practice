package mvc.view;
import javax.swing.*;
import java.awt.event.*;
public class Authen extends JFrame{
    private JLabel title=new JLabel("Your Answer:");
    private JTextField answer =new JTextField(10);
    private JButton checkbutton=new JButton("Answer The Question");
    public Authen(){
        JPanel ansPanel= new JPanel();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(200,200);
        ansPanel.add(title);
        ansPanel.add(answer);
        ansPanel.add(checkbutton);
        this.add(ansPanel);
    }
    public String getAns(){
        return answer.getText();
    }
    public void setbuttonListener(ActionListener listener){
        checkbutton.addActionListener(listener);
    }
    public void message(String m){
        JOptionPane.showMessageDialog(this,m);
    }
    public void close(){
        this.dispose();
    }
    
}
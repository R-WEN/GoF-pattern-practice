package mvc.view;
import javax.swing.*;
import java.awt.event.*;

public class ResetPW extends JFrame{
    private JLabel uid=new JLabel("input UID");
    private JTextField insert_uid = new JTextField(10);
    private JLabel question=new JLabel("");
    private JButton getQ=new JButton("GetQuestion");
    private String q;
    public ResetPW(){
        JPanel QPanel=new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,200);
        QPanel.add(uid);
        QPanel.add(insert_uid);
        QPanel.add(getQ);
        QPanel.add(question);
        this.add(QPanel);
        
    }
    public void addQbuttonListener(ActionListener QbuttonListener){
        getQ.addActionListener(QbuttonListener);
    }
    public String getuserid(){
        return insert_uid.getText();
    }
    public void setQ(String Q){
        question.setText(Q);
    }
}

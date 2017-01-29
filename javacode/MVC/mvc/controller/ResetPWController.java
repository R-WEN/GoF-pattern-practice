package mvc.controller;
import java.awt.event.*;
import mvc.view.*;
import mvc.model.*;
public class ResetPWController{
    private ResetPW QView;
    private DBMgr model;
    private Authen ansView;
    private User user;
    public ResetPWController(ResetPW QView,DBMgr model,Authen ansView){
        this.QView=QView;
        this.model=model;
        this.ansView=ansView;
        this.QView.addQbuttonListener(new QuestionListener());
        this.ansView.setbuttonListener(new AnswerListener());
    }
    
    
    public String getQuestion(String uid){
        user=model.getUser(uid);
        return user.getQuest();
    }
    public String checkAns(String answer){
        
        return user.checkAns(answer);
    }
    
    class QuestionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            String id=QView.getuserid();
            QView.setQ(getQuestion(id));
            ansView.setVisible(true);
        }
    }
    class AnswerListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            String ans=ansView.getAns();
            ansView.message(checkAns(ans));
        }
    }
}

package mvc.model;
public class User{
    private String uid;
    private String answer;
    private String question;
    private String msg;
    public User(String uid,String answer,String question){
        this.uid=uid;
        this.answer=answer;
        this.question=question;
    }
    public String getQuest(){
        return question;
    }
    public String checkAns(String answer){
        if (answer.equals(this.answer)){
            msg="You got it";
        }else{
            msg="Mistake";
        }
        return msg;
    }
}
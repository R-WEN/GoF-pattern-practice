package mvc.model;

public class DBMgr{
    private String answer;
    private String question;
    public User getUser(String uid){
        //query database to get answer & question
        question="Test1 answer:1234";
        answer="1234";
        User user=new User(uid,answer,question);
        return user;
    }
}

import mvc.view.*;
import mvc.controller.*;
import mvc.model.*;
public class TestMVC{
    public static void main(String[] args){
        ResetPW Qview=new ResetPW();
        Authen AnsView=new Authen();
        ResetPWController controller=new ResetPWController(Qview,new DBMgr(),AnsView);
        Qview.setVisible(true);
        
    }
}
import java.util.*;

class Backup {
    final String state;
    final Date date;
    Backup(String state) {
        this.state = state;
        this.date = new Date();
    }
}

class Application {
    private String state = "default setting";
    
    Backup backup() {
        return new Backup(state);
    }
    void recover(Backup backup) {
        this.state = backup.state;
    }
    
    void setState(String state) {
        this.state = state;
    }
    String getState() {
        return state;
    }
}

class Recovery {
    private List<Backup> backups = new ArrayList<Backup>();
    void add(Backup backup) {
        backups.add(backup);
    }
    Backup retrieve(Date date) {
        for(Backup backup : backups) {
            if(backup.date.equals(date)) {
                backups.remove(backup);
                return backup;
            }
        }
        return null;
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Application application = new Application();
        Recovery recovery = new Recovery();
        
        System.out.println(application.getState());
        
        Backup backup = application.backup();  // 建立備忘
        recovery.add(backup); // 加入備忘錄
        
        application.setState("customer setting");
        System.out.println(application.getState());
        
        Date date = backup.date; // 假設 date 是使用者自行設定所要取得的還原時間！
        application.recover(recovery.retrieve(date)); // 取得備忘來還原
        System.out.println(application.getState());
    }
}
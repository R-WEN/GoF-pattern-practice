
public class MyMain {
 public static void main(String[] args) {
Singleton obj = Singleton.getInstance();
Singleton obj2 = Singleton.getInstance();
     Singleton obj3 = Singleton.getInstance();
     Singleton obj4 = Singleton.getInstance();
System.out.println(Singleton.scount);
System.out.println(obj2.icount);
}
}


class Singleton {
private static Singleton obj = new Singleton();
public static int scount;
public int icount;
    private Singleton() {
    scount++;
    icount++;
    }
    public static Singleton getInstance() {
    return obj;
    }
}




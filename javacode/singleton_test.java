
public class singleton_test{
    public static void main(String[] args){
        singleton a=new singleton();
        System.out.println(Integer.toHexString(System.identityHashCode(a)));
        singleton b=new singleton();
        System.out.println(Integer.toHexString(System.identityHashCode(b)));

    }
}

class singleton{
    private int data;
}

public class singleton_eager{
    public static void main(String[] args){
        singleton a=singleton.getInstance();
        System.out.println(Integer.toHexString(System.identityHashCode(a)));
        singleton b=singleton.getInstance();
        System.out.println(b.toString());

    }
}

class singleton{
    private static singleton obj=new singleton();
    private singleton(){}
    private int data;
    public static singleton getInstance(){
        return obj;
    }
    public void setData(int data){
        this.data=data;
    }
    public int getData(){
        return data;
    }
}
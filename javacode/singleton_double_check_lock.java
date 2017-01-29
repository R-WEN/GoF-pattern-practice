public class singleton_double_check_lock{
    public static void main(String[] args){
        singleton a= singleton.getInstance();
        System.out.println(Integer.toHexString(System.identityHashCode(a)));
        singleton b= singleton.getInstance();
        System.out.println(Integer.toHexString(System.identityHashCode(b)));
    }
}
class singleton{
    private static singleton obj=null;
    private singleton(){}
    public static singleton getInstance(){
        if (obj==null){
            synchronized(singleton.class){
                if (obj==null){
                    obj=new singleton();
                }
                
            }
        }
        return obj;
    }
}
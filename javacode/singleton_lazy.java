public class singleton_lazy{
    public static void main(String[] args) {
        singleton a=singleton.getInstance();
        System.out.println("A:data:"+a.getData());
        a.setData(5);
        singleton b=singleton.getInstance();
        System.out.println("A data:"+a.getData());
        System.out.println("B data:"+b.getData());
    }
}

class singleton{
    private static singleton obj=null;
    private singleton(){}
    private int data;
    public static singleton getInstance(){
        if (obj==null){
            obj=new singleton();
        }
        return obj;
    }
    public void setData(int data){
        this.data=data;
    }
    public int getData(){
        return data;
    }
    
}
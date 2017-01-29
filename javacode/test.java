
public class test {
    public static void main(String[] args){
        Animal a=new Bird();
        Bird b=new Bird();
        
        a.go();
        a.call();//會出現錯誤 因為是父類別型態，沒有call()方法
        b.go();
        b.call();
        
    }
}

abstract class Animal{
    abstract public void go();
}

class Bird extends Animal{
    public void go(){
        System.out.println("我是鳥在走..");
    }
    public void call(){
        System.out.println("叫我叫我");
    }
}
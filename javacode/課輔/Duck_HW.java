public class Duck_HW {
    public static void main(String[] args){
        Duck duck1=new Duck("J","red",5);
        Duck duck2=new Duck("B","red",15);
        Duck duck3=new FlyDuck("FJ","blue",25);
        Duck duck4=new SwimDuck("SJ","green",35);
        Duck duck5=new FlyDuck("FB","blue",45);
        killDuck(duck1);
        killDuck(duck2);
        killDuck(duck3);
        killDuck(duck4);
        killDuck(duck5);
    }
    public static void killDuck(Duck d){
        if(d.getWeight()>20){
            System.out.println(d.getName());
        }
    }
}
class Duck {
    private String name;
    private String color;
    private int weight;
    Duck(String n,String c,int w){
        name=n;
        color=c;
        weight=w;
    }
    public String getName(){
        return name;
        
    }
    public String getColor(){
        return color;
    }
    public int getWeight(){
        return weight;
    }
}
class FlyDuck extends Duck{
    FlyDuck(String n,String c,int w){
        super(n,c,w);
    }
}
class SwimDuck extends Duck{
    SwimDuck(String n,String c,int w){
        super(n,c,w);
    }

}
public class B10223041{
    public static void main(String[] args){
        Student me=new Student("黃正文","blue",(double)68.8,170); 
        System.out.println("Name:"+me.getName()+"\n"+"Color:"+me.getColor()+"\n"+"Weight:"+me.getWeight()+"\n"+"Height:"+me.getHeight());
    }
}

class Student{
    private String name;
    private String color;
    private double weight;
    private int height;
    public Student(String a,String b,double c,int d){
        name=a;
        color=b;
        weight=c;
        height=d;
    }
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }
    public double getWeight(){
        return weight;
    }
    public int getHeight(){
        return height;
    }
}
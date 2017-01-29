import java.util.ArrayList;
import java.util.EmptyStackException;
public class StackAndList{
    public static void main(String[] args){
        Stack st=new Stack();
        st.push("1");
        st.push("2");
        st.push("3");
        try{
            String os=st.top();
            while (!st.isEmpty()){
                System.out.println(os);
                os=st.pop();
            }
            System.out.println(os);
            System.out.println(st.pop());
        }
        catch(Exception e){
            System.out.println(e);
        }
    System.out.println(st.toString());
    }
}
class Stack{
    private ArrayList<String> ls =new ArrayList<String>();
    public void push(String s){
        ls.add(s);
    }
    public String pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }else{
            String s=ls.get(ls.size() - 1);
            ls.remove(s);
            return s;
        }
    }
    public boolean isEmpty(){
        return ls.isEmpty();
    }
    public String top(){
        if(isEmpty()){
            throw new EmptyStackException();
        }else{
            String s=ls.get(ls.size() - 1);
            return s;
        }
    }
    public int size(){
        return ls.size();
    }
    public String toString(){
        return "Stack@"+ls.toString();
    }
}
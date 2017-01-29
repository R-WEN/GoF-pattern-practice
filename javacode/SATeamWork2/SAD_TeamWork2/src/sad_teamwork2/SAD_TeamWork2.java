/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sad_teamwork2;

import java.util.ArrayList;
import java.util.Date;


public class SAD_TeamWork2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

abstract class Customer{
    private String ID;
    private String Password;
    private String Name;
    private Date Birthday;
    private String Sex;
    private String Phone;
    private String Address;
    private float discount;
    protected int Bonus;
    public Customer(String ID,String Password,String Name,Date Birthday,String Sex,String Phone,String Address,float discount){
        this.ID=ID;
        this.Password=Password;
        this.Name=Name;
        this.Birthday=Birthday;
        this.Sex=Sex;
        this.Phone=Phone;
        this.Address=Address;
        this.discount=discount;
    }
    //get Methods....
    abstract public void IncreaseBonus();
}
class GoldMember extends Customer{
    public GoldMember(String ID,String Password,String Name,Date Birthday,String Sex,String Phone,String Address,float discount,int Bonus){
        super(ID,Password,Name,Birthday,Sex,Phone,Address,discount);
        this.Bonus=Bonus;
    }
    @Override
    public void  IncreaseBonus() {
        Bonus+=1000;
    }
}
class NormalMember extends Customer{
    public NormalMember(String ID,String Password,String Name,Date Birthday,String Sex,String Phone,String Address,float discount,int Bonus){
        super(ID,Password,Name,Birthday,Sex,Phone,Address,discount);
        this.Bonus=Bonus;
    }
    @Override
    public void  IncreaseBonus() {
        Bonus+=100;
    }
}
class CustomerDB{
    //attribute and methods.
    public Customer getCustomer(String ID){
        //Customer attributes
        String CID="";
        String Password="";
        String Name="";
        Date Birthday=null;
        String Sex="";
        String Phone="";
        String Address="";
        float discount=0;
        int Bonus=0;
        //connect DataBase.
        //search DataBase.
        //put data into Customer attributes
        String Customertype="";
        //Customertype is select from DB.
        Customer customer=null;
        if (Customertype.equals("Gold")){
            customer=new GoldMember(CID,Password,Name,Birthday,Sex,Phone,Address,discount,Bonus);
        }else if(Customertype.equals("Normal")){
            customer=new NormalMember(CID,Password,Name,Birthday,Sex,Phone,Address,discount,Bonus);
        }
        return customer;
    }
}

class Product{
    public int getPrice(){
        //return price
        return 0;
    }
}
class ShoppingCart {
    private ArrayList<Product> productlist=new ArrayList<>();
    
    public void addProduct(Product p){
        productlist.add(p);
    }
    public void removeProduct(Product p){
        productlist.remove(p);
    }
    public int calculateTotalPrice(){
        int total=0;
        for (Product p : productlist){
            total+=p.getPrice();
        }
        return total;
    }
    public ArrayList<Product> CheckOut(){
        return productlist;
    }
    public void clearCart(){
        productlist.removeAll(productlist);
    }
}
class ShoppingCartGUI{
    
}
class Payment{
    private Customer customer;
    private Order o;
    public void setOrder(Order o){
        this.o=o;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
    public void Pay(String CreditCardID){
        //Pay for this order.
        
        customer.IncreaseBonus();
        
    }
    public boolean checkCreditCard (String CreditCardID){
        //check Credit
        
        
        
        
        return false;
    }
}
class Order{
    private ArrayList<Product> product;
    private String ID;
    private int TotalPrice;

    public void setProduct(ShoppingCart Cart){
        //LoD (2) it's okay to call methods on objects passed in to our method
        product=Cart.CheckOut();
        
        //LoD (1) it's okay to call our own methods
        iniOrder();
    }
    private void iniOrder(){
        //calculate price and create ID
    }

}
class ShoppingCartController{
    private ShoppingCartGUI gui=new ShoppingCartGUI();
    private ShoppingCart shoppingcart=new ShoppingCart();
    private Payment payment=new Payment();
    public String checkout(){
        //LoD (3) it's okay to call methods on any objects we create
        Order o=new Order();
        o.setProduct(shoppingcart);

        //LoD (4) any directly held component objects
        shoppingcart.clearCart();
        
        //When customer Checkout , payament's order will change and customer can pay for different Order
        payment.setOrder(o);
        
        
        
        return "Checkout complete.";
    } 
}
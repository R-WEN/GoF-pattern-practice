import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class OnlineShoppingCart {
	public static void main(String[] args) {
		ProductItemGUI productItemGUI = new ProductItemGUI();
		productItemGUI.setVisible(true);
	}
}

class ProductItemGUI extends JFrame {
	private JLabel pmenu,cartItem,qStock,num,price;
	private DefaultListModel<String> myListModel;
	private JList<String> list;
	private JScrollPane scrollPane,scrollPane2;
	private JComboBox<String> callNum;
	private JButton add,checkout,remove,clear;
	private JTable table;
	private DefaultTableModel defaultModel;
	ProductDB productDB = new ProductDB();
	CartItem cart = new CartItem();

	public ProductItemGUI() {
		this.setTitle("Online Shopping Cart");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,600,400);
		this.setPreferredSize(new Dimension(300, 600));
        this.setLayout(null);
        this.setResizable(false);
		this.setLocationRelativeTo(null);

		pmenu = new JLabel("Product Menu : ");
		myListModel = new DefaultListModel<>();
		myListModel.addElement("Tomato");
		myListModel.addElement("Watermelon");
		myListModel.addElement("Pear");
		myListModel.addElement("Guava");
		myListModel.addElement("Orange");
		myListModel.addElement("Mango");
		myListModel.addElement("Papaya");
		myListModel.addElement("Banana");
		myListModel.addElement("Lemon");
		myListModel.addElement("Olives");
		list = new JList<>(myListModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane = new JScrollPane(list);

		qStock = new JLabel("");
		price = new JLabel("");
		num = new JLabel("choose quantity : ");
	
		callNum = new JComboBox<>();
		callNum.addItem("1");
		callNum.addItem("2");
		callNum.addItem("3");
		callNum.addItem("4");
		callNum.addItem("5");
		add = new JButton("Add Product");

		cartItem = new JLabel("Cart Item :");
        //myListModel2 = new DefaultListModel();
        //list2 = new JList(myListModel2);
		
		String[][] productInfo = new String[0][2];
		String[] names = {"Product","Quantity"};
		defaultModel = new DefaultTableModel(productInfo, names){
			public boolean isCellEditable(int row,int col){
				return false;
			}
		};
		table = new JTable(defaultModel);
		table.setPreferredScrollableViewportSize(new Dimension(170, 80));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane2 = new JScrollPane(table);

		remove = new JButton("Remove Item");
		clear = new JButton("Clear Cart");
		checkout = new JButton("Check out");

		pmenu.setBounds(10,10,100,20);
		scrollPane.setBounds(25,30,250,150);
		qStock.setBounds(120,180,100,20);
		price.setBounds(220,180,100,20);
		num.setBounds(50,310,100,20);
		callNum.setBounds(180,310,50,20);
		add.setBounds(90,350,120,20);

		cartItem.setBounds(310,10,100,20);
        scrollPane2.setBounds(325,30,250,150);
        remove.setBounds(330,180,120,20);
        clear.setBounds(450,180,120,20);
		checkout.setBounds(450,350,120,20);

		this.add(pmenu);
		this.add(scrollPane);
		this.add(qStock);
		this.add(price);
		this.add(num);
		this.add(callNum);
		this.add(add);
		this.add(checkout);
		this.add(cartItem);
        this.add(scrollPane2);
        this.add(remove);
		this.add(clear);

		checkout.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		CheckoutGUI checkoutGUI = new CheckoutGUI();
	    		checkoutGUI.setCartandDB(cart);
	    		close();
	    		checkoutGUI.setVisible(true);
	    	}    
    	});

		list.addListSelectionListener(new ListSelectionListener(){
		    public void valueChanged(ListSelectionEvent e) {
	    		Product p = productDB.getProduct(String.valueOf(list.getSelectedValue()));
	    		int s = p.getProductStock();
	    		if(s==0){
            		qStock.setText("Choose Product!!");
            	}else{
            		qStock.setText("Stock : " + s);
            	}
            	price.setText("Price:" + p.getPrice());
	    	 	
		    }
		});

    	add.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {    		
	    		Product p = productDB.getProduct(String.valueOf(list.getSelectedValue()));
	    		//System.out.println(p.getProductStock());
	    		int s = p.getProductStock();
	    		productDB.updateStock(p,Integer.parseInt((String)callNum.getSelectedItem()));
                
                
	    		int qty=Integer.parseInt(callNum.getSelectedItem().toString());
	    		Product item=new Product(p.getProductName(),p.getProductStock(),p.getPrice());
                item.setSell(qty);
	    		cart.addProduct(item);
	    		//System.out.println(s);
            	//System.out.println(callNum.getSelectedItem());
            	//System.out.println(list.getSelectedValue());
            	int ss = p.getProductStock();
	    		if(s==0){
            		qStock.setText("Choose Product!!");
            	}else{
            		defaultModel.addRow(new String[]{p.getProductName(),String.valueOf(p.getThisSell())});
            		qStock.setText("Stock : " + ss);
            		//System.out.println(s);
            		//System.out.println(ss);
            	}
            }
    	});
    	remove.addActionListener(new ActionListener(){
       		public void actionPerformed(ActionEvent e){
       			//((DefaultListModel) list2.getModel()).remove(list2.getSelectedIndex());
       			//Product p = productDB.getProduct();
       			int r = (Integer)table.getSelectedRow();
       			int c = (Integer)table.getSelectedColumn();
       			if( c == 1 || c == 0){
       				Product p = productDB.getProduct(String.valueOf(table.getValueAt(r,0)));
       				productDB.updateStock(p,-(Integer.parseInt(String.valueOf(table.getValueAt(r,1)))));
       				//System.out.println(String.valueOf(table.getValueAt(r,0)));
       				//System.out.println(String.valueOf(table.getValueAt(r,1)));
       				int s = p.getProductStock();
       				qStock.setText("Stock : " + s);
       				cart.removeProduct(p);
       			}

       			defaultModel.removeRow((Integer)table.getSelectedRow());
       		}
       	});
       	clear.addActionListener(new ActionListener(){
       		public void actionPerformed(ActionEvent e){
       			cart = new CartItem();
       			productDB = new ProductDB();

       			DefaultTableModel model = (DefaultTableModel) table.getModel();
    			model.setRowCount(0);
       		}
       	});
	}
	public void close() {
	        this.dispose();
	}

}


class CheckoutGUI extends JFrame{
	private JButton show,shop,exit;
	private JPanel panel;
	private JLabel tJLabel,wJLabel;
	private JTextArea jTA;
	CartItem cart;

    public CheckoutGUI() {  
    	this.setTitle("Check Out");	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100,100,300,400);
		this.setPreferredSize(new Dimension(300, 400));
        this.setLayout(null);
        this.setResizable(false);
		this.setLocationRelativeTo(null);
       	this.setVisible(true);

       	panel = new JPanel();
       	panel.setBorder(BorderFactory.createTitledBorder("Order Detail"));
       	//tJLabel = new JLabel("");
       	jTA = new JTextArea();
       	jTA.setEditable(false);
		wJLabel = new JLabel("");
		show = new JButton("Show");	
       	shop = new JButton("Shopping Again");
       	exit = new JButton("Exit");

       	//tJLabel.setBounds(10,10,100,20);
       	jTA.setBounds(10,10,100,20);
		wJLabel.setBounds(10,30,100,20);

       	panel.setBounds(10,10,280,300);
       	show.setBounds(200,310,70,20);
       	shop.setBounds(30,350,170,20);
       	exit.setBounds(200,350,70,20);

       	panel.add(jTA);
       	panel.add(wJLabel);
       	this.add(panel);
       	this.add(show);
       	this.add(shop);
       	this.add(exit);


       	show.addActionListener(new ActionListener(){
       		public void actionPerformed(ActionEvent e){
				jTA.setText(cart.getAllDetail());
       			wJLabel.setText("Total Price :" + cart.calculateTotalPrice());

       			
       		}
       	});
       	shop.addActionListener(new ActionListener(){
       		public void actionPerformed(ActionEvent e){
       			ProductItemGUI productItemGUI = new ProductItemGUI();
       			close();
	    		productItemGUI.setVisible(true);
       		}
       	});
       	exit.addActionListener(new ActionListener(){
       		public void actionPerformed(ActionEvent e){
       			close();
       		}
       	});

    }

     public void close() {
	        this.dispose();
	}
	public void setCartandDB(CartItem c){
		cart = c;
	}
}

class ProductItemController {
	private ProductItemGUI productItem;
	private ProductDB productDB;

	public ProductItemController(ProductItemGUI productItem,ProductDB productDB){
		this.productItem = productItem;
		this.productDB = productDB;
	}


}



class ProductDB {
	Product tomato = new Product("Tomato",100,12);
 	Product watermelon = new Product("Watermelon",100,9);
	Product pear = new Product("Pear",100,23);
	Product guava = new Product("Guava",100,120);
	Product orange = new Product("Orange",100,13);
	Product mango = new Product("Mango",100,50);
	Product papaya = new Product("Papaya",100,40);
	Product banana = new Product("Banana",100,18);
	Product lemon = new Product("Lemon",100,33);
	Product olives = new Product("Olives",100,100);
	Product a = new Product("",0,0);
	ArrayList<Product> productList = new ArrayList<>();
	int q;

	public ProductDB(){
		productList.add(tomato);
		productList.add(watermelon);
		productList.add(pear);
		productList.add(guava);
		productList.add(orange);
		productList.add(mango);
		productList.add(papaya);
		productList.add(banana);
		productList.add(lemon);
		productList.add(olives);
	}

	public Product getProduct(String name) {
		for(Product p : productList){
			if(name == p.getProductName()){
				return p;
			}
		}
		return a;
	}
	public void updateStock(Product p,int quantity){
		p.updateStock(quantity);
		for(Product pp : productList){
			if(p.getProductName() == pp.getProductName()){
				pp=p;
			}
		}
	}
}

class Product {
	private String name;
	private int stock;
	private int price;
	private int sell = 0;
	private int thissell;

	public Product(String name,int stock,int price) {
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	public String getProductName(){
		return name;
	}
	public int getProductStock(){
			return stock;
	}
	public int getPrice(){
		return price;
	}
    public void setSell(int q){
        sell=q;
    }
	public int getSell(){
		return sell;
	}
	public int getThisSell(){
		return thissell;
	}
	public void updateStock(int quantity){
		if(stock < quantity){
			quantity=stock;
		}
		stock -= quantity;
		thissell = quantity;
		//System.out.println(thissell);
		sell+=quantity;
	}
	public int calculatePrice(){
		return sell*price;
	}
}

class CartItem {
	ArrayList<Product> cItem = new ArrayList<>();

	public void setCartNull(){
		cItem = null;
	}
	public void addProduct(Product p){
		System.out.println(p.getProductName());
		cItem.add(p);

		System.out.println(calculateTotalPrice());
	}
	public void removeProduct(Product p){
		if(p.getSell()==0){
			// for(int i =0;i<=cItem.size();i++){
			// 	if(cItem[i].getProductName() == p.getProductName()){
			// 		cItem[i].remove();
			// 	}
			// }
			System.out.println(p);
			cItem.remove(p);
			System.out.println(p);
		}
	}
	public int calculateTotalPrice(){
		int all=0;
		for(Product p : cItem){

			all += p.calculatePrice();
		}
		return all;
	}

	public String getAllDetail(){

		String str = "";
		for(Product p : cItem){
			System.out.println(p);
			String a = p.getProductName();
			String b = String.valueOf(p.getSell());
			String c = String.valueOf(p.getPrice());
			System.out.println(a+" "+b+" "+c);
			str += a + " " + b + " " + c;
			//return a+" "+b+" "+c;
			str += "\n";
		}
		return str;
	}

}






import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class order{
	public static void main(String[] args){
            AbstractExpression node3=new Subtract(new Mod(new Number(73),new Number(10)),new Divide(new Number(10),new Number(5)));
            AbstractExpression node2=new Negate(new Variable("X",50));
            AbstractExpression node1=new Add(node3,new Number(6));
            AbstractExpression root=new Multiply(node2,node1);
            
            InstrumentedExpressionTree iet=new InstrumentedExpressionTree(root);
            iet.accept(new PrintVisitor());
            System.out.println("\n\nAnswer: "+root.interpreter());
            
	}
}
class LevelOrderIterator implements Iterator{

    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class OrderIterator implements Iterator{
    private Stack<AbstractExpression> stack;
    public OrderIterator(Stack<AbstractExpression> stack){
        this.stack=stack;
    }
    public boolean hasNext() {
        if (!stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public AbstractExpression next() {
        if (!stack.isEmpty()){
            return stack.pop();
        }else{
            return null;
        }
    }
}






abstract class ExpressionTree{
    protected AbstractExpression imp=null;
    public ExpressionTree(AbstractExpression imp){
        this.imp=imp;
    }
    public abstract void accept(Visitor v);
}
class InstrumentedExpressionTree extends ExpressionTree {
    public InstrumentedExpressionTree(AbstractExpression imp){
        super(imp);
    }
    public void accept(Visitor v){
        //inorder
        System.out.println("InOrder:");
        Iterator<AbstractExpression> itr=((NonTerminalExpression)imp).inorderiterator();
        while (itr.hasNext()){
            AbstractExpression e=itr.next();
            e.accept(v);
        }
        //preorder
        System.out.println("\nPreOrder:");
        itr=((NonTerminalExpression)imp).preorderiterator();
        while (itr.hasNext()){
            AbstractExpression e=itr.next();
            e.accept(v);
        }
        //postorder
        System.out.println("\nPostOrder:");
        itr=((NonTerminalExpression)imp).postorderiterator();
        while (itr.hasNext()){
            AbstractExpression e=itr.next();
            e.accept(v);
        }
    }


}
class SynchronizedExpressionTree extends ExpressionTree{
    public SynchronizedExpressionTree(AbstractExpression imp){
        super(imp);
    }
    public void accept(Visitor v){
        
    }
}

interface Visitor{
    public void visit(TerminalExpression LeafNode);
    public void visit(Negate CompositeNegateNode);
    public void visit(Add CompositeAddNode);
    public void visit(Subtract CompositeSubstractNode);
    public void visit(Multiply CompositeMultiplyNode);
    public void visit(Divide CompositeDivideNode);
    public void visit(Mod CompositeModNode);
    
}
class PrintVisitor implements Visitor{
    public void visit(TerminalExpression LeafNode){
        System.out.print(LeafNode.getName());
    };
    public void visit(Negate CompositeNegateNode){
        System.out.print(CompositeNegateNode.getName());
    };
    public void visit(Add CompositeAddNode){
        System.out.print(CompositeAddNode.getName());
    };
    public void visit(Subtract CompositeSubstractNode){
        System.out.print(CompositeSubstractNode.getName());
    };
    public void visit(Multiply CompositeMultiplyNode){
        System.out.print(CompositeMultiplyNode.getName());
    };
    public void visit(Divide CompositeDivideNode){
        System.out.print(CompositeDivideNode.getName());
    };
    public void visit(Mod CompositeModNode){
        System.out.print(CompositeModNode.getName());
    };
}
//more visitor


abstract class AbstractExpression{
	public abstract int interpreter();
	public abstract void accept(Visitor v);
        protected abstract void inorder(Stack<AbstractExpression> stack);
        protected abstract void preorder(Stack<AbstractExpression> stack);
        protected abstract void postorder(Stack<AbstractExpression> stack);
        protected abstract void levelorder(Queue<AbstractExpression> queue);
        public abstract String getName();
        
}

abstract class NonTerminalExpression extends AbstractExpression{
	public abstract OrderIterator inorderiterator();
        public abstract OrderIterator preorderiterator();
        public abstract OrderIterator postorderiterator();
	
}
abstract class BinaryExpression extends NonTerminalExpression{
	protected AbstractExpression left=null;
	protected AbstractExpression right=null;
	public BinaryExpression(AbstractExpression left,AbstractExpression right){
		this.left=left;
		this.right=right;
	}
        public OrderIterator inorderiterator(){
            Stack<AbstractExpression> s=new Stack<>();
            inorder(s);
            return new OrderIterator(s);
        };
        public OrderIterator preorderiterator(){
            Stack<AbstractExpression> s=new Stack<>();
            preorder(s);
            return new OrderIterator(s);
        };
        public OrderIterator postorderiterator(){
            Stack<AbstractExpression> s=new Stack<>();
            postorder(s);
            return new OrderIterator(s);
        };
        protected void preorder(Stack<AbstractExpression> stack){
            right.preorder(stack);
            left.preorder(stack);
            stack.push(this);
        };
        protected void postorder(Stack<AbstractExpression> stack){
            stack.push(this);
            left.postorder(stack);
            right.postorder(stack);

        };
        protected void inorder(Stack<AbstractExpression> stack){
            right.inorder(stack);
            stack.push(this);
            left.inorder(stack);
        };
        protected void levelorder(Queue<AbstractExpression> queue){
            
            queue.offer(this);
            
        };
        
        

}
class Mod extends BinaryExpression{
	public Mod(AbstractExpression left,AbstractExpression right){
		super(left,right);
	}
	public int interpreter(){
		return left.interpreter()%right.interpreter();
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        public String getName(){
            return "%";
        }
}
class Add extends BinaryExpression{
	public Add(AbstractExpression left,AbstractExpression right){
		super(left,right);
	}
	public int interpreter(){
		return left.interpreter()+right.interpreter();
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        public String getName(){
            return "+";
        }
}
class Multiply extends BinaryExpression{
	public Multiply(AbstractExpression left,AbstractExpression right){
		super(left,right);
	}
	public int interpreter(){
		return left.interpreter()*right.interpreter();
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        public String getName(){
            return "*";
        }
}
class Subtract extends BinaryExpression{
	public Subtract(AbstractExpression left,AbstractExpression right){
		super(left,right);
	}
	public int interpreter(){
		return left.interpreter()-right.interpreter();
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        public String getName(){
            return "-";
        }
}
class Divide extends BinaryExpression{
	public Divide(AbstractExpression left,AbstractExpression right){
		super(left,right);
	}
	public int interpreter(){
		return left.interpreter()/right.interpreter();
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        public String getName(){
            return "/";
        }
}
abstract class UnaryExpression extends NonTerminalExpression{
	protected AbstractExpression leaf=null;
	public UnaryExpression(AbstractExpression leaf){
		this.leaf=leaf;
	}
}
class Negate extends UnaryExpression{
	public Negate(AbstractExpression leaf){
		super(leaf);
	}
	public int interpreter(){
		return leaf.interpreter()*(-1);
	}
        public void accept(Visitor v){
		v.visit(this);
	}
        protected void inorder(Stack<AbstractExpression> stack){
            stack.push(this);
            leaf.inorder(stack);
            
        }
        protected void preorder(Stack<AbstractExpression> stack){
            leaf.inorder(stack);
            stack.push(this);
        };
        protected void postorder(Stack<AbstractExpression> stack){
            stack.push(this);
            leaf.inorder(stack);
        };
        protected void levelorder(Queue<AbstractExpression> queue){
            queue.offer(this);
            queue.offer(leaf);
        };
        public String getName(){
            return "(-)";
        }

        @Override
        public OrderIterator inorderiterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public OrderIterator preorderiterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public OrderIterator postorderiterator() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


}
//
abstract class TerminalExpression extends AbstractExpression{
	public void accept(Visitor v){
		v.visit(this);
	}
}
class Number extends TerminalExpression{
	private int num=0;
	public Number(int num){
		this.num=num;
	}
	public int interpreter(){
		return num;
	}
        protected void inorder(Stack<AbstractExpression> stack){
            
            stack.push(this);
        }
        protected void preorder(Stack<AbstractExpression> stack){
            stack.push(this);
            
        };
        protected void postorder(Stack<AbstractExpression> stack){
            stack.push(this);
        };        
        protected void levelorder(Queue<AbstractExpression> queue){
            queue.offer(this);
        };
        public String getName(){
            return String.valueOf(num);
        }
}
class Variable extends TerminalExpression{
	private String v=null;
        private int n=0;
	public Variable(String v,int n){
		this.v=v;
                this.n=n;
	}
        public void setValue(int n){
            this.n=n;
        }
	public int interpreter(){
		return n;
	}
        protected void inorder(Stack<AbstractExpression> stack){
            stack.push(this);
        }
        protected void preorder(Stack<AbstractExpression> stack){
            stack.push(this);
            
        };
        protected void postorder(Stack<AbstractExpression> stack){
            stack.push(this);
            
        };         
        protected void levelorder(Queue<AbstractExpression> queue){
            queue.offer(this);
        };
        public String getName(){
            return v;
        }
}


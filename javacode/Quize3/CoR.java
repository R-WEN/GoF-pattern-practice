public class CoR {
    public static void main(String[] args){
        Helper H=new H_1000(new H_500(new H_100(new H_10(new H_5(new H_1(null))))));
        H.help(156437);
    }
}
abstract class Helper{
    Helper next;
    Helper(Helper next){
        this.next=next;
    }
    abstract void help(int m);
    public void doNext(int m){
        if (next!=null){
            next.help(m);
        }
    }
}
class H_1000 extends Helper{
    H_1000(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=1000 && m!=0){
        System.out.println("1000 = "+(m / 1000));
        }
        doNext(m % 1000);
    }
}
class H_500 extends Helper{
    H_500(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=500 && m!=0){
        System.out.println("500 = "+(m / 500));
        }
        doNext(m % 500);
    }
}
class H_100 extends Helper{
    H_100(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=100 && m!=0){
        System.out.println("100 = "+(m / 100));
        }
        doNext(m % 100);
    }
}
class H_10 extends Helper{
    H_10(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=10 && m!=0){
        System.out.println("10 = "+(m / 10));
        }
        doNext(m % 10);
    }
}
class H_5 extends Helper{
    H_5(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=5 && m!=0){
        System.out.println("5 = "+(m / 5));
        }
        doNext(m % 5);
    }
}
class H_1 extends Helper{
    H_1(Helper next){
        super(next);
    }
    public void help(int m){
        if (m>=1 && m!=0){
        System.out.println("1 = "+(m / 1));
        }
        doNext(m);
    }
}
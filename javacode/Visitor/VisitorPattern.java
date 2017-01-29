interface Visitable {
    void accept(Visitor visitor);
}

interface Visitor {
    void visit(Member member);
    void visit(VIP vip);
}

class Customer implements Visitable {
    void doCustomer() {
        System.out.println("客戶服務");
    }
    void pay() {
        System.out.println("結帳");
    }
    public void accept(Visitor visitor) {
        // nothing to do
    }    
}

class Member extends Customer {
    void doMember() {
        System.out.println("會員服務");
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this); // 看似多型，其實是 overload
    }    
}
    
class VIP extends Customer {
    void doVIP() {
        System.out.println("VIP 服務");
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this); // 看似多型，其實是 overload
    }    
}

class VisitorImpl implements Visitor {
    public void visit(Member member) {
        member.doMember();
    }
    public void visit(VIP vip) {
        vip.doVIP();
    }
}

class Service {
    private Visitor visitor = new VisitorImpl();
    void doService(Customer customer) {
        customer.doCustomer();
        customer.accept(visitor);
        customer.pay();
    }
}

public class VisitorPattern {
    public static void main(String[] args) {
        Service service = new Service();
        service.doService(new Member());
        service.doService(new VIP());
    }
}
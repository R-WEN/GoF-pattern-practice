public class Gear{
    public static void main(String[] args){
        btn b=new btn(new GearN());
        b.press();
        b.press();
        b.press();
        b.press();
        b.press();
        b.press();
        
    }
}
abstract class GearState{
    abstract void nextGear(btn b);
    
}
class GearN extends GearState{
    public void nextGear(btn b){
        b.setGear(new GearD());
        System.out.println("Change to GearD");
    }
}
class GearD extends GearState{
    public void nextGear(btn b){
        b.setGear(new GearR());
        System.out.println("Change to GearR");
    }
}
class GearR extends GearState{
    public void nextGear(btn b){
        b.setGear(new GearN());
        System.out.println("Change to GearN");
    }
}
class btn{
    private GearState state;
    btn(GearState s){
        state=s;
    }
    public void setGear(GearState state){
        this.state=state;
    }
    public void press(){
        state.nextGear(this);
    }
}
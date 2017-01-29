interface State {
    void change(TrafficLight light);
}

abstract class Light implements State {
    public abstract void change(TrafficLight light);
    protected void sleep(int second) {
        try {
            Thread.sleep(second);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Red extends Light {
    public void change(TrafficLight light) {
        System.out.println("紅燈");
        sleep(5000);
        light.set(new Green()); // 如果考慮彈性調整狀態，可以不用寫死狀態物件設定
    }
}

class Green extends Light {
    public void change(TrafficLight light) {
        System.out.println("綠燈");
        sleep(5000);
        light.set(new Yellow());
    }
}

class Yellow extends Light {
    public void change(TrafficLight light) {
        System.out.println("黃燈");
        sleep(1000);
        light.set(new Red());
    }
}

class TrafficLight {
    private State current = new Red();
    void set(State state) {
        this.current = state;
    }
    void change() {
        current.change(this);
    }
}

public class StatePattern {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        while(true) {
            trafficLight.change();
        }
    }
}
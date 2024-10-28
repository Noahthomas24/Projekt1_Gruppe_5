import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Bruger {
    String name;

}

class Customer extends Bruger{
    Time t;
    int saldo;
    private static int i= 0;
    public Customer(String name, Time t) {
        this.name = name;
        this.t = t;
        i++;
    }

    @Override
    public String toString() {
        return name+"-"+t;
    }
}
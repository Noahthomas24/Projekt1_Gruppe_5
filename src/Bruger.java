import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Bruger {
    String name;

}

class Customer extends Bruger{
    Time t;
    int phone;
    private static int i= 0;
    static int kundeID=0;
    public Customer(String name, Time t) {
        this.name = name;
        this.phone = phone;
        this.t = t;
        i++;
        kundeID = i;

    }

    @Override
    public String toString() {
        return name+"-"+t;
    }
}
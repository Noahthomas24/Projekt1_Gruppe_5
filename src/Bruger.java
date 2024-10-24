public abstract class Bruger {
    String name;

}

class Customer extends Bruger{
    Time t;
    private static int i= 0;
    static int kundeID=0;
    public Customer(String name, Time t) {
        this.name = name;
        this.t = t;
        i++;
        kundeID = i;

    }

    @Override
    public String toString() {
        return name+"-"+t;
    }
}
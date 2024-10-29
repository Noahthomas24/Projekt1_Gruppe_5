public class Customer{
    String name;
    Time t;

    public Customer(String name, Time t) {
        this.name = name;
        this.t = t;
    }

    @Override
    public String toString() {
        return name+"-"+t;
    }
}
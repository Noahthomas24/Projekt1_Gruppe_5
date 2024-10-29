import java.util.ArrayList;

public class Betalinger{
    Customer c;
    int payment;

    public Betalinger(Customer c, int payment){
        this.c = c;
        this.payment = payment;
    }

    public String toString(){
        return "Kunde: "+ c +"\t Beløb: "+ payment + " kr.";
    }



}

public class Betalinger{
    Customer c;
    double payment;

    public Betalinger(Customer c, double payment){
        this.c = c;
        this.payment = payment;
    }

    public String toString(){
        return "Kunde: "+ c +"\t Beløb: "+ payment + " kr.";
    }



}

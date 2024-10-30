public class Produkt {
    String produktNavn;
    double pris;



    public Produkt(String produktNavn, double pris){
        this.produktNavn = produktNavn;
        this.pris = pris;
    }

    public double getPris(){
        return pris;
    }


    @Override
    public String toString() {
        return produktNavn+": "+pris;
    }
}

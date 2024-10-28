import java.util.ArrayList;
import java.util.List;

public class Produkt {
    String produktNavn;
    double pris;
    Produkt hairspray = new Produkt("Hairspray", 150);
    Produkt shampoo = new Produkt("Shampoo", 300);
    Produkt headAndShoulders = new Produkt("Head and Shoulders",35);
    Produkt klipning = new Produkt("Klippetid",250);

    public Produkt(String produktNavn, double pris){
        this.produktNavn = produktNavn;
        this.pris = pris;
    }



    @Override
    public String toString() {
        return produktNavn+": "+pris;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Produkt {
    String produktNavn;
    double pris;



    public Produkt(String produktNavn, double pris){
        this.produktNavn = produktNavn;
        this.pris = pris;
    }



    @Override
    public String toString() {
        return produktNavn+": "+pris;
    }
}

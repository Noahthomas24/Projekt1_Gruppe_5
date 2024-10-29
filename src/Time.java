import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Time {
    LocalTime tidspunkt;
    LocalDate dato;
    public Time(LocalTime tidspunkt, LocalDate dato) {
        this.tidspunkt=tidspunkt;
        this.dato=dato;
    }

    public LocalDate getDate(){
        return dato;
    }

    @Override
    public String toString() {
        return "(" + tidspunkt +"-"+tidspunkt.plusMinutes(30)+ ')'+" "+dato;
    }
}

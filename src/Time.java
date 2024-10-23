import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Time {
    LocalTime tidspunkt = LocalTime.now();

    public Time(LocalTime tidspunkt){
        this.tidspunkt=tidspunkt;
    }

    @Override
    public String toString() {
        return "(" + tidspunkt +"-"+tidspunkt.plusMinutes(30)+ ')';
    }
}

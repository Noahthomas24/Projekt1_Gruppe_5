import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Calendar {
    public static void main(String[] args) {
        ArrayList<Time> list = new ArrayList();
        LocalTime t = LocalTime.of(10,0);
        for (int i = 1; i<=16;i++){
            list.add(new Time(t));
            t=t.plusMinutes(30);
        }
        for (Time s:list){
            System.out.println(s);
        }

    }
}

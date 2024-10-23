import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class WeeklyCalendar {

    LocalDate startDate = LocalDate.now();
    ArrayList<Time> list = new ArrayList();
    LocalTime start = LocalTime.of(10,0);
    LocalTime stop = LocalTime.of(18,0);
    // Format the date output
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
      //  System.out.println("\nWeekly Calendar:");
    // Loop to print each day of the week

    public void genTid() {

        for (int i = 0; i < 5; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String dayOfWeek = currentDate.format(formatter);


            if (!dayOfWeek.contains("lørdag") && !dayOfWeek.contains("søndag")) {
                System.out.println(currentDate.format(formatter));
                dagsTider();
            }
        }
    }
    public void dagsTider () {
        while ((start.isBefore(stop))) {
            list.add(new Time(start));
            start = start.plusMinutes(30);
        }
        for (Time s : list) {
            System.out.println(s);
        }
    }
}


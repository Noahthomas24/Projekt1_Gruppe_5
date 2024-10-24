import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyCalendar {
    int i;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Time> generedeTider = new ArrayList();
    ArrayList<Time> ledigeTider = new ArrayList();
    ArrayList<Customer> booketTider = new ArrayList();
    LocalTime start = LocalTime.of(10,0);
    LocalTime stop = LocalTime.of(18,0);
    LocalDate startDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
        //System.out.println("\nWeekly Calendar:");

    public void genTid() {
        for (i = 0; i < 7; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek!=DayOfWeek.SATURDAY && dayOfWeek!=DayOfWeek.SUNDAY) {
                generedeTider.clear(); //rydder listen så alt ikke tilføjes dobbelt

                LocalTime currentStart = start; //resetter tiden hver dag

                while (currentStart.isBefore(stop)) {
                    generedeTider.add(new Time(currentStart, currentDate)); // Add new times for 30 min intervals
                    currentStart = currentStart.plusMinutes(30);
                }

                ledigeTider.addAll(generedeTider);
            }

        }
    }

    public void bookTid(){//Modtager en scanner, hvor brugeren kan angive et tal til at vælge en ledig tid ud fra index nummeret.
        System.out.println("Indtast nummer for den tid du gerne vil vælge.");
        Time valgTid = ledigeTider.get(scanner.nextInt()-1);
        System.out.println("Du har valgt tiden: "+valgTid);
        System.out.println("Indtast dit navn.");
        scanner.nextLine();
        String navn = scanner.nextLine();
        booketTider.add(new Customer(navn,valgTid));
        ledigeTider.remove(valgTid);

    }

    public void visTider() {// Viser alle ledige tider i listen ledigeTider
        int i = 0;
        for (Time s:ledigeTider){
            i++;
            System.out.println(i+ ": "+s);
        }
    }
    public void annullerTid(){
        int i = 0;
        for (Customer s:booketTider){
            i++;
            System.out.println(i+ ": "+s);
        }
        int valgtTid = scanner.nextInt()-1;
        ledigeTider.add(ledigeTider.get(valgtTid));
        System.out.println("Du har nu annuleret din tid: "+booketTider.get(valgtTid));
        booketTider.remove(valgtTid);
    }
}


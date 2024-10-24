import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyCalendar {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Time> generedeTider = new ArrayList();
    ArrayList<Time> ledigeTider = new ArrayList();
    ArrayList<Customer> booketTider = new ArrayList();
    LocalTime start = LocalTime.of(10,0);
    LocalTime stop = LocalTime.of(18,0);
    LocalDate startDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
        //System.out.println("\nWeekly Calendar:");

    public void bookTid(String navn){//Modtager en scanner, hvor brugeren kan angive et tal til at vælge en ledig tid ud fra index nummeret.
        Time valgTid = ledigeTider.get(scanner.nextInt()-1);
        System.out.println("Du har valgt tiden: "+valgTid);
        booketTider.add(new Customer(navn,valgTid));
        ledigeTider.remove(valgTid);

    }
    public void genTid() {

        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String dayOfWeek = currentDate.format(formatter);


            if (!dayOfWeek.contains("lørdag") && !dayOfWeek.contains("søndag")) {
                System.out.println(currentDate.format(formatter));
                dagsTider();
                visTider();


            }

        }
    }


    public void dagsTider () { //generer dagens tider, og tilføjer til listen over ledige tider.
        System.out.println(startDate);
        while ((start.isBefore(stop))) { //Tjekker for åbningstider
            generedeTider.add(new Time(start,startDate)); //Tilføjer nye tider til listen af 30 min intervallet.
            start = start.plusMinutes(30);
        }
        ledigeTider.addAll(generedeTider); //Tilføjer dagens tider til ledigeTider listen.

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


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
    ArrayList<Time> booketTider = new ArrayList();
    LocalTime start = LocalTime.of(10,0);
    LocalTime stop = LocalTime.of(18,0);
    LocalDate startDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");
        //System.out.println("\nWeekly Calendar:");

    public void bookTid(){//Modtager en scanner, hvor brugeren kan angive et tal til at vælge en ledig tid ud fra index nummeret.
        Time valgTid = ledigeTider.get(scanner.nextInt()-1);
        System.out.println("Du har valgt tiden: "+valgTid);
        booketTider.add(valgTid);
        ledigeTider.remove(valgTid);

    }
/*
    public void genTid() {

        for (int i = 0; i < 1; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            String dayOfWeek = currentDate.format(formatter);


            if (!dayOfWeek.contains("lørdag") && !dayOfWeek.contains("søndag")) {
                System.out.println(currentDate.format(formatter));
                dagsTider();
            }
        }
    }*/
    public void dagsTider (int year,int month,int day) { //generer dagens tider, og tilføjer til listen over ledige tider.
        startDate = LocalDate.of(year,month,day);
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
            System.out.println(i+ ": "+s+ startDate);
        }
    }
    public void annullerTid(){
        int i = 0;
        for (Time s:booketTider){
            i++;
            System.out.println(i+ ": "+s+" "+startDate);
        }
        int valgtTid = scanner.nextInt()-1;
        ledigeTider.add(ledigeTider.get(valgtTid));
        System.out.println("Du har nu annuleret din tid: "+booketTider.get(valgtTid));
        booketTider.remove(valgtTid);
    }
}


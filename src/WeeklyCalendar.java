import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
public class WeeklyCalendar{
    LocalDate dateDatoValg;
    Scanner scanner = new Scanner(System.in);
    ArrayList<Produkt> produkter = new ArrayList<>();
    Produkt klippetid = new Produkt("Klippetid", 250);

    String password = "HairyHarry";

    LocalTime morgenTid = LocalTime.of(10, 30, 00);
    LocalDate enUgeSiden = LocalDate.of(2024, 10, 22);
    Time t1 = new Time(morgenTid, enUgeSiden);
    Customer k1 = new Customer("Simon", t1);
    Time t2 = new Time(LocalTime.of(11, 00, 00), LocalDate.of(2024, 10, 21));
    Customer k2 = new Customer("Per", t2);

    ArrayList<Produkt> kurv = new ArrayList<>();
    ArrayList<Time> generedeTider = new ArrayList();
    ArrayList<Time> ledigeTider = new ArrayList();
    ArrayList<Customer> booketTider = new ArrayList();
    ArrayList<Betalinger> betalteTider = new ArrayList<>();

    LocalTime start = LocalTime.of(10, 0);
    LocalTime stop = LocalTime.of(18, 0);
    LocalDate startDate = LocalDate.now();
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM, yyyy");


    public void genTid() {
        betalteTider.add(new Betalinger(k1, 500)); //Tilføjer en fiktiv betaling til betalingsoversigten (bruges som test).
        betalteTider.add(new Betalinger(k2, 600));

        for (int i = 0; i < 5; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
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

    public void visTider() {// Viser alle ledige tider i listen ledigeTider
        List<LocalDate> dates = getNextFiveDays();
        System.out.println("Vælg et nummer for den ønskede dato ");

        for (int i = 0; i < dates.size(); i++) {
            System.out.println(i + 1 + ": " + dates.get(i));
        }
        int valgtDato = scanner.nextInt();
        dateDatoValg = dates.get(valgtDato - 1);
        printTiderForDato(dateDatoValg);
    }


    private void printTiderForDato(LocalDate date) {
        for (int i = 0; i < getTiderForDato(date).size(); i++) {
            if (getTiderForDato(date).get(i).dato == date) {
                System.out.println(i + 1 + ": " + getTiderForDato(date).get(i));
            }
        }
    }

    private List<Time> getTiderForDato(LocalDate date) {
        List<Time> tider = new ArrayList<>();
        for (int i = 0; i < ledigeTider.size(); i++) {
            if (ledigeTider.get(i).dato == date) {
                tider.add(ledigeTider.get(i));
            }
        }
        return tider;
    }

    private List<LocalDate> getNextFiveDays() {
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < ledigeTider.size(); i++) {
            Time tid = ledigeTider.get(i);
            if (!dates.contains(tid.dato)) {
                dates.add(tid.dato);
            }
        }
        return dates;
    }

    public void bookTid() {//Modtager en scanner, hvor brugeren kan angive et tal til at vælge en ledig tid ud fra index nummeret.
        System.out.println("Indtast nummer for den tid du gerne vil vælge.");
        List<Time> tider = getTiderForDato(dateDatoValg);
        int valg = scanner.nextInt() - 1;
        Time valgTid = tider.get(valg);
        System.out.println("Du har valgt tiden: " + valgTid);
        System.out.println("Indtast dit navn.");
        scanner.nextLine();
        String navn = scanner.nextLine();
        booketTider.add(new Customer(navn, valgTid));
        ledigeTider.remove(valgTid);
        System.out.println("Din booking for " + navn + " " + "klokken: " + valgTid + " er nu bekræftet.");

    }


    public void annullerTid() {
        int i = 0;
        for (Customer s : booketTider) {
            i++;
            System.out.println(i + ": " + s);
        }
        System.out.println("Vælg et nummer for den tid du gerne vil annulere: ");
        int valgtTid = scanner.nextInt() - 1;
        ledigeTider.add(ledigeTider.get(valgtTid));
        System.out.println("Du har nu annuleret din tid: " + booketTider.get(valgtTid));
        booketTider.remove(valgtTid);
    }


    public void betalTid() {
        int l = 0;
        int i = 0;
        int amount = 250;
        produkter.add(new Produkt("Hairspray", 150));
        produkter.add(new Produkt("Shampoo", 300));
        produkter.add(new Produkt("Head and Shoulders", 30));

        for (Customer s : booketTider) {
            i++;
            System.out.println(i + ": " + s);
        }
        while (true) {
            System.out.println("Kunne du tænkte dig at tilkøbe nogle produkter? ");
            System.out.println("Tast '1' for ja");
            System.out.println("Tast '2' for nej");
            int valgMulighed = scanner.nextInt();
            if (valgMulighed == 1) {
                System.out.println();
                scanner.nextLine();
                System.out.println("Vælg et nummer for det produkt du gerne vil købe.");
                for (int j = 0; j < produkter.size(); j++) {
                    System.out.println(j + 1 + ": " + produkter.get(j));
                }
                int produktValg = scanner.nextInt() - 1;
                produkter.get(produktValg);
                kurv.add(produkter.get(produktValg));
                System.out.println("Din kurv består nu af følgende produkter: ");
                for (Produkt p : kurv) {
                    System.out.println(p);
                }
                System.out.println(klippetid);
                System.out.println();
            }
            if (valgMulighed == 2) {
                for (Produkt k : kurv) {
                    amount += k.pris;
                }
                System.out.println("Dit samlede beløb er: " + amount);

                System.out.println();

                betalteTider.add(new Betalinger(booketTider.get(i - 1), amount));
                break;
            }


        }

    }

    public void visBetalingerForSpecifikDato() {
        System.out.println("Indtast kodeord");
        String kodeord = scanner.nextLine();

        while (!kodeord.equals(password)) {
            System.out.println("Ugyldigt kodeord, prøv igen");
            kodeord = scanner.nextLine();
        }

        System.out.println("Indtast det ønskede år.");
        int year = scanner.nextInt();
        System.out.println("Indtast den ønskede måned");
        int month = scanner.nextInt();
        System.out.println("Indtast ønskede dag i måneden");
        int day = scanner.nextInt();
        LocalDate targetDate = LocalDate.of(year, month, day);
        System.out.println(targetDate);

        for (Betalinger b : betalteTider) {
            LocalDate betalingsDato = b.c.t.getDate();
            if (betalingsDato.equals(targetDate)) {
                System.out.println(b);
            }
        }
    }

    public void visSenesteBetalinger() {
        System.out.println("Indtast kodeord");
        String kodeord = scanner.nextLine();

        while (!kodeord.equals(password)) {
            System.out.println("Ugyldigt kodeord, prøv igen");
            kodeord = scanner.nextLine();
        }
        for (Betalinger b:betalteTider){
            System.out.println(b);
        }
    }

    public void ferieFriDage(){
        System.out.println("Her kan du registrere en lukkedag.");
        System.out.println("Indtast det ønskede år.");
        int year = scanner.nextInt();
        System.out.println("Indtast den ønskede måned");
        int month = scanner.nextInt();
        System.out.println("Indtast ønskede dag i måneden");
        int day = scanner.nextInt();
        LocalDate targetDate = LocalDate.of(year, month, day);
        System.out.println(targetDate);

        ledigeTider.removeIf(t -> t.getDate().equals(targetDate));
    }
}



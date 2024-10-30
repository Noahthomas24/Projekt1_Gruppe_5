import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeeklyCalendar {

    // Klasse-variabler
    LocalDate dateDatoValg;
    Scanner scanner = new Scanner(System.in);
    Produkt klippetid = new Produkt("Klippetid", 250);
    String password = "HairyHarry";

    // Foruddefinerede tider og kunder som bliver tilføjet til listen over tidligere betalinger.
    Time t1 = new Time(LocalTime.of(10, 30, 00), LocalDate.of(2024, 10, 22));
    Time t2 = new Time(LocalTime.of(11, 00, 00), LocalDate.of(2024, 10, 21));
    Customer k1 = new Customer("Simon", t1);
    Customer k2 = new Customer("Per", t2);

    // Lister til forskellige kategorier af tider og kunder
    ArrayList<Produkt> kurv = new ArrayList<>();
    ArrayList<Time> genereredeTider = new ArrayList<>();
    ArrayList<Time> ledigeTider = new ArrayList<>();
    ArrayList<Customer> bookedeTider = new ArrayList<>();
    ArrayList<Betalinger> betalteTider = new ArrayList<>();
    ArrayList<Produkt> produkter = new ArrayList<>();


    // Åbningstider
    LocalTime start = LocalTime.of(10, 0);
    LocalTime stop = LocalTime.of(18, 0);
    LocalDate startDate = LocalDate.now();

    // Generer ledige tider for en uge
    public void genTid() {
        // Tilføjer test-betalinger
        betalteTider.add(new Betalinger(k1, 500));
        betalteTider.add(new Betalinger(k2, 600));

        // Genererer ledige tider i ugedage (mandag-fredag)
        for (int i = 0; i < 5; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                genereredeTider.clear(); // Rydder listen for dobbeltposter
                LocalTime currentStart = start; // Reset starttid hver dag
                while (currentStart.isBefore(stop)) {
                    genereredeTider.add(new Time(currentStart, currentDate));
                    currentStart = currentStart.plusMinutes(30);
                }
                ledigeTider.addAll(genereredeTider);
            }
        }
    }

    // Viser ledige tider og lader bruger vælge en dato
    public void visTider() {
        List<LocalDate> dates = getNextFiveDays();
        System.out.println("Vælg et nummer for den ønskede dato ");
        for (int i = 0; i < dates.size(); i++) {
            System.out.println(i + 1 + ": " + dates.get(i));
        }
        int valgtDato = scanner.nextInt();
        dateDatoValg = dates.get(valgtDato - 1);
        printTiderForDato(dateDatoValg);
    }

    // Udskriver ledige tider for en specifik dato
    private void printTiderForDato(LocalDate date) {
        List<Time> tider = getTiderForDato(date);
        for (int i = 0; i < tider.size(); i++) {
            System.out.println(i + 1 + ": " + tider.get(i));
        }
    }

    // Henter ledige tider for en specifik dato
    private List<Time> getTiderForDato(LocalDate date) {
        List<Time> tider = new ArrayList<>();
        for (Time t : ledigeTider) {
            if (t.dato.equals(date)) {
                tider.add(t);
            }
        }
        return tider;
    }

    // Henter de næste fem dage med ledige tider
    private List<LocalDate> getNextFiveDays() {
        List<LocalDate> dates = new ArrayList<>();
        for (Time tid : ledigeTider) {
            if (!dates.contains(tid.dato)) {
                dates.add(tid.dato);
            }
        }
        return dates;
    }

    // Book en tid
    public void bookTid() {
        System.out.println("Indtast nummer for den tid du gerne vil vælge.");
        List<Time> tider = getTiderForDato(dateDatoValg);
        int valg = scanner.nextInt() - 1;
        Time valgTid = tider.get(valg);
        System.out.println("Du har valgt tiden: " + valgTid);
        System.out.println("Indtast dit navn.");
        scanner.nextLine();
        String navn = scanner.nextLine();
        bookedeTider.add(new Customer(navn, valgTid));
        ledigeTider.remove(valgTid);
        System.out.println("Din booking for " + navn + " klokken: " + valgTid + " er nu bekræftet.");
    }

    // Annuller en booking
    public void annullerTid() {
        int i = 0;
        for (Customer s : bookedeTider) {
            i++;
            System.out.println(i + ": " + s);
        }
        System.out.println("Vælg et nummer for den tid du gerne vil annullere: ");
        int valgtTid = scanner.nextInt() - 1;
        ledigeTider.add(ledigeTider.get(valgtTid));
        System.out.println("Du har nu annulleret din tid: " + bookedeTider.get(valgtTid));
        bookedeTider.remove(valgtTid);
    }

    // Betal for en booking
    public void betalTid() {
        double amount = klippetid.getPris();
        produkter.add(new Produkt("Hairspray", 150));
        produkter.add(new Produkt("Shampoo", 300));
        produkter.add(new Produkt("Head and Shoulders", 30));

        while (true) {
            System.out.println("Vil du købe yderligere produkter? (1=Ja, 2=Nej)");
            int valgMulighed = scanner.nextInt();
            if (valgMulighed == 1) {
                System.out.println("Vælg et nummer for det produkt du gerne vil købe.");
                for (int i = 0; i < produkter.size(); i++) {
                    System.out.println(i + 1 + ": " + produkter.get(i));
                }
                int produktValg = scanner.nextInt() - 1;
                kurv.add(produkter.get(produktValg));
                System.out.println("Du har valgt følgende varer: ");
                for (Produkt p : kurv) {
                    System.out.println(p);
                }
                System.out.println(klippetid);
            } else {
                for (Produkt k : kurv) {
                    amount += k.pris;
                }
                System.out.println("Dit samlede beløb er: " + amount);
                betalteTider.add(new Betalinger(bookedeTider.get(bookedeTider.size() - 1), amount));
                break;
            }
        }
    }

    // Bekræfter kodeord
    public boolean verifyPassword() {
        System.out.println("Indtast kodeord");
        String kodeord = scanner.nextLine();
        while (!kodeord.equals(password)) {
            System.out.println("Ugyldigt kodeord, prøv igen");
            kodeord = scanner.nextLine();
        }
        return true;
    }

    // Viser betalinger for en specifik dato
    public void visBetalingerForSpecifikDato() {
            System.out.println("Indtast ønsket år, måned og dag for betalingen:");
            LocalDate targetDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());


            for (Betalinger b : betalteTider) {
                if (b.c.t.getDate().equals(targetDate)) { // Henter datoen fra tidspunktet, som er tilknyttet kunden, der er tilknyttet betalingen.
                    System.out.println(b);
                }
            }
    }

        // Viser seneste betalinger
        public void visSenesteBetalinger () {
            for (Betalinger b : betalteTider) {
                System.out.println(b);
            }
        }


        // Registrerer lukkedage
        public void ferieFriDage() {
            System.out.println("Indtast år, måned og dag for lukkedagen:");
            LocalDate targetDate = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            ledigeTider.removeIf(t -> t.getDate().equals(targetDate));
        }
    }
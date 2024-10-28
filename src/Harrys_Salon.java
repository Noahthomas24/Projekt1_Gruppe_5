import java.util.Scanner;

public class Harrys_Salon {
    Scanner scanner = new Scanner(System.in);
    WeeklyCalendar c = new WeeklyCalendar();

    public void Harrys_Salon(){
        c.genTid();
        int valg;
        while (true){
            System.out.println("Velkommen til Harrys Salon");
            System.out.println("Tast '1' for at booke en tid");
            System.out.println("Tast '2' for at annulere en tid");
            System.out.println("Tast '3' for at betale for din tid");
            System.out.println("Tast '4' for at logge ind som admin");
            System.out.println("Tast '5' for at se vores hårprodukter");
            System.out.println("Tast '0' for at afslutte");
            valg = scanner.nextInt();
            if (valg ==0) break;
            switch (valg){
                case 1:
                    c.visTider();
                    c.bookTid();
                    break;
                case 2:
                    c.annullerTid();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    continue;
            }
        }
    }
}

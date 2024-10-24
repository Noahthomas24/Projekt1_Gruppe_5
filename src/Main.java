import java.util.Scanner;
import java.util.SortedMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeeklyCalendar g = new WeeklyCalendar();
        g.genTid();
        g.visTider();
        g.bookTid();
        g.annullerTid();
        System.out.println();


        }
    }

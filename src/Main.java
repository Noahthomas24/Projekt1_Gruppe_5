import java.util.SortedMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        WeeklyCalendar g = new WeeklyCalendar();
        g.dagsTider(2024,10,24);
        g.visTider();
        g.bookTid();
        g.visTider();
        g.annullerTid();
        g.visTider();
        System.out.println();
        }
    }

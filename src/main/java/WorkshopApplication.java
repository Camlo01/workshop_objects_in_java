import model.BankAccount;
import model.Fruit;
import model.Invoice;
import model.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class WorkshopApplication {

    public static void main(String[] args) {

        initializedSuccessfully();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        ArrayList<Fruit> fruits = new ArrayList<>();

        Fruit fruitReusable = new Fruit("Sandía", "Citrullus lanatus", 3000, 1450, new ArrayList<>()
                , new ArrayList<>(), 2.000F, 30, true, false, Fruit.Maturity.MATURITY, Fruit.Taste.SWEET);

        fruits.add(fruitReusable);
        fruits.add(fruitReusable);
        fruits.add(fruitReusable);

        Invoice invoice = new Invoice(1, "Camilo", "Matt", fruits);

        System.out.println(invoice.showInvoice());





    }

    /**
     * Console message indicating that it started correctly
     */
    private static void initializedSuccessfully() {
        System.out.println("-------------------------");
        System.out.println("Initialized Successfully!");
        System.out.println("-------------------------");
    }



}

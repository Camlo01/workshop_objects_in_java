package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class representing an invoice with useful methods.
 *
 * @author Camilo Beltrán
 */
public class Invoice {

    private int invoiceNumber;
    private String whoInvoice;
    private String whoPays;
    private LocalDate date;
    private ArrayList<Fruit> fruits;
    private double subTotal;
    private double costIva;
    private double total;

// constructor

    /**
     * Empty constructor.
     */
    public Invoice() {
        this.date = LocalDate.now();
    }

    /**
     * Constructor with the necessary information completed.
     * values like "subtotal", "costIva" and "total" are automatically generated.
     *
     * @param invoiceNumber Unique invoice number
     * @param whoInvoice    cashier name
     * @param whoPays       customer name
     * @param fruits        ArrayList of products to buy
     */
    public Invoice(int invoiceNumber, String whoInvoice, String whoPays, ArrayList<Fruit> fruits) {
        this.invoiceNumber = invoiceNumber;
        this.whoInvoice = whoInvoice;
        this.whoPays = whoPays;
        this.date = LocalDate.now();
        this.fruits = fruits;
        this.subTotal = calculateTotalPrice(fruits);
        this.costIva = calculateIvaPrice(subTotal);
        this.total = Math.floor(subTotal + costIva);

    }

    // getters and setters

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getWhoInvoice() {
        return whoInvoice;
    }

    public void setWhoInvoice(String whoInvoice) {
        this.whoInvoice = whoInvoice;
    }

    public String getWhoPays() {
        return whoPays;
    }

    public void setWhoPays(String whoPays) {
        this.whoPays = whoPays;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getCostIva() {
        return costIva;
    }

    public double getTotal() {
        return total;
    }


//    Util methods

    /**
     * Loop array of products and adds the price of all products to be returned
     *
     * @param fruits list of fruits to buy
     * @return total price of them
     */
    protected double calculateTotalPrice(ArrayList<Fruit> fruits) {
        //Initializes the value of subTotal into an object that can be used in lambda
        AtomicReference<Double> subTotal = new AtomicReference<>(0.0);
        fruits.forEach((fruit -> {
            //Update the subTotal
            subTotal.updateAndGet(v -> (double) (v + fruit.getPrice()));
        }));
        //SubTotal is rounded to 2 decimals places
        BigDecimal subTotalRounded = new BigDecimal(subTotal.get()).setScale(2, RoundingMode.HALF_DOWN);

        //The double value inside the object is returned
        return subTotalRounded.doubleValue();
    }

    /**
     * Calculate the price value of the IVA of a price
     *
     * @param subTotal entered value
     * @return the IVA price
     */
    protected double calculateIvaPrice(double subTotal) {
        final int IVA = 21;
        double ivaPrice = IVA * subTotal / 100;
        //Is returned the rounded value
        return new BigDecimal(ivaPrice).setScale(2, RoundingMode.HALF_DOWN).doubleValue();


    }

    /**
     * Know the size of an array
     *
     * @param fruits Array to count
     * @return total size
     */
    protected int countProducts(ArrayList<Fruit> fruits) {
        return fruits.size();
    }

    /**
     * show on the receipt the date in the format as is desired
     *
     * @return 31/12/2022 (example)
     */
    private String dateFormatForInvoice() {
        DateTimeFormatter formatToUse = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatToUse);

    }

    /**
     * The ArrayList of products is listed with its weight and its price to be added inside showInvoice()
     *
     * @param fruits Array of products
     * @return the list
     */
    private String productsDetailInvoice(ArrayList<Fruit> fruits) {
        StringBuilder details = new StringBuilder("List...........................\n");
        fruits.forEach((fruit -> {
            String productInfo = fruit.getName() + " | " + fruit.getWeight() + "g | " + "$" + fruit.getPrice() + "\n";
            details.append(productInfo);
        }));


        return details.toString();
    }

    /**
     * Show the invoice with all the details
     *
     * @return the invoice
     */
    public String showInvoice() {
        return "-------------------------------\n" +
                "--- Invoice -------- n.º " + invoiceNumber + "\n" +
                "-------------------------------\n" +
                "date: " + dateFormatForInvoice() + "\n" +
                "------------------------------- \n" +
                "who attended: " + whoInvoice + "\n" +
                "person who buy: " + whoPays + "\n\n" +
                //List of Fruits
                productsDetailInvoice(fruits) +
                "\n" +
                "total product: " + countProducts(fruits) + "\n" +
                "cost...........................\n" +
                "subTotal: " + subTotal + "\n" +
                "cost of IVA: " + costIva + "\n" +
                "total: " + total + "\n" +
                "\n";
    }

}

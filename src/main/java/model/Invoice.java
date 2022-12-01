package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

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

    public Invoice() {
        this.date = LocalDate.now();
    }

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

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getCostIva() {
        return costIva;
    }

    public void setCostIva(double costIva) {
        this.costIva = costIva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
     *
     * @return
     */
    private String dateFormatForInvoice() {
        return " " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() + " ";

    }

    /**
     *
     * @return
     */
    public String showInvoice() {
        return "-------------------------------\n" +
                "--- Invoice -------- n.º " + invoiceNumber + "\n" +
                "-------------------------------\n" +
                "date: " + dateFormatForInvoice() + "\n" +
                "------------------------------- \n" +
                "who attended: " + whoInvoice + "\n" +
                "person who buy: " + whoPays + "\n" +
                "\n" +
                "List...........................\n" +
                "\n" +
                //List of Fruits
                "\n" +
                "cost...........................\n" +
                "subTotal: " + subTotal + "\n" +
                "cost of IVA: " + costIva + "\n" +
                "total: " + total + "\n" +
                "\n";
    }
}

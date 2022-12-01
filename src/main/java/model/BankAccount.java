package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class BankAccount {

    private int accountNumber;
    protected boolean activated;
    private double balance;
    private Integer cardNumber;
    private int password;
    protected boolean taxFree;
    private Person owner;
    private double monthlyFee;
    private Date lastFeeDiscount;
    private AccountType type;

    public enum AccountType {CURRENT, SAVINGS, PAYROLL, IRA}

//    Constructors

    public BankAccount() {
    }

    public BankAccount(int accountNumber, boolean activated, double balance, Integer cardNumber, int password, boolean taxFree, Person owner, double monthlyFee, Date lastFeeDiscount, AccountType type) {
        this.accountNumber = accountNumber;
        this.activated = activated;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.password = password;
        this.taxFree = taxFree;
        this.owner = owner;
        this.monthlyFee = monthlyFee;
        this.lastFeeDiscount = lastFeeDiscount;
        this.type = type;
    }

    //    Getters and Setters

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean isTaxFree() {
        return taxFree;
    }

    public void setTaxFree(boolean taxFree) {
        this.taxFree = taxFree;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Date getLastFeeDiscount() {
        return lastFeeDiscount;
    }

    public void setLastFeeDiscount(Date lastFeeDiscount) {
        this.lastFeeDiscount = lastFeeDiscount;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }


//    Useful methods

    /**
     * Sum Array of numbers and return the result
     *
     * @param arr array of numbers to sum
     * @return the sum of all
     */
    private int sumDigits(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    /**
     * count how many months handling fee is due
     *
     * @return returns the number of months owed
     */
    private int monthsPendingHandlingFree() {
        LocalDate lastDiscount = lastFeeDiscount.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate actualDate = LocalDate.now();
        int differenceYears = Period.between(lastDiscount, actualDate).getYears();
        return differenceYears * 12 + Period.between(lastDiscount, actualDate).getMonths();
    }

    /**
     * Validate if X amount of money can be deducted by fulfilling the following conditions
     * - The amount of money in the account cannot be negative
     * - There must be money left to pay the months pending rate
     *
     * @param amountToDiscount quantity to evaluate
     * @return true if the discount operation can be continued
     */
    public boolean canDiscountAmount(double amountToDiscount) {

        //There is enough money in the account
        boolean enoughMoney = balance - amountToDiscount >= 0;

        double noPendingToPayInFees = monthsPendingHandlingFree() * monthlyFee;

        //There must be enough money left to pay the remaining months of the fee
        boolean leftToPayFees = (balance - amountToDiscount) >= noPendingToPayInFees;

        return enoughMoney && leftToPayFees;
    }

    /**
     * deduct money from account
     *
     * @param toDiscount amount of money to withdraw
     * @return true if the operation was successful
     */
    protected boolean discountAmount(double toDiscount) {

        int howMuchCantDiscount = monthsPendingHandlingFree();

        if (howMuchCantDiscount == 0) {
            this.balance = balance - toDiscount;
            return true;
        }
        return false;
    }

    /**
     * Know the amount of money in the bank account
     *
     * @return String with the balance
     */
    protected String consultBalance() {
        return "your balance is: $" + balance;
    }

    /**
     * Check if the number complies with Luhn's algorithm
     *
     * @return true
     */
    protected boolean isCardNumberValidate(String anyCardNumber) {
        // int array for processing the cardNumber
        int[] cardIntArray = new int[anyCardNumber.length()];
        for (int i = 0; i < anyCardNumber.length(); i++) {
            char c = anyCardNumber.charAt(i);
            cardIntArray[i] = Integer.parseInt("" + c);
        }
        // sum the numbers
        for (int i = cardIntArray.length - 2; i >= 0; i = i - 2) {
            int num = cardIntArray[i];
            num = num * 2;
            if (num > 9) {
                num = num % 10 + num / 10;
            }
            cardIntArray[i] = num;
        }

        //check if the sum is modulo 10
        int sum = sumDigits(cardIntArray);
        return sum % 10 == 0;

    }

    /**
     * Know information about the months of fee pending payable
     *
     * @return months due and the total price
     */
    protected String monthsPayable() {
        int monthsPending = monthsPendingHandlingFree();
        double totalToPay = monthsPending * monthlyFee;
        if (monthsPending > 0) {
            if (monthsPending == 1) {
                return "You only owe one month of fee, $" + monthlyFee + " to pay";
            }

            return "You owe a total of " + monthsPending + " months of fee, a total payable of $" + totalToPay;
        }
        return "You are up to date with the payment of your monthly fee";
    }

}

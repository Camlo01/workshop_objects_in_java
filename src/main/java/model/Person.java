package model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class that represents a person and includes useful methods.
 *
 * @author Camilo Beltrán
 */
public class Person {

    public String name;
    public String lastName1;
    public String lastName2;
    public Date dateBirth;
    public float height;
    public String email;
    public int phoneNumber;
    public int postalCode;
    public String address;
    public MaritalStatus status;

    private enum MaritalStatus {SINGLE, MARRIED, DIVORCED}

//    Constructors

    /**
     * Empty constructor.
     */
    public Person() {
        this.status = MaritalStatus.SINGLE;
    }

    /**
     * Minimum information to create the person object.
     */
    public Person(String name, Date dateBirth, float height, int phoneNumber, int postalCode, String address) {
        this.name = name;
        this.dateBirth = dateBirth;
        this.height = height;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address = address;
    }

    /**
     * Constructor to write the complete information of the person.
     */
    public Person(String name, String lastName1, String lastName2, Date dateBirth, float height, String email, int phoneNumber, int postalCode, String address, MaritalStatus status) {
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.dateBirth = dateBirth;
        this.height = height;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.postalCode = postalCode;
        this.address = address;
        this.status = status;
    }


    //    Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MaritalStatus getStatus() {
        return status;
    }

    public void setStatus(MaritalStatus status) {
        this.status = status;
    }

//    Useful methods

    /**
     * full name with name and last names.
     *
     * @return String with name concatenated with lastName1 and lastName2
     */
    protected String fullName() {
        return name + " " + lastName1 + " " + lastName2;
    }

    /**
     * Check the age according to the person's date of birth and if it is older than the entered age.
     *
     * @param requiredAge minimum age to evaluate
     * @return true if is older than the requiredAge
     */
    private boolean isOldEnough(int requiredAge) {

        LocalDate toEvaluate = dateBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate actualDate = LocalDate.now();

        int difference = Period.between(toEvaluate, actualDate).getYears();

        return difference >= requiredAge;
    }

    /**
     * Verify if the person is considered as adult by his birth Date.
     *
     * @return true in that case
     */
    public boolean isAdult() {
        int ageToBeAdult = 18;
        return isOldEnough(ageToBeAdult);
    }

}

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * @author Camilo Beltrán
 */
public class Fruit {

    public String name;
    public String scientificName;
    public float weight;
    private float averageWeight;
    public ArrayList<String> colors;
    public ArrayList<String> vitamins;
    public Float pricePound;
    public int calories;
    public boolean edible;
    public boolean poisonous;
    public Maturity maturity;
    public Taste taste;

    public enum Maturity {DEVELOPMENT, MATURITY, OVERRIPE, ROTTEN}

    public enum Taste {SWEET, SEMISWEET, ACID, SEMIACID, NEUTRAL}

//    Constructors

    public Fruit() {
        this.colors = new ArrayList<String>();
        this.vitamins = new ArrayList<String>();
        this.maturity = Maturity.DEVELOPMENT;
        this.taste = Taste.NEUTRAL;
    }

    public Fruit(String name, String scientificName, float weight, float averageWeight, ArrayList<String> colors, ArrayList<String> vitamins, Float pricePound, int calories, boolean edible, boolean poisonous, Maturity maturity, Taste taste) {
        this.name = name;
        this.scientificName = scientificName;
        this.weight = weight;
        this.averageWeight = averageWeight;
        this.colors = colors;
        this.vitamins = vitamins;
        this.pricePound = pricePound;
        this.calories = calories;
        this.edible = edible;
        this.poisonous = poisonous;
        this.maturity = maturity;
        this.taste = taste;
    }

    //    Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(float averageWeight) {
        this.averageWeight = averageWeight;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public ArrayList<String> getVitamins() {
        return vitamins;
    }

    public void setVitamins(ArrayList<String> vitamins) {
        this.vitamins = vitamins;
    }

    public Float getPricePound() {
        return pricePound;
    }

    public void setPricePound(Float pricePound) {
        this.pricePound = pricePound;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous(boolean poisonous) {
        this.poisonous = poisonous;
    }

    public Maturity getMaturity() {
        return maturity;
    }

    public void setMaturity(Maturity maturity) {
        this.maturity = maturity;
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }


//    Useful methods


    /**
     * Evaluate the level of maturity and return it
     *
     * @return String of the level of maturity
     */
    public String maturityLevel() {
        switch (maturity) {
            case DEVELOPMENT:
                return "Development";
            case MATURITY:
                return "Maturity";
            case OVERRIPE:
                return "Overripe";
            case ROTTEN:
                return "Rotten";
        }
        return null;
    }

    /**
     * Evaluate the taste and return it
     *
     * @return String of the taste
     */
    public String tasteIs() {
        switch (taste) {
            case ACID:
                return "Acid";
            case SEMIACID:
                return "Semi-Acid";
            case NEUTRAL:
                return "Neutral";
            case SEMISWEET:
                return "Semi-Sweet";
            case SWEET:
                return "Sweet";
        }
        return null;
    }

    /**
     * Evaluate a condition that in case the entered boolean value is true return "yes", else returns "no"
     *
     * @param value to evaluate
     * @return String "yes" or "no"
     */
    private String yerOrNo(boolean value) {
        return value ? "yes" : "no";
    }

    /**
     * Loop through the arrayList of colors and concatenate the colors with line break
     *
     * @return String with list of colors
     */
    protected String listOfColors() {
        StringBuilder listOfColors = new StringBuilder(" - Colors - \n");
        for (String color : colors) {
            listOfColors.append("color:").append(color).append("\n");
        }
        listOfColors.append("------\n");
        return listOfColors.toString();
    }

    /**
     * Loop through the arrayList of vitamins and concatenate it into a String
     *
     * @return String with vitamins compiled
     */
    protected String listOfVitamins() {
        StringBuilder listOfVitamins = new StringBuilder("Vitamins: ");
        for (String vitamin : vitamins) {
            listOfVitamins.append(" ").append(vitamin).append(" ");
        }
        listOfVitamins.append("\n");
        return listOfVitamins.toString();
    }

    /**
     * Evaluate different properties of the fruit if it can be eaten
     *
     * @return an explanation of whether the fruit can be eaten
     */
    public String canBeEaten() {

        if (poisonous) {
            return "This fruit cannot be eaten because it is poisonous and could kill you.!";
        }

        if (!edible) {
            return "This fruit cannot be eaten without being properly prepared.";
        }

        switch (maturity) {
            case DEVELOPMENT:
                return "This fruit is still developing, you still can't consume.";
            case MATURITY:
                return "This fruit can be eaten";
            case OVERRIPE:
                return "It is better not to consume this fruit because it is already too ripe.";
            case ROTTEN:
                return "Do not eat this fruit, it is rotten!";
        }

//        This value should only be returned in case an object is missing an important property
        return "some required value is null";
    }

    /**
     * Text with detailed description information with each of the details of the fruit
     *
     * @return String with all the details
     */
    public String detailedDescription() {

        return " --- Detailed description --- \n" +
                "Fruit name: " + name + "\n" +
                "Scientific name: " + scientificName + "\n" +
                "Weight: " + weight + "\n" +
                "Average Weight: " + averageWeight + " grams\n" +
                listOfColors() +
                listOfVitamins() + "\n" +
                "Price per pound: $" + pricePound + "\n" +
                "Calories: " + calories + "\n" +
                "Is edible? " + yerOrNo(edible) + "\n" +
                "Contains poisonous? " + yerOrNo(poisonous) + "\n" +
                "Level of maturity: " + maturityLevel() + "\n" +
                "Taste: " + tasteIs() + "\n" +
                "------------------------------";
    }

    /**
     * Text with basic information about the fruit
     * - name, average weight, calories and price per pound
     *
     * @return String
     */
    public String description() {
        return "- Detailed description - \n" +
                "Name: " + name + "\n" +
                "Weight: " + weight + " grams \n" +
                "Calories: " + calories + "\n" +
                "Price per pound: $" + pricePound + "\n";
    }

    /**
     * Know the price with the cost of the pound by the weight in grams of the fruit
     *
     * @return the price
     */
    public double getPrice() {
        //large number
        double poundsToRound = weight / 453.59;

        //It is rounded
        BigDecimal pounds = new BigDecimal(poundsToRound).setScale(2, RoundingMode.HALF_DOWN);

        //Multiply de price per pound to total pounds and is returned
        return pricePound * pounds.doubleValue();
    }


}


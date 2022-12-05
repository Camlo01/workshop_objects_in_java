package model;

import java.util.ArrayList;

/**
 * Class that represents a movie entity along with useful methods.
 *
 * @author Camilo Beltrán
 */
public class Movie {

    private String name;
    private String prologue;
    private ArrayList<String> directors;
    private ArrayList<String> actors;
    private ArrayList<String> writers;
    private ArrayList<String> genres;
    private ArrayList<String> producers;
    private float rate;

//    Constructors

    /**
     * Empty constructors
     */
    public Movie() {
        this.directors = new ArrayList<>();
        this.actors = new ArrayList<>();
        this.writers = new ArrayList<>();
        this.genres = new ArrayList<>();
        this.producers = new ArrayList<>();
    }

    /**
     * full constructor
     */
    public Movie(String name, String prologue, ArrayList<String> directors, ArrayList<String> actors, ArrayList<String> writers, ArrayList<String> genres, ArrayList<String> producers, float rate) {
        this.name = name;
        this.prologue = prologue;
        this.directors = directors;
        this.actors = actors;
        this.writers = writers;
        this.genres = genres;
        this.producers = producers;
        this.rate = rate;
    }

    //    Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrologue() {
        return prologue;
    }

    public void setPrologue(String prologue) {
        this.prologue = prologue;
    }

    public ArrayList<String> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<String> directors) {
        this.directors = directors;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getWriters() {
        return writers;
    }

    public void setWriters(ArrayList<String> writers) {
        this.writers = writers;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<String> producers) {
        this.producers = producers;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

//    Useful methods

    /**
     * Add line breaks to improve the visualization of a long text displayed by the console
     *
     * @param text       String to format
     * @param widthLimit Limit of characters that will be displayed in line in console
     * @return formatted text
     */
    protected String maxWidth(String text, int widthLimit) {

        //The immutable StringBuffer class is instantiated
        StringBuilder stringToFormat = new StringBuilder(text);

//        initialization and declaration of values to use
        int size = stringToFormat.length();
        int position = widthLimit;
        //times the for will be executed
        int limitTimes = size / position;
        //value to be incremented from the position of where to insert the line break
        int toIncrease = position + 1;

        for (int i = 0; i < limitTimes; i++) {
            stringToFormat.insert(position, "\n");
            position += toIncrease;
        }
        stringToFormat.append("\n");
        return stringToFormat.toString();
    }

    /**
     * Return a string of objects separated by commas for readability
     *
     * @param array array of objects of type String
     * @return All objects in a string separated by comma
     */
    protected String addComma(ArrayList<String> array) {
        StringBuilder toReturn = new StringBuilder("");
        array.forEach((text) -> {
            toReturn.append(text).append(", ");
        });
        return toReturn.toString();
    }

    /**
     * View Movie entity information such as its name, prologue, directors, actors, writers, and genres
     *
     * @return String with all information
     */
    public String getDetailsDescription() {
        int maxWidth = 37;
        return "--- Movie details ------------------- \n" +
                "- Name: " + name + "\n" +
                "------------------------------------- \n" +
                "- Prologue: \n" + maxWidth(prologue, maxWidth) +
                "------------------------------------- \n" +
                "- Directors: \n" + maxWidth(addComma(directors), maxWidth) +
                "------------------------------------- \n" +
                "- Actors: \n" + maxWidth(addComma(actors), maxWidth) +
                "------------------------------------- \n" +
                "- Writers: \n" + maxWidth(addComma(writers), maxWidth) +
                "------------------------------------- \n" +
                "- Genres: \n" + maxWidth(addComma(genres), maxWidth) +
                "------------------------------------- \n";

    }

}

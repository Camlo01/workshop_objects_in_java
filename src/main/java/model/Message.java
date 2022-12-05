package model;

import java.util.Date;

/**
 * Class representing a Message with useful methods.
 *
 * @author Camilo Beltrán
 */
public class Message {

    private String author;
    private String addressee;
    private String body;
    private final Date date;
    private boolean status;
    private boolean edited;

//    Constructors


    /**
     * Empty constructor
     */
    public Message() {
        this.date = new Date();
        this.status = true;
        this.edited = false;
    }

    /**
     * Constructors with necessary information
     */
    public Message(String author, String addressee, String body) {
        this.author = author;
        this.addressee = addressee;
        this.body = body;
        this.date = new Date();
        this.status = true;
        this.edited = false;
    }

//    getters and setters

    public String getAuthor() {
        return author;
    }

    public String getAddressee() {
        return addressee;
    }


    public String getBody() {
        return body;
    }


    public Date getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }


//    Useful methods

    /**
     * The status of the message is marked as false which indicates that the message has been deleted
     */
    public void deleteMessage() {
        this.status = false;
    }

    /**
     * The message is updated and edited property is set to true to indicate that the message has been edited
     *
     * @param newMessage new message content
     */
    public void editMessage(String newMessage) {
        this.body = newMessage;
        this.edited = true;
    }

}

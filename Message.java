import java.io.File;
import java.util.Date;

public class Message {
    private String text;
    private File image;
    private Category category;
    private Importance importance;
    private User sender;
    private Office recipient;
    private String ID;
    private Date date;

    public Message(User sender, Office recipient, String text, File image) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = image;
        this.date = new Date();
    }

    public Message(User sender, Office recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = null;
        this.date = new Date();
    }

    public User getSender() {
        return sender;
    }

    public Office getRecipient() {
        return recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(Office recipient) {
        this.recipient = recipient;
    }

    public void displayMessage() {
        System.out.println("To: " + recipient);
        System.out.println("From: " + sender + "\nDate: " + date);
        System.out.println(text);
        if (image != null) {
            System.out.println();
            System.out.println(image);
        }
    }
}

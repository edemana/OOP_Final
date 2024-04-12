import java.io.File;
import java.util.Date;
import java.util.Locale.Category;

public class Message {
    private String text;
    private File image;
    private enum Category {Water, Electricity, Internet, Bins, Others};
    private Importance importance;
    private UserComplaints sender;
    private UserComplaints recipient;
    private String ID;
    private Date date;

    public Message(UserComplaints sender, UserComplaints recipient, String text, File image) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = image;
        this.date = new Date();
    }

    public Message(UserComplaints sender, UserComplaints recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = null;
        this.date = new Date();
    }

    public UserComplaints getSender() {
        return sender;
    }

    public UserComplaints getRecipient() {
        return recipient;
    }

    public void setSender(UserComplaints sender) {
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

    public String getID() {
        return ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

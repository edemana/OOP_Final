import java.io.File;
import java.util.Date;
import java.util.Locale.Category;

public class Message {
    private String text;
    private File image;
    private Category category;
    private Importance importance;
    private UserComplaint sender;
    private Office recipient;
    private String ID;
    private Date date;

    public Message(UserComplaint sender, Office recipient, String text, File image) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = image;
        this.date = new Date();
    }

    public Message(UserComplaint sender, Office recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = null;
        this.date = new Date();
    }

    public UserComplaint Sender() {
        return sender;
    }

    public Office getRecipient() {
        return recipient;
    }

    public void setSender(UserComplaint sender) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

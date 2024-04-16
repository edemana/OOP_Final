import java.io.File;
import java.util.Date;

public class Message {
    private String text; // Text content of the message
    private String image; // Image attached to the message
    public enum Category {Water, Electricity, Internet, Bins, Others}; // Enum for general complaint categories
    public enum ClimateChangeCategory {Water, Electricity, Internet, Bins}; // Enum for climate change-related complaint categories
    private UserComplaints sender; // Sender of the message
    private UserComplaints recipient; // Recipient of the message
    private String ID; // Unique identifier for the message
    private Date date; // Date when the message was created
    private String status; // Status of the message (e.g., open, closed)

   /**
     * Constructor for creating a general message.
     * @param sender The sender of the message.
     * @param recipient The recipient of the message.
     * @param text The text content of the message.
     * @param image The image attached to the message.
     * @throws IllegalArgumentException if the sender, recipient, or text is null.
     */
    public Message(UserComplaints sender, UserComplaints recipient, String text, String image) {
        // Check if sender, recipient, or text is null, and throw an exception if so
        if (sender == null || recipient == null || text == null) {
            throw new IllegalArgumentException("Sender, recipient, and text cannot be null.");
        }
        // Initialize the message attributes
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = image;
        this.date = new Date(); // Sets the creation date
    }

    /**
     * Constructor for creating a new Message object without an image.
     * @param sender The UserComplaints object representing the sender of the message.
     * @param recipient The UserComplaints object representing the recipient of the message.
     * @param text The text content of the message.
     * @throws IllegalArgumentException If sender, recipient, or text is null.
     */
    public Message(UserComplaints sender, UserComplaints recipient, String text) {
        if (sender == null || recipient == null || text == null) {
            throw new IllegalArgumentException("Sender, recipient, and text cannot be null");
        }
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
        this.image = null;
        this.date = new Date(); // Sets the creation date
    }

    // Getters and Setters 
    /**
     * Gets the sender of the message.
     * @return The UserComplaints object representing the sender.
     */
    public UserComplaints getSender() {
        return sender;
    }
       
    /**
     * Sets the sender of the message.
     * @param sender The UserComplaints object to be set as the sender.
     * @throws IllegalArgumentException If the provided sender is null.
     */
    public void setSender(UserComplaints sender) {
        if (sender == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        this.sender = sender;
    }
    
    /**
     * Sets the recipient of the message (assuming Office extends UserComplaints for flexibility).
     * @param recipient The UserComplaints object to be set as the recipient. 
     * @throws IllegalArgumentException If the provided recipient is null.
     */
    public void setRecipient(UserComplaints recipient) { 
        if (recipient == null) {
            throw new IllegalArgumentException("Recipient cannot be null");
        }
        this.recipient = recipient;
    }
       
    /**
     * Gets the recipient of the message.
     * @return The UserComplaints object representing the recipient.
     */
    public UserComplaints getRecipient() {
        return recipient;
    }
    
    /**
     * Gets the unique ID of the message.
     * @return The message's ID.
     */
    public String getID() {
        return ID;
    }
       
    /**
     * Sets the unique ID of the message.
     * @param ID The ID to be assigned to the message. 
     */
    public void setID(String ID) {
        this.ID = ID;
    }
       
    /**
     * Sets the creation date of the message.
     * @param date The Date object representing the creation time.
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * Gets the creation date of the message.
     * @return The Date object representing the creation time. 
     */
    public Date getDate() {
    return date;
    }
    
    /**
     * Gets the text content of the message.
     * @return The message's text.
     */
    public String getText() {
        return text;
    }
    
    /**
     * Sets the text content of the message.
     * @param text The text to be set as the message's content.
     * @throws IllegalArgumentException If the provided text is null.
     */
    public void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.text = text;
    }
    /**
     * Gets the image content of the message.
     * @return The message's image file.
     */
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        }
   
     /**
     * Sets the status of the message.
     * @param status The status to set for the message.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * Gets the status of the message.
     * @return The status of the message.
     */
    public String getStatus() {
        return status;
    }
}

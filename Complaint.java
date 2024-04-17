import java.io.File;

/**
 * Represents a complaint message.
 */
public class Complaint extends Message {
    private Message.Category chosen_Category; // Category chosen for the complaint
    private String status; // Status of the complaint

    /**
     * Constructor for creating a complaint message.
     * @param sender The sender of the complaint.
     * @param recipient The recipient of the complaint.
     * @param text The text content of the complaint.
     * @param image The image attached to the complaint.
     * @param category The category of the complaint.
     */
    public Complaint(UserComplaints sender, Office recipient, String text, String image, Message.Category category) {
        // Call the superclass constructor to initialize sender, recipient, text, and image
        super(sender, recipient, text, image);
        // Set the chosen category for the complaint
        this.chosen_Category = category;
        // Set the initial status of the complaint to "open"
        this.status = "open";
    }

    /**
     * Overrides the getRecipient method from Message, ensures it returns an Office object.
     * @return The Office object representing the recipient of the complaint.
     * @throws ClassCastException If the recipient is not an instance of Office.
     */
    @Override
    public Office getRecipient() {
        Office office = (Office) super.getRecipient(); 
        if (office == null) {
            throw new ClassCastException("Complaint recipient must be an Office");
        }
        return office;
    }

    /**
     * Changes the status of the complaint to "responded".
     */
    public void markResponded() {
        this.status = "responded";
    }

    public Message.Category getChosenCategory() {
        return chosen_Category;
    }

    /**
     * @return The current status of the complaint.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of the complaint.
     * @param status The new status value.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Overrides the getSender method from Message, ensures it returns a UserComplaints object.
     * @return The UserComplaints object representing who sent the complaint.
     * @throws ClassCastException If the sender is not an instance of UserComplaints.
     */
    @Override
    public UserComplaints getSender() {
        return (UserComplaints) super.getSender();

    }

   /**
    * Gets the category of the complaint message.
    * @return The category chosen for the complaint message.
    */
    public Message.Category getCategory() {
    return this.chosen_Category;
    }
}

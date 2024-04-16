import java.io.File;

public class Complaint extends Message {
    private int chosen_Category; // Stores the index of the selected category
    private String status;       // Indicates the status of the complaint (e.g., "open", "responded")

    /**
     * Creates a new Complaint object.
     * @param sender The UserComplaints object representing the person making the complaint.
     * @param recipient The Office object representing the recipient of the complaint.
     * @param text The text content of the complaint.
     * @param image An optional image related to the complaint.
     * @param category The Category of the complaint.
     */
    public Complaint(UserComplaints sender, Office recipient, String text, File image, Category category) {
        super(sender, recipient, text, image); 
        this.chosen_Category = category.ordinal();  // Store the Category's ordinal value
        this.status = "open"; // Initialize the complaint's status as "open"
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

    /**
     * @return The ordinal value representing the chosen complaint category.
     */
    public int getChosenCategory() {
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
        UserComplaints sender = (UserComplaints) super.getSender();
        if (sender == null) {
            throw new ClassCastException("Complaint sender must be a UserComplaints object");
        }
        return sender;
    }
}

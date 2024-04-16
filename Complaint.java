import java.io.File;

public class Complaint extends Message {
    private Message.Category chosen_Category;
    private String status;

    public Complaint(UserComplaints sender, Office recipient, String text, String image, Message.Category category) {
        super(sender, recipient, text, image);
        this.chosen_Category = category;
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

    public Message.Category getCategory() {
        return this.chosen_Category;
    }
}

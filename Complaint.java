import java.io.File;
import java.util.Locale.Category;

public class Complaint extends Message {
    private int chosen_Category;
    private String status;

    public Complaint(UserComplaints sender, Office recipient, String text, File image, Category category) {
        super(sender, recipient, text, image);
        this.chosen_Category = category.ordinal();
        this.status = "open";
    }

    @Override
    public Office getRecipient() {
        return (Office) super.getRecipient();
    }

    public void markResponded() {
        this.status = "responded";
    }

    public int getChosenCategory() {
        return chosen_Category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public UserComplaints getSender() {
        return (UserComplaints) super.getSender();

    }
}

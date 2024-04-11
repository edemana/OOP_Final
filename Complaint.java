import java.io.File;

public class Complaint extends Message {
    private Importance importance;
    private String status;

    public Complaint(User sender, Office recipient, String text, File image, Importance importance) {
        super(sender, recipient, text, image);
        this.importance = importance;
        this.status = "open";
    }

    @Override
    public Office getRecipient() {
        return (Office) super.getRecipient();
    }

    public void markResponded() {
        this.status = "responded";
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

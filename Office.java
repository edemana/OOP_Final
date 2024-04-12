import java.util.ArrayList;
import java.util.List;

public class Office extends UserComplaints {
    private String departmentName;
    private List<Complaint> complaints;

    public Office(String departmentName, String userName, String email, String password) {
        super(userName, email, password);
        this.departmentName = departmentName;
        this.complaints = new ArrayList<>();
    }

    public void receiveComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    public boolean respond(Complaint complaint, String text) {
        if (complaint.getRecipient().equals(this.departmentName)) {
            complaint.getSender().send(new Message(complaint.getSender(), this, text));
            complaint.markResponded();
            return true;
        }
        return false;
    }

    public List<Complaint> topFiveComplaints() {
        List<Complaint> topFive = new ArrayList<>();
        // Assuming we want to return the first five complaints in the list
        for (int i = 0; i < Math.min(5, complaints.size()); i++) {
            topFive.add(complaints.get(i));
        }
        return topFive;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

}

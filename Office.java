import java.util.ArrayList;
import java.util.List;

public class Office extends UserComplaints {
    private String departmentName; 
    private List<Complaint> complaints; 

    /**
     *  Constructor to create an Office object.
     *  @param departmentName The name of the department this office represents.
     *  @param userName The username associated with the office.
     *  @param email The email address associated with the office.
     *  @param password The password used by the office.
     */
    public Office(String departmentName, String userName, String email, String password) {
        super(userName, email, password);
        this.departmentName = departmentName;
        this.complaints = new ArrayList<>();
    }

    /**
     * Records a new complaint received by the office.
     * @param complaint The Complaint object to be added to the office's records.
     */
    public void receiveComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    /**
     * Allows the office to respond to a complaint.
     * @param complaint The complaint the office is responding to.
     * @param text The text of the office's response. 
     * @return True if the office was the intended recipient and the response was sent, false otherwise.
     * @throws InvalidComplaintException If the office is not the intended recipient of the complaint.
     */
    public boolean respond(Complaint complaint, String text) {
        if (!complaint.getRecipient().equals(this.departmentName)) {
            throw new InvalidComplaintException("This office is not the intended recipient of the complaint");
        }

        // Send a message back to the sender of the complaint
        complaint.getRecipient().send((UserComplaints) complaint.getSender(), text, null); 
        complaint.markResponded();
        return true; 
    }

    /**
     * Returns the top five complaints received by the office (assuming top five means first five).
     * If there are fewer than five complaints, returns all available complaints.
     * @return A list of Complaint objects representing the top five.
     */
    public List<Complaint> topFiveComplaints() {
        List<Complaint> topFive = new ArrayList<>();

        // Add up to the first five complaints to the topFive list 
        for (int i = 0; i < Math.min(5, complaints.size()); i++) { 
            topFive.add(complaints.get(i));
        }
        return topFive;
    }

    // Getters and Setters
    /**
     * Returns the department name.
     *
     * @return departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * Sets the department name.
     *
     * @param departmentName The new department name
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    /**
     * Returns the list of complaints associated with the office.
     *
     * @return complaints
     */
    public List<Complaint> getComplaints() {
        return complaints;
    }
    /**
     * Sets the list of complaints for the office.
     *
     * @param complaints The new list of complaints
     */
    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}

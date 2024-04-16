public class Student extends UserComplaints {
    private String major;
    private String studentID;

    /**
     *  Constructor of the Student class which represents a student complainer.
     *  @param userName The name of the student.
     *  @param email The email address of the complaining student. 
     *  @param password The password of the student user.
     *  @param major The student's major.
     *  @param studentID The student's ID.
     *  @throws IllegalArgumentException If any of the parameters are null or empty.
     */
    public Student(String userName, String email, String password, String major, String studentID) {
        super(userName, email, password); 

        // Input Validation
        if (userName == null || userName.isEmpty() || email == null || email.isEmpty() ||
            password == null || password.isEmpty() || major == null || major.isEmpty() || 
            studentID == null || studentID.isEmpty()) {
            throw new IllegalArgumentException("All student information fields are required");
        }

        this.major = major;
        this.studentID = studentID;
    }

     /**
     * Allows a student to send a follow-up message on an existing complaint.
     * @param msg The original Complaint object for which to send a follow-up.
     * @return True if the follow-up was sent successfully, false otherwise.
     * @throws InvalidComplaintStatusException If the complaint is not in the "open" state.
     * @throws MessageSendException If there's a general error in sending the message.
     */
    public boolean followUp(Complaint msg){
        //if the message is not responded to, send a follow up message
        if (msg.getStatus() == "open"){
            Message followUp = new Message(this, msg.getRecipient(), String.format("Follow up message on this message: %s", msg.getText()));
            msg.getRecipient().receive(followUp, this);
            return true;
        }
        return false;
    }
    }
    // Getters
    /**
     * Gets the major of the student.
     * @return The student's major.
     */
    public String getMajor() {
        return major;
    }

    /**
     * Gets the student ID.
     * @return The student's ID.
     */
    public String getStudentID() {
        return studentID;
    }

    // Setters
    /**
     * Sets the major of the student.
     * @param major The new major of the student.
     * @throws IllegalArgumentException If the provided major is null or empty.
     */
    public void setMajor(String major) {
        if (major == null || major.isEmpty()) {
            throw new IllegalArgumentException("Major cannot be null or empty");
        }
        this.major = major;
    }

    /**
     * Sets the student ID.
     * @param studentID The new student ID.
     * @throws IllegalArgumentException If the provided student ID is null or empty.
     */
    public void setStudentID(String studentID) {
        if (studentID == null || studentID.isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        this.studentID = studentID;
    }
}

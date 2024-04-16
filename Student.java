public class Student extends UserComplaints {
    private String major;
    private String studentID;

    /**
     * Constructor for Student class.
     * @param name The name of the student.
     * @param email The email address of the student.
     * @param password The password of the student.
     * @param major The major of the student.
     * @param studentID The student ID of the student.
     */
    public Student(String name, String email, String password, String major, String studentID) {
        super(name, email, password);
        this.major = major;
        this.studentID = studentID;
    }

    /**
     * Sends a follow-up message if the original message is not responded to.
     * @param msg The original message.
     * @return True if the follow-up message is sent successfully, false otherwise.
     */
    public boolean followUp(Message msg) {
        // Check if the message is not responded to
        if (msg.getStatus().equals("open")) {
            // Send a follow-up message
            Message followUp = new Message(this, msg.getRecipient(), String.format("Follow up message on this message: %s", msg.getText()));
            msg.getRecipient().receive(followUp, this);
            return true;
        }
        return true;
    }

    /**
     * Gets the name of the student.
     * @return The name of the student.
     */
    public String getName() {
        return super.getUserName();
    }

    /**
     * Sets the major of the student.
     * @param major The new major to set.
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Sets the student ID of the student.
     * @param studentID The new student ID to set.
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * 
     * @param userName The new username to set.
     * @throws IllegalArgumentException if the provided username is null or empty.
     */
    public void setName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        super.setUserName(userName);
    }

    /**
     * Overrides the setter method for email.
     * @param email The new email address to set.
     * @throws IllegalArgumentException if the provided email address is null or empty.
     */
    @Override
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        super.setEmail(email);
    }

    /**
     * Overrides the setter method for password.
     * @param password The new password to set.
     * @throws IllegalArgumentException if the provided password is null or empty.
     */
    @Override
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        super.setPassword(password);
    }
}

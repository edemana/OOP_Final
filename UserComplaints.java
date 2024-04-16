import java.io.File;
import java.util.ArrayList;

/**
 * Represents a user who can send complaints/messages.
 */
public class UserComplaints {
    private String userName;
    private String email;
    private String password;
    private History history;

    /**
     * Default constructor.
     */
    public UserComplaints() {
    }

    /**
     * Constructor with parameters.
     * @param userName The username of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     */
    public UserComplaints(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.history = new History();
    }

    /**
     * Sends a general message.
     * @param recipient The recipient of the message.
     * @param message The message content.
     * @param image The image file attached to the message (can be null if no image).
     * @return True if the message is sent successfully, false otherwise.
     */
    public boolean send(Office recipient, String message, File image) {
        Message msg = new Message(this, recipient, message);
        if (image != null) {
            msg = new Message(this, recipient, message, image);
        }
        recipient.receive(msg, this);
        history.addOutbox(msg);
        return true;
    }

    /**
     * Sends a complaint message.
     * @param recipient The recipient of the complaint.
     * @param message The complaint message content.
     * @param image The image file attached to the complaint (can be null if no image).
     * @param category The category of the complaint.
     * @return True if the complaint is sent successfully, false otherwise.
     */
    public boolean send(Office recipient, String message, File image, Category category) {
        Complaint msg = new Complaint(this, recipient, message, image, category);
        recipient.receive(msg, this);
        history.addOutbox(msg);
        return true;
    }

    /**
     * Sends a message to another UserComplaints object.
     * @param recipient The recipient of the message.
     * @param message The message content.
     * @param image The image file attached to the message (can be null if no image).
     * @return True if the message is sent successfully, false otherwise.
     */
    public boolean send(UserComplaints recipient, String message, File image) {
        Message msg = new Message(this, recipient, message);
        if (image != null) {
            msg = new Message(this, recipient, message, image);
        }
        recipient.receive(msg, this);
        history.addOutbox(msg);
        return true;
    }

    /**
     * Receives a message.
     * @param msg The message received.
     * @param sender The sender of the message.
     * @return True if the message is received successfully, false otherwise.
     */
    public boolean receive(Message msg, UserComplaints sender) {
        history.addInbox(msg);
        return true;
    }

    /**
     * Views messages either from the inbox or the outbox.
     * @param text Specifies whether to view messages from the inbox or the outbox.
     * @return The list of messages to be viewed.
     */
    public ArrayList<Message> viewMessages(String text) {
        if (text.equals("inbox")) {
            return history.getInbox();
        } else {
            return history.getOutbox();
        }
    }

    /**
     * Gets the username of the user.
     * @return The username of the user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the email address of the user.
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username of the user.
     * @param userName The new username to set.
     * @throws IllegalArgumentException if the provided username is null or empty.
     */
    public void setUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.userName = userName;
    }

    /**
     * Sets the email address of the user.
     * @param email The new email address to set.
     * @throws IllegalArgumentException if the provided email address is null or empty.
     */
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    /**
     * Sets the password of the user.
     * @param password The new password to set.
     * @throws IllegalArgumentException if the provided password is null or empty.
     */
    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }
}

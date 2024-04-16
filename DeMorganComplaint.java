import java.io.File;
import java.util.ArrayList;

public class DeMorganComplaint {
    // Define states for the user
    final int STATE_LOGIN = 0;
    final int STATE_SEND = 1;
    final int STATE_RECEIVE = 2;
    final int STATE_VIEW = 3;

    // Initial state
    int state = STATE_LOGIN;
    UserComplaints user;
    Office office;

    // Main method to simulate user interactions
    public static void main(String[] args) {
        // Create an instance of DeMorganComplaint
        DeMorganComplaint complaintSystem = new DeMorganComplaint();

        // Simulate user interactions
        complaintSystem.login();
        complaintSystem.send();
        complaintSystem.receive();
        complaintSystem.respond();
        complaintSystem.view();
        complaintSystem.logout();
    }

    // Getter for the current state
    public int getState() {
        return state;
    }

    // Setter for the state
    public void setState(int state) {
        this.state = state;
    }

    // Method to simulate user login
    public void login() {
        if (state == STATE_LOGIN) {
            // Perform login
            user = new UserComplaints("John Doe", "john@example.com", "password");
            setState(STATE_VIEW);
            System.out.println("Logged in successfully.");
        } else {
            // Throw error if already logged in
            throw new IllegalStateException("Error: Already logged in.");
        }
    }

    // Method to simulate sending a complaint
    public void send() {
        if (state == STATE_VIEW) {
            // Ensure the office object is initialized
            if (office == null) {
                office = new Office("IT Department", "IT", "it@example.com", "password");
            }

            // Simulate sending a complaint
            File image = new File("complaint_image.jpg");
            user.send(office, "Internet is down", image, Message.Category.Internet);
            setState(STATE_SEND);
            System.out.println("Complaint sent successfully.");
        } else {
            // Throw error if not in correct state
            throw new IllegalStateException("Error: Cannot send complaint. Please login first.");
        }
    }

    // Method to simulate office receiving complaints
    public void receive() {
        if (state == STATE_SEND) {
            // Simulate office receiving complaints
            System.out.println("Office receiving complaints...");
            // Simulate receiving complaints
            Complaint complaint = new Complaint(user, office, "Internet is down", null, Message.Category.Internet);
            office.receiveComplaint(complaint);
            setState(STATE_RECEIVE);
            System.out.println("Complaint received successfully.");
        } else {
            // Throw error if not in correct state
            throw new IllegalStateException("Error: Cannot receive complaints. Please send a complaint first.");
        }
    }

    // Method to simulate office responding to a complaint
    public void respond() {
        if (state == STATE_RECEIVE) {
            // Simulate office responding to a complaint
            System.out.println("Office responding to complaint...");
            // Simulate responding to a complaint
            Complaint receivedComplaint = office.getComplaints().get(0); // Assuming first complaint
            office.respond(receivedComplaint, "We are working on it.");
            setState(STATE_VIEW);
            System.out.println("Response sent successfully.");
        } else {
            // Throw error if not in correct state
            throw new IllegalStateException("Error: Cannot respond. No complaints received.");
        }
    }

    // Method to simulate user viewing response
    public void view() {
        if (state == STATE_VIEW) {
            // Simulate user viewing response
            System.out.println("User viewing response...");
            // Simulate viewing response
            ArrayList<Message> inboxMessages = user.viewMessages("inbox");
            if (inboxMessages.isEmpty()) {
                System.out.println("No messages in inbox.");
            } else {
                for (Message message : inboxMessages) {
                    message.displayMessage();
                }
            }
        } else {
            // Throw error if not in correct state
            throw new IllegalStateException("Error: Cannot view response. Please login first.");
        }
    }

    // Method to simulate user logout
    public void logout() {
        if (state == STATE_VIEW || state == STATE_SEND || state == STATE_RECEIVE) {
            // Simulate logout
            user = null;
            setState(STATE_LOGIN);
            System.out.println("Logged out successfully.");
        } else {
            // Throw error if not in correct state
            throw new IllegalStateException("Error: Cannot logout. No user logged in.");
        }
    }
}

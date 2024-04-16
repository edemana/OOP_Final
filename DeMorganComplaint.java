import java.io.File;
import java.util.ArrayList;

public class DeMorganComplaint {
    // Manage state of the user
    final int STATE_LOGIN = 0;
    final int STATE_SEND = 1;
    final int STATE_RECEIVE = 2;
    final int STATE_VIEW = 3;

    int state = STATE_LOGIN;
    UserComplaints user;
    Office office;

    public static void main(String[] args) {
        // Create an instance of DeMorganComplaint
        DeMorganComplaint complaintSystem = new DeMorganComplaint();

        // Simulate user interactions
        complaintSystem.login();

        // Simulate sending a complaint
        complaintSystem.send();

        // Simulate office receiving complaints
        complaintSystem.receive();

        // Simulate office responding to a complaint
        complaintSystem.respond();

        // Simulate user viewing response
        complaintSystem.view();

        // Simulate logging out
        complaintSystem.logout();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void login() {
        if (state == STATE_LOGIN) {
            // Perform login
            user = new UserComplaints("John Doe", "john@example.com", "password");
            setState(STATE_VIEW);
            System.out.println("Logged in successfully.");
        } else {
            // Throw error
            System.out.println("Error: Already logged in.");
        }
    }

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
            // Throw error
            System.out.println("Error: Cannot send complaint. Please login first.");
        }
    }

    public void receive() {
        if (state == STATE_SEND) {
            // Simulate office receiving complaints
            System.out.println("Office receiving complaints...");
            // Assume office is already initialized
            // Simulate receiving complaints
            Complaint complaint = new Complaint(user, office, "Internet is down", null, Message.Category.Internet);
            office.receiveComplaint(complaint);
            setState(STATE_RECEIVE);
            System.out.println("Complaint received successfully.");
        } else {
            // Throw error
            System.out.println("Error: Cannot receive complaints. Please send a complaint first.");
        }
    }

    public void respond() {
        if (state == STATE_RECEIVE) {
            // Simulate office responding to a complaint
            System.out.println("Office responding to complaint...");
            // Assume office is already initialized
            // Simulate responding to a complaint
            Complaint receivedComplaint = office.getComplaints().get(0); // Assuming first complaint
            office.respond(receivedComplaint, "We are working on it.");
            setState(STATE_VIEW);
            System.out.println("Response sent successfully.");
        } else {
            // Throw error
            System.out.println("Error: Cannot respond. No complaints received.");
        }
    }

    public void view() {
        if (state == STATE_VIEW) {
            // Simulate user viewing response
            System.out.println("User viewing response...");
            // Assume user is already initialized
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
            // Throw error
            System.out.println("Error: Cannot view response. Please login first.");
        }
    }


    public void logout() {
        if (state == STATE_VIEW || state == STATE_SEND || state == STATE_RECEIVE) {
            // Simulate logout
            user = null;
            setState(STATE_LOGIN);
            System.out.println("Logged out successfully.");
        } else {
            // Throw error
            System.out.println("Error: Cannot logout. No user logged in.");
        }
    }
}

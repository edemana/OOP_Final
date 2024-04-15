import java.io.File;

public class DeMorganComplaint {
    // Manage state of the user
    final int STATE_LOGIN = 0;
    final int STATE_SEND = 1;
    final int STATE_RECEIVE = 2;
    final int STATE_VIEW = 3;

    int state = STATE_LOGIN;
    UserComplaints user;

    public static void main(String[] args) {
        // Create an instance of DeMorganComplaint
        DeMorganComplaint complaintSystem = new DeMorganComplaint();

        // Simulate user interactions
        complaintSystem.login();

        // Simulate sending a complaint
        complaintSystem.send();

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
            // Simulate sending a complaint
            File image = new File("complaint_image.jpg");
            user.send(new Office("IT Department", "IT", "it@example.com", "password"), "Internet is down", image);
            setState(STATE_SEND);
            System.out.println("Complaint sent successfully.");
        } else {
            // Throw error
            System.out.println("Error: Cannot send complaint. Please login first.");
        }
    }

    public void receive() {
        if (state == STATE_VIEW) {
            // Simulate receiving complaints
            System.out.println("Checking for new complaints...");
            // Code to receive complaints goes here
            setState(STATE_RECEIVE);
        } else {
            // Throw error
            System.out.println("Error: Cannot receive complaints. Please login first.");
        }
    }
    
    public void view() {
        if (state == STATE_LOGIN) {
            // Throw error
            System.out.println("Error: Cannot view complaints. Please login first.");
        } else {
            // Simulate viewing complaints
            System.out.println("Viewing complaints...");
            // Code to view complaints goes here
            setState(STATE_VIEW);
        }
    }

    public void logout() {
        if (state == STATE_VIEW || state == STATE_SEND) {
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

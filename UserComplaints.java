import java.io.File;
import java.util.ArrayList;


public class UserComplaints {
    private String userName;
    private String email;
    private String password;
    private History history;

    // private History history

    public UserComplaints() {

    }

    public UserComplaints(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.history = new History();
    }


    public boolean send(Office recepient, String message, String image) {
        Message msg = new Message(this, recepient, message);
        if (image != null) {
            msg = new Message(this, recepient, message, image);
        }
        recepient.receive(msg, this);
        history.addOutbox(msg);
        return true;
    }

    public boolean send(Office recepient, String message, String image, Message.Category category){
        Complaint msg = new Complaint(this,recepient,message,image,category);
        recepient.receive(msg,this); 
        history.addOutbox(msg);
        return true;

    }
    public boolean send(UserComplaints recepient, String message, String image){
        Message msg = new Message(this,recepient,message);
        if (image != null) {
            msg = new Message(this,recepient,message,image);
        }
        recepient.receive(msg,this); 
        history.addOutbox(msg);

        return true;

    }

    public boolean receive(Message msg, UserComplaints sender) {
        if (history == null) {
            System.out.println("Error: History object is not initialized.");
            return false;
        }
        history.addInbox(msg);
        return true;
    }
    

    public ArrayList<Message> viewMessages(String text) {
        ArrayList<Message> messagesToShow = new ArrayList<>();
        if (text.equals("inbox")) {
            messagesToShow = history.getInbox();
        } else {
            messagesToShow = history.getOutbox();
        }
        return messagesToShow;
    }
    

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

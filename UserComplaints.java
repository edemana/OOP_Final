import java.io.File;
import java.util.ArrayList;
import java.util.Locale.Category;

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
    }


    public boolean send(Office recepient, String message, File image) {
        Message msg = new Message(this, recepient, message);
        if (image != null) {
            msg = new Message(this, recepient, message, image);
        }
        recepient.receive(msg, this);
        history.addOutbox(msg);
        return true;
    }

    public boolean send(Office recepient, String message, File image, Category category){
        if (this instanceof student) {
                Complaint msg = new Complaint(this,recepient,message,image,category);
                recepient.receive(msg,this); 
                history.addOutbox(msg);
                return true;
        }

    }
    public boolean send(UserComplaints recepient, String message, File image){
            Message msg = new Message(this,recepient,message);
            if (image != null) {
                msg = new Message(this,recepient,message,image);
            }
            recepient.receive(msg,this); 
            history.addOutbox(msg);

            return true;

    }

    public boolean receive(Message msg, UserComplaints sender) {
        history.addInbox(msg);
        return true;
    }

    public ArrayList<Message> viewMessages(String text){
        if (text.equals("inbox")){
           return history.getInbox();
        }
        else{
            return history.getOutbox();
        }
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

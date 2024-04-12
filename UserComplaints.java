import java.io.File;
public class UserComplaints {
    private String userName;
    private String email;
    private String password;
    private History history;

    //private History history

    public UserComplaints(){

    }

    public UserComplaints(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public boolean send(Office recepient, String message, File image){
        Message msg = new Message(this,recepient,message);
        if (image != null) {
            msg = new Message(this,recepient,message,image);
        }
        recepient.receive(msg,this); 
        history.addOutbox(msg);
        return true;
    }

    public boolean receive(Message msg,UserComplaints sender){
        history.addInbox(msg);
        return true;
    }

    // public void viewMessages(History user_History){

    // }

    public String getUserName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    
}

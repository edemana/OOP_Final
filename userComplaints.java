
public class userComplaints {
    private String userName;
    private String email;
    private String password;

    //private History history

    public userComplaints(){

    }

    public userComplaints(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // public boolean send(Message msg, Office recipient){
        
    // }

    // public boolean receive(Message msg,Office sender){

    // }

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

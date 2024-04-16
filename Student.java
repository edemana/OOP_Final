
public class Student extends UserComplaints {
    private String major;
    private String studentID;

    public Student(String name,String email, String password, String major, String studentID){
        super(name, email, password);
        this.major = major;
        this.studentID = studentID;
    }

    public boolean followUp(Message msg){
        //if the message is not responded to, send a follow up message
        if (msg.getStatus() == "open"){
            Message followUp = new Message(this, msg.getRecipient(), String.format("Follow up message on this message: %s", msg.getText()));
            msg.getRecipient().receive(followUp, this);
            return true;
        }
        return true;
    }

    public String getName(){
        return super.getUserName();
    }
}

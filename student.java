
public class student extends UserComplaints {
    private String major;
    private String studentID;

    public student(String name,String email, String password, String major, String studentID){
        super(name, email, password);
        this.major = major;
        this.studentID = studentID;
    }

    public boolean followUp(Message.getID()){
        return true;
    }
}

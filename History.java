import java.util.ArrayList;

public class History {
    private ArrayList<Message> inbox;
    private ArrayList<Message> outbox;
    private String[] ClimateChangeIssues = { "Global Warming", "Deforestation", "Pollution", "Overpopulation",
            "Water"};
    
    public History() {
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
    }

    public void addInbox(Message message) {
        //add message to inbox depending on the weather its is a climate change issue or based on time
        for (int i = 0; i < ClimateChangeIssues.length; i++) {
            if (message.getText().contains(ClimateChangeIssues[i])) {
                //add message to the first place of the inbox and push the others
                inbox.add(0, message);
                return;
            }
        }
    }

    public void addOutbox(Message message) {
        outbox.add(message);
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public ArrayList<Message> getOutbox() {
        return outbox;
    }

    public boolean remove(String ID) {
        for (int i = 0; i < inbox.size(); i++) {
            if (inbox.get(i).getID() == ID) {
                inbox.remove(i);
                return true;
            }
        }
        for (int i = 0; i < outbox.size(); i++) {
            if (outbox.get(i).getID() == ID) {
                outbox.remove(i);
                return true;
            }
        }
        return false;
    }
}
import java.util.ArrayList;

public class History {
    private ArrayList<Message> inbox;
    private ArrayList<Message> outbox;
    
    public History() {
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
    }

    public void addInbox(Message message) {
        inbox.add(message);
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
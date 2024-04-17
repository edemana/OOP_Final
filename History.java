import java.util.ArrayList;

public class History {
    private ArrayList<Message> inbox;
    private ArrayList<Message> outbox;
    private String[] ClimateChangeIssues = { "Global Warming", "Deforestation", "Pollution", "Overpopulation",
            "Water"};

    /**
     * Constructs a new History object with empty inbox and outbox.
     */
    public History() {
        this.inbox = new ArrayList<Message>();
        this.outbox = new ArrayList<Message>();
    }

    /**
     * Adds a message to the inbox.
     * 
     * @param msg The message to be added to the inbox.
     * @return true if the message is successfully added, false otherwise.
     */
    public boolean addInbox(Message msg) {
        if (msg != null) {
            // Check if the message contains climate change issues
            boolean isClimateChangeIssue = containsClimateChangeIssue(msg);

            // Add the message to the appropriate position in the inbox array
            if (isClimateChangeIssue) {
                inbox.add(0, msg); // Add at the beginning of the array
            } else {
                inbox.add(msg); // Add at the end of the array
            }

            return true;
        } else {
            System.out.println("Error: Cannot add null message to inbox.");
            return false;
        }
    }

    /**
     * Checks if the message contains climate change issues.
     * 
     * @param msg The message to be checked.
     * @return true if the message contains climate change issues, false otherwise.
     */
    private boolean containsClimateChangeIssue(Message msg) {
        // Get the category of the issue from the message
        Message.Category category =  msg.getCategory();

        // Check if the category is Climate Change
        if (category == Message.Category.CLIMATE_CHANGE) {
            return true; // If the category is Climate Change, return true
        } else {
            // If the category is not Climate Change, perform keyword analysis
            String messageText = msg.getText().toLowerCase(); // Convert message text to lowercase for case-insensitive comparison

            // Define a list of climate change-related keywords
            String[] climateChangeKeywords = {"energy efficiency", "sustainable transportation", "renewable energy",
                    "waste management", "green spaces", "environmental policy", "climate education",
                    "carbon footprint", "carbon neutrality", "green buildings", "public transportation",
                    "bicycle infrastructure", "solar energy", "wind power", "recycling", "composting",
                    "urban forestry", "green initiatives", "carbon offsets", "sustainability curriculum"};

            // Check if any of the climate change keywords appear in the message text
            for (String keyword : climateChangeKeywords) {
                if (messageText.contains(keyword)) {
                    return true; // Return true if any keyword is found
                }
            }
        }

        return false; // Return false if no climate change-related category or keywords are found
    }

    /**
     * Adds a message to the outbox.
     * 
     * @param message The message to be added to the outbox.
     */
    public void addOutbox(Message message) {
        outbox.add(message);
    }

    /**
     * Retrieves the inbox.
     * 
     * @return The inbox containing messages.
     */
    public ArrayList<Message> getInbox() {
        return inbox;
    }

    /**
     * Retrieves the outbox.
     * 
     * @return The outbox containing messages.
     */
    public ArrayList<Message> getOutbox() {
        return outbox;
    }

    /**
     * Removes a message from either the inbox or outbox based on its ID.
     * 
     * @param ID The ID of the message to be removed.
     * @return true if the message is successfully removed, false otherwise.
     */
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

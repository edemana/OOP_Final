

public class DeMorganComplaint {
    // manage state of the user
    final int STATE_LOGIN = 0;
    final int STATE_SEND = 1;
    final int STATE_RECEIVE = 2;
    final int STATE_VIEW = 3;

    int state = STATE_LOGIN;
    public static void main(String[] args) {
        // GUI of the application interaction with the user
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void login() {
        if (state == STATE_LOGIN) {
            // login
            setState(STATE_VIEW);
        } else {
            // throw error
        }
    }

    public void send() {
        if (state == STATE_VIEW) {
            // send
            setState(STATE_SEND);
        } else {
            // throw error
        }
    }

    public void receive() {
        if (state == STATE_VIEW) {
            // receive
            setState(STATE_RECEIVE);
        } else {
            // throw error
        }
    }
    
    public void view() {
        if (state == STATE_LOGIN) {
            // throw error
        } else {
            // view
            setState(STATE_VIEW);
        }
    }

    public void logout() {
        if (state == STATE_VIEW) {
            // logout
            setState(STATE_LOGIN);
        } else {
            // throw error
        }
    }
}
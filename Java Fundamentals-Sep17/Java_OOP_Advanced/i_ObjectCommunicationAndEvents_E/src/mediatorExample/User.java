package mediatorExample;

public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void sendMessage(String msg) {
        /**
         * use the mediator class ChatRoom
         * to proceed the event
         */
        ChatRoom.sendMessage(this, msg);
    }
}

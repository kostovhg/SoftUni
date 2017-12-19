package mediatorExample;

public class Main {
    public static void main(String[] args) {

        User user1 = new User("Simona");
        User user2 = new User("Tosho");

        user1.sendMessage("Some message");
        user2.sendMessage("Ouch");
    }
}

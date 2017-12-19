package observerExample;

public class Main {
    public static void main(String[] args) {
        Subject subject = new BitBucket();
        User user1 = new User("Toshko", subject);
        User user2 = new User("Moni", subject);
        User user3 = new User("Vasko", subject);

        subject.setCommit(user1.makeCommit());
    }
}

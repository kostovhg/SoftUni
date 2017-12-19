package observerExample;

import java.util.Random;

/**
 * Concrete class that will observe some subject
 */
public class User extends Observer {

    private String username;

    /**
     * The user should be aware witch subject it observes.
     * So we are giving it trough the constructor
     * Also, every time when we create a user, he will be attached to
     * the Subject
     * @param username
     * @param subject
     */
    public User(String username, Subject subject) {
        this.username = username;
        this.subject = subject;
        this.subject.attach(this);
    }

    /**
     * A method for
     * @return
     */
    public int makeCommit(){
        return new Random().nextInt(100);
    }

    @Override
    protected void update() {
        System.out.println("Username: " + this.username + " Commit with number: " + this.subject.getCommit());
    }
}

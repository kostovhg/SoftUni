package mediatorExample;

import java.util.Date;

/**
 * This class is a Mediator that will collect the behavior/events
 * from all objects that include it (User class)
 */
public class ChatRoom {

    public static void sendMessage(User user, String msg) {
        System.out.println(new Date().toString() + "[" + user.getName() +"] : " + msg );
    }
}

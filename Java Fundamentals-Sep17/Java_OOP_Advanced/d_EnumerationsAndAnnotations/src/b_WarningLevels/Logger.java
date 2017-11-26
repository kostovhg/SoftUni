package b_WarningLevels;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private List<Message> messageList;
    private Importance importance;

    public Logger(Importance imp) {
        this.importance = imp;
        messageList = new ArrayList<>();
    }

    public void addMessage(Importance importance, Message msg){
        if(this.importance.getPriority() <= importance.getPriority()){
            this.messageList.add(new Message(importance + ": " + msg));
        }
    }

    public Iterable<Message> getMessages() {
        //this.messageList.sort(Comparator.naturalOrder());
        return this.messageList;
    }

}

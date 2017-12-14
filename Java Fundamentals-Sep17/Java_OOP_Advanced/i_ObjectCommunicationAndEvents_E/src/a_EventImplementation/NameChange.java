package a_EventImplementation;

import java.util.EventObject;

public class NameChange extends EventObject {

    private String changedName;

    public NameChange(Object source, String changedName) {
        super(source);
        this.changedName = changedName;
    }

    public String getChangedName() {
        return this.changedName;
    }
}

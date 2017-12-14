package a_EventImplementation;

import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private String name;

    private List<NameChangeListener> listeners;

    public Dispatcher() {
        this.listeners = new ArrayList<>();
    }

    public void addNameChangeLIstener(NameChangeListener listener) {
        this.listeners.add(listener);
    }

    public void removeNameChangeListener(NameChangeListener listener) {
        this.listeners.remove(listener);
    }

    public void setName(String name) {
        this.name = name;
        this.fireNameChangeEvent(new NameChange(this, this.name));
    }

    public void fireNameChangeEvent(NameChange event) {
        for (NameChangeListener listener : this.listeners) {
            listener.handleChangedName(event);
        }
    }
}

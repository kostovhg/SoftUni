package observerExample;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class represent a object that will be observed
 */
public class Subject {

    /**
     * Property that contain a list of observers;
     */
    private List<Observer> observers;

    // some property which change will be observed by the observers
    private int commit;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * A method to attach observers to current Subject
     *
     * @param observer
     */
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    public void deattach(Observer observer) {
        this.observers.remove(observer);
    }

    public int getCommit() {
        return this.commit;
    }

    /**
     * This is the event that triggers the updateState of all observers
     * @param commit
     */
    public void setCommit(int commit) {
        this.commit = commit;
        /**
         * Call the private method that updateState the observers
         */
        this.updateObserversInfo();
    }

    /**
     * Iterate over every observer in the list with observers
     * and updateState them.
     */
    private void updateObserversInfo() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}

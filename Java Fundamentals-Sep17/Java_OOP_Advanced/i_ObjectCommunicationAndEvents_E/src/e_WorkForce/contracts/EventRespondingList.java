package e_WorkForce.contracts;


/**
 * Interface for List of Observable objects. Those objects will contain
 * Observer/s that will watch for specified triggers.
 * @param <E> - A observable objects for a list
 */
public interface EventRespondingList<E extends Observable> {

    /**
     * Update list objects. After the update, the observer will check
     * if event are triggered.
     */
    void update();

    /**
     * check the status of the objects in the list
     */
    void status();

    /**
     * Adding Object with observer!
     * @param observable - add to list only objects that can be observed
     */
    void addObservableObject(E observable);
}

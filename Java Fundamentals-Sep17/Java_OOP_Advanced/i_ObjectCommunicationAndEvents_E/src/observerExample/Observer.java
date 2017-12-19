package observerExample;

/**
 * Abstract class that will be extended by different observers;
 */
public abstract class Observer {
    protected Subject subject;
    protected abstract void update();

}

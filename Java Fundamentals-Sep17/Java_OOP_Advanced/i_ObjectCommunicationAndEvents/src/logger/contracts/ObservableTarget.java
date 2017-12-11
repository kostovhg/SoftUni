package logger.contracts;

public interface ObservableTarget extends Subject, Target {

    void register(Observer observer);

    void unregister(Observer observer);

    void notifyObservers();
}

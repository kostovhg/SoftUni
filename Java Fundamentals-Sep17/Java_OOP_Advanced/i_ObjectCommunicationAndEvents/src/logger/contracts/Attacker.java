package logger.contracts;

public interface Attacker extends Observer {

    void attack();

    void setTarget(ObservableTarget target);
}

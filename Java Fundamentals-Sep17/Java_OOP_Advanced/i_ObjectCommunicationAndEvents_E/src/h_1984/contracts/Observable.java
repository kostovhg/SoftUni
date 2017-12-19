package h_1984.contracts;

import java.lang.reflect.InvocationTargetException;

public interface Observable extends ConspiracyObjects {

    int getState();

    void setState(String property, String newValue) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException;

    void attach(Observer observer);

    void notifyAllObservers(String property, String oldValue, String newValue);
}

package app.contracts;

import java.lang.reflect.InvocationTargetException;

public interface TargetableFactory {
    Targetable create(String name, String className) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException;
}

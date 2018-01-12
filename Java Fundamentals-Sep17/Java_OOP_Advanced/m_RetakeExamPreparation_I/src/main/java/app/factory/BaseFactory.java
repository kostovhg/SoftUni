package app.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class BaseFactory<T> {

    private static final String CLASS_PATH = "app.models.";
    private Map<String, Class<?>> classes;

    protected BaseFactory() {
        this.classes = new HashMap<>();
    }

    private Class<?> getClass(String subClassName, String className) throws ClassNotFoundException {
        if (!this.classes.containsKey(className)) {
            Class<?> aClass = Class.forName(CLASS_PATH + subClassName + "." + className);
            this.classes.put(className, aClass);
        }
        return classes.get(className);
    }

    public T create(String subClassName, String name) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> objectClass = getClass(subClassName, name);
        Constructor constructor = objectClass.getConstructor();
        T object = (T) constructor.newInstance();
        return object;
    }
}

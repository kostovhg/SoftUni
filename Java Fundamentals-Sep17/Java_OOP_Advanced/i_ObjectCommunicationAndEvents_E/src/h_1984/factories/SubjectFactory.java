package h_1984.factories;

import h_1984.contracts.Observable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static h_1984.constants.PrimitiveClassesToWrapperClassesMapper.mapToWrapper;

public class SubjectFactory {

    public static Observable create(String inputString) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        String[] tokens = inputString.split("\\s+");
        String type = tokens[0];
        String[] args = Arrays.stream(tokens).skip(1).toArray(String[]::new);
        String className = "h_1984.models." + type;

        // Getting the class with full name class variable;
        Class<?> objectClass = null;
        try {
            objectClass = Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalArgumentException("There is no " + type + " model to be created");
        }

        Constructor<?> objectConstructor = objectClass.getDeclaredConstructors()[0];
        objectConstructor.setAccessible(true);
        Class<?>[] objectParametersTypes = objectConstructor.getParameterTypes();
        Object[] objectsArguments = new Object[objectParametersTypes.length];
        for (int i = 0; i < objectParametersTypes.length; i++) {
            Class<?> parameterType = objectParametersTypes[i].isPrimitive() ? mapToWrapper(objectParametersTypes[i]) : objectParametersTypes[i];
            Constructor<?> paramConstructor = parameterType.getConstructor(String.class);
            objectsArguments[i] = paramConstructor.newInstance(args[i]);
        }

        Observable object = (Observable) objectConstructor.newInstance(objectsArguments);

        return object;
    }
}

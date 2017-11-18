package p02_GenericArrayCreator;

import java.lang.reflect.Array;

public class ArrayCreator {

    /*
    https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
     */

    public ArrayCreator(){}

    // unchecked: weak type
    public static <T> T[] create(int length, T item) {

        Object[] objects = new Object[length];

        for (int i = 0; i < length; i++) {
            objects[i] = item;
        }
        return (T[])objects;
    }

    // checked: strong type
    public static <T> T[] create(Class<T> someClass, int length, T item) {
        T[] objects = (T[]) Array.newInstance(someClass, length);
        for (int i=0; i < length; i++) {
            objects[i] = item;
        }
        return objects;
    }
}

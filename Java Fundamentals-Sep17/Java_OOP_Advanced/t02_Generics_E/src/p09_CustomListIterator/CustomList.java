package p09_CustomListIterator;

import java.util.List;

public interface CustomList<T extends Comparable> extends Iterable<T> {

    void add(T element);
    T remove(int index);
    boolean contains(T element);
    void swap(int firstIndex, int secondIndex);
    int countGreaterThan(T element);
    T getMax();
    T getMin();
    List<T> get();
}

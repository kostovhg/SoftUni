package p08_CustomListSorter;

import java.util.List;

public interface CustomList<T extends Comparable> {

    public void add(T element);
    public T remove(int index);
    public boolean contains(T element);
    public void swap(int firstIndex, int secondIndex);
    public int countGreaterThan(T element);
    public T getMax();
    public T getMin();
    public List<T> get();
}

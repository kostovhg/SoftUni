package p07_CustomList;

import java.util.ArrayList;
import java.util.List;

public class CustomListImpl<T extends Comparable> implements CustomList<T> {

    List<T> tList;

    public List<T> get() {
        return tList;
    }

    public CustomListImpl() {
        this.tList = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        this.tList.add(element);
    }

    @Override
    public T remove(int index) {
        return this.tList.remove(index);
    }

    @Override
    public boolean contains(T element) {
        return this.tList.contains(element);
    }

    @Override
    public void swap(int firstIndex, int secondIndex) {
        T temp = this.tList.get(firstIndex);
        this.tList.set(firstIndex, this.tList.get(secondIndex));
        this.tList.set(secondIndex, temp);
    }

    @Override
    public int countGreaterThan(T element) {
        int count = 0;
        for (T el :
                this.tList) {
            if(el.compareTo(element) > 0) count++;
        }
        return count;
    }

    @Override
    public T getMax() {
        T max = this.tList.get(0);
        for (T el :
                this.tList) {
            if(el.compareTo(max) > 0) max = el;
        }
        return max;
    }

    @Override
    public T getMin() {
        T min = this.tList.get(0);
        for (T el :
                this.tList) {
            if(el.compareTo(min) < 0) min = el;
        }
        return min;
    }
}

package p09_CustomListIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;


public class CustomListImpl<T extends Comparable> implements CustomList<T>, Iterable<T> {

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

    @Override
    public Iterator<T> iterator() {

        Iterator<T> it=new Iterator<T>() {
            private int currentIndex=0;

            @Override
            public boolean hasNext() {
                return currentIndex < tList.size() && tList.get(currentIndex) != null;
            }

            @Override
            public T next() {
                return tList.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}


package p09_CollectionHierarchy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyListImpl<T> implements MyList<T> {

    private List<T> theList;

    public MyListImpl() {
        this.theList = new ArrayList<>(100);
    }

    @Override
    public int add(T element) {
        this.theList.add(0, element);
        return 0;
    }

    @Override
    public T remove() {
        return this.theList.remove(0);
    }

    @Override
    public int used() {
        return this.theList.size();
    }
}

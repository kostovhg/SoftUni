package p09_CollectionHierarchy;

import java.util.*;

public class AddRemoveCollectionImpl<T> implements AddRemoveCollection<T> {

    private List<T> theList;

    public AddRemoveCollectionImpl() {
        this.theList = new ArrayList<>(100);
    }

    @Override
    public int add(T element) {
        this.theList.add(0, element);
        return 0;
    }

    @Override
    public T remove() {
        return this.theList.remove(this.theList.size() - 1);
    }
}

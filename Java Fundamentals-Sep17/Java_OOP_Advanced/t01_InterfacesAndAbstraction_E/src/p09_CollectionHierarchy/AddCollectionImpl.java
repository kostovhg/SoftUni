package p09_CollectionHierarchy;

import java.util.*;

public class AddCollectionImpl<T> implements AddCollection <T> {

    private List<T> theList;

    public AddCollectionImpl() {
        this.theList = new ArrayList<>(100);
    }

    @Override
    public int add(T element) {
        this.theList.add(element);
        return this.theList.size() - 1;
    }
}

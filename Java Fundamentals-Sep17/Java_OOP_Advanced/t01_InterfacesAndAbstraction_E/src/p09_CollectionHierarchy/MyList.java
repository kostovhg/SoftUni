package p09_CollectionHierarchy;

import java.util.Map;

public interface MyList<T> extends AddRemoveCollection<T>{

    int add(T element);

    T remove();

    int used();
}

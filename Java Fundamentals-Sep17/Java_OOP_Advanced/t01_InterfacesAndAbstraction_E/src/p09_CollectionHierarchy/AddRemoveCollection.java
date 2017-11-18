package p09_CollectionHierarchy;

public interface AddRemoveCollection<T> extends AddCollection<T>{

    int add(T element);

    T remove();
}

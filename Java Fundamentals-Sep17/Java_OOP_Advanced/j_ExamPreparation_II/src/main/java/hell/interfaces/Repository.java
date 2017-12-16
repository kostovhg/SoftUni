package hell.interfaces;

import java.util.List;

public interface Repository<T> {

    void add(T element);

    List<T> findAll();

    T findByName(String name);
}

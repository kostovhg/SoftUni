package regapp.repository;

import java.util.List;

public interface GenericRepository<E, ID> {

    E save(E entity);

    List<E> findAll();

    E findById(ID id);

    void remove(ID id);
}

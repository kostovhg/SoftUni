package casebook.repository;

import java.util.List;

public interface GenericRepository<E, ID> {

    E save(E entity);

    E update(E entity);

    List<E> findAll();

    E findById(ID id);
}

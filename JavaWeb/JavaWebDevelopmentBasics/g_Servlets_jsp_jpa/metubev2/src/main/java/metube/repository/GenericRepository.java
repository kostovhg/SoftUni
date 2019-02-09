package metube.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <E, Id> {

    E save (E entity);

    List<E> findAll();

    Optional<E> findById(Id id);

    Optional<E> findByName(String name);

    long size();
}

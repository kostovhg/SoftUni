package n_InfernoInfinityRefactoring.repositories;

import n_InfernoInfinityRefactoring.models.api.WeaponInterface;

import java.util.Map;

public interface Repository<T> {

    void add(T element);

    Map<String, WeaponInterface> findAll();
}

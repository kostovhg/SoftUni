package metube.repository;

import metube.domain.entities.Tube;

import java.util.Optional;

public interface TubeRepository extends GenericRepository<Tube, String> {

    Optional<Tube> getByName(String name);

}

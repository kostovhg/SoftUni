package app.retake.repositories;

import app.retake.domain.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {

}

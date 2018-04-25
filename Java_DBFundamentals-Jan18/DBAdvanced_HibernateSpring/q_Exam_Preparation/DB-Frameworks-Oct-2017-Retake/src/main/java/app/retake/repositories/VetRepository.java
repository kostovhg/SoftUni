package app.retake.repositories;

import app.retake.domain.models.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {
    Vet findByName(String name);
}

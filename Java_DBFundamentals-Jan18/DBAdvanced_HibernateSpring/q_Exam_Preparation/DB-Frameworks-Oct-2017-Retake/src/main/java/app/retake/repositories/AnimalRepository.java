package app.retake.repositories;

import app.retake.domain.dto.PassportJSONImportDTO;
import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Animal findByPassportSerialNumber(String passport);
}

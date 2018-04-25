package app.retake.repositories;

import app.retake.domain.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, String> {
}

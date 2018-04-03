package app.repositories;

import app.model.labels.BasicLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicLabelRepository extends JpaRepository<BasicLabel, Long> {

    BasicLabel findById(Long id);
}

package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {

    @Query("SELECT MAX(re.id) FROM RaceEntry AS re")
    Integer findLastId();

    RaceEntry findById(Integer id);
}

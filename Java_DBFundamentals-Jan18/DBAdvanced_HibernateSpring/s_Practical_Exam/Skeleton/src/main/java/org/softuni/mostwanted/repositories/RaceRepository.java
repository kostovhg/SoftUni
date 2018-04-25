package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Integer> {

    @Query("SELECT MAX(r.id) FROM Race AS r")
    Integer findByMaxId();



}

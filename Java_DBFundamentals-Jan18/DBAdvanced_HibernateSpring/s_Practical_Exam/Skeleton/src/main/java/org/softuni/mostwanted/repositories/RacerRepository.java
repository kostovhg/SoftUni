package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    boolean existsByName(String name);

    Racer findByName(String name);

}

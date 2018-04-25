package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Town getOneByName(String name);

    /*
    @Query(value = "SELECT t.name AS 'name', COUNT(r.id) as 'racers' " +
            "FROM most_wanted_db.towns AS t " +
            "JOIN most_wanted_db.racers AS r ON r.id = t.id " +
            "GROUP BY t.name " +
            "ORDER BY `racers` DESC, t.name ASC", nativeQuery = true)
    List<TownByRacersJSONExportDto> getAllByRacersCount();
    */



}

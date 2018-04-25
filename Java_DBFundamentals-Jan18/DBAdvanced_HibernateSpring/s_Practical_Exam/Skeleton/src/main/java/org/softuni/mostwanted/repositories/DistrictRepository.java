package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {

    boolean existsByName(String name);

    District findByName(String districtName);
}
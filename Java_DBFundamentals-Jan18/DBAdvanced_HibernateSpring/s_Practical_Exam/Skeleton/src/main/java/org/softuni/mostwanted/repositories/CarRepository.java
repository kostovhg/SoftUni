package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findById(Integer integer);
}
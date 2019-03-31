package com.softuni.javascriptajax.repositories;

import com.softuni.javascriptajax.domain.entities.Virus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VirusRepository extends JpaRepository<Virus, Integer> {
}

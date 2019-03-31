package com.softuni.javascriptajax.repositories;

import com.softuni.javascriptajax.domain.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapitalRepository extends JpaRepository<Capital, Integer> {
}

package com.softuni.exodia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
interface MultyRepository<Entity, ID extends Serializable> extends JpaRepository<Entity, ID> {

    Optional<Entity> findById(ID id);
}

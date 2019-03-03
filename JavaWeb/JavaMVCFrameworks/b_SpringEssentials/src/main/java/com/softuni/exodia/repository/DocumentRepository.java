package com.softuni.exodia.repository;

import com.softuni.exodia.domain.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, String> {

    Optional<Document> findByTitle(String value);
}

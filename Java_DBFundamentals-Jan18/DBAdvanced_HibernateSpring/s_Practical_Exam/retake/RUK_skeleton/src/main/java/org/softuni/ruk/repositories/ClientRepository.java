package org.softuni.ruk.repositories;

import org.softuni.ruk.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByFullName(String fullName);
}

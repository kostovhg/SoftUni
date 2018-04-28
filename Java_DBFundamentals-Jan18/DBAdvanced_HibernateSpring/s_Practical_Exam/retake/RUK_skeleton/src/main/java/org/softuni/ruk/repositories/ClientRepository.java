package org.softuni.ruk.repositories;

import org.softuni.ruk.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByFullName(String fullName);

    @Query(value =
            "SELECT c.id, c.full_name, ba.account_number, ba.balance, COUNT(ca.id) " +
                    "FROM ruk_db.clients AS c " +
                    " JOIN ruk_db.bank_accounts AS ba ON c.bank_account_id = ba.id " +
                    " JOIN ruk_db.cards AS ca ON ca.bank_account = ba.id " +
                    " GROUP BY c.id " +
                    " HAVING COUNT(ca.id) > 0 " +
                    " ORDER BY COUNT(ca.id) DESC, c.id ASC " +
                    "LIMIT 1",
            nativeQuery = true)
    Client findClientWithMostCardsAndLowestId();
}

package org.softuni.ruk.repositories;

import org.softuni.ruk.model.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    BankAccount findByAccountNumber(String accountNumber);
}

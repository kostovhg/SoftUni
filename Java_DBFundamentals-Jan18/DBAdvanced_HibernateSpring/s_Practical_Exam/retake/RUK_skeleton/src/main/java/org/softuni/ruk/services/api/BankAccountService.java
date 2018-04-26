package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.BankAccountXMLImportDTO;
import org.softuni.ruk.model.entities.BankAccount;

public interface BankAccountService {
    boolean create(BankAccountXMLImportDTO bankAccountDto);

    BankAccount findByAccountNumber(String accountNumber);
}

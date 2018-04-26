package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.BankAccountXMLImportDTO;
import org.softuni.ruk.model.entities.BankAccount;
import org.softuni.ruk.model.entities.Client;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.repositories.BankAccountRepository;
import org.softuni.ruk.services.api.BankAccountService;
import org.softuni.ruk.services.api.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private ClientService clientService;


    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientService clientService) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientService = clientService;
    }

    @Override
    public boolean create(BankAccountXMLImportDTO bankAccountDto) {
        Client client = this.clientService.findByFullName(bankAccountDto.getClientNames());
        if(client == null ){
            return false;
        }  else {
            try {
                BankAccount ba = new BankAccount();
                ba.setAccountNumber(bankAccountDto.getAccountNumber());
                ba.setBalance(bankAccountDto.getBalance());
                ba.setClient(client);
                client.setBankAccount(ba);
                this.bankAccountRepository.saveAndFlush(ba);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {
        return this.bankAccountRepository.findByAccountNumber(accountNumber);
    }
}
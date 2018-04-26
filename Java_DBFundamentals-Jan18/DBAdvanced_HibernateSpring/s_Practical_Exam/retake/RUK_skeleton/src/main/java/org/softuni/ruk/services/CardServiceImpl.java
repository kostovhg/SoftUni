package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.CardXMLImportDTO;
import org.softuni.ruk.model.entities.BankAccount;
import org.softuni.ruk.model.entities.Card;
import org.softuni.ruk.repositories.CardRepository;
import org.softuni.ruk.services.api.BankAccountService;
import org.softuni.ruk.services.api.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    private BankAccountService bankAccountService;
    private CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(BankAccountService bankAccountService, CardRepository cardRepository) {
        this.bankAccountService = bankAccountService;
        this.cardRepository = cardRepository;
    }

    @Override
    public boolean create(CardXMLImportDTO cardDto) {
        BankAccount bankAccount = this.bankAccountService.findByAccountNumber(cardDto.getAccountNumber());
        if (bankAccount == null) {
            return false;
        } else {
            try {
                Card card = new Card();
                card.setBankAccount(bankAccount);
                card.setCardNumber(cardDto.getCardNumber());
                card.setCardStatus(cardDto.getCardStatus());
                bankAccount.getCards().add(card);
                this.cardRepository.saveAndFlush(card);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

}

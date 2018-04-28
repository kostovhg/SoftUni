package org.softuni.ruk.controllers;

import org.softuni.ruk.model.dto.BankAccountsWrapperXMLImportDTO;
import org.softuni.ruk.parser.interfaces.Parser;
import org.softuni.ruk.services.api.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static org.softuni.ruk.parser.ValidationUtil.isValid;
import static org.softuni.ruk.utils.Config.BANK_ACCOUNT_CONTROLLER;
import static org.softuni.ruk.utils.Messages.INCORRECT_DATA_ERROR;
import static org.softuni.ruk.utils.Messages.SUCCESS_FORMAT;

@Controller(value = BANK_ACCOUNT_CONTROLLER)
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final Parser xmlParser;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, @Qualifier("XMLParser") Parser xmlParser) {
        this.bankAccountService = bankAccountService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            BankAccountsWrapperXMLImportDTO xmlBankAccountsWrapper = xmlParser.read(BankAccountsWrapperXMLImportDTO.class, xmlContent);
            xmlBankAccountsWrapper.getBankAccounts().forEach(baDto -> {
                if(isValid(baDto)){
                    bankAccountService.create(baDto);
                    sb.append(String.format(SUCCESS_FORMAT, "BankAccount", baDto.getAccountNumber()));
                } else {
                    sb.append(INCORRECT_DATA_ERROR);
                }
                sb.append(System.lineSeparator());
            });
            return sb.toString().trim();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String exportDataToXML() {
        try {
            this.xmlParser.write(this.bankAccountService.findAccountWithMostCards());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
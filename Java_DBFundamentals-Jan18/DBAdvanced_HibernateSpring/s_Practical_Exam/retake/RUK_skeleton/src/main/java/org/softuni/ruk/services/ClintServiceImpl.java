package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.ClientJSONImportDTO;
import org.softuni.ruk.model.dto.exportXml.BankAccountXMLExportDto;
import org.softuni.ruk.model.dto.exportXml.CardXMLExportDto;
import org.softuni.ruk.model.dto.exportXml.ClientXMLExportDto;
import org.softuni.ruk.model.entities.BankAccount;
import org.softuni.ruk.model.entities.Client;
import org.softuni.ruk.model.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.services.api.ClientService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class ClintServiceImpl implements ClientService {

    private EmployeeService employeeService;
    private ClientRepository clientRepository;
    private ModelParser modelParser;

    @Autowired
    public ClintServiceImpl(EmployeeService employeeService, ClientRepository clientRepository, ModelParser modelParser) {
        this.employeeService = employeeService;
        this.clientRepository = clientRepository;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(ClientJSONImportDTO clientDto) {
        try {
            Client client = this.clientRepository.findByFullName(clientDto.getFullName());
            Employee newEmployee = this.employeeService.findByName(clientDto.getAppointedEmployee());
            if(client == null){
                client = new Client();
                client.setAge(clientDto.getAge());
                client.setFullName(clientDto.getFullName());
            }

            client.changeAssignedEmployee(newEmployee);
            this.clientRepository.saveAndFlush(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Client findByFullName(String clientNames) {
        return this.clientRepository.findByFullName(clientNames);
    }

    @Override
    public ClientXMLExportDto findClientWithMostCardsAndLowestId() {
        Client client = this.clientRepository.findClientWithMostCardsAndLowestId();
        BankAccount clientBankAccount = client.getBankAccount();
        BankAccountXMLExportDto baDto = new BankAccountXMLExportDto();
        baDto.setAccountNumber(clientBankAccount.getAccountNumber());
        baDto.setCards(clientBankAccount.getCards().stream().map(c -> this.modelParser.convert(c, CardXMLExportDto.class)).collect(Collectors.toList()));
        ClientXMLExportDto clientDto = new ClientXMLExportDto();
        clientDto.setFullName(client.getFullName());
        clientDto.setAge(String.valueOf(client.getAge()));
        clientDto.setBankAccount(baDto);

        return clientDto;
    }
}

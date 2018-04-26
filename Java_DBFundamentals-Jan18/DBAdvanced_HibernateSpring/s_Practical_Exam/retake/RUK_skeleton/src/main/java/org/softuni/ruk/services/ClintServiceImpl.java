package org.softuni.ruk.services;

import org.softuni.ruk.model.dto.ClientJSONImportDTO;
import org.softuni.ruk.model.dto.EmployeeJSONImportDTO;
import org.softuni.ruk.model.entities.Client;
import org.softuni.ruk.model.entities.Employee;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.repositories.ClientRepository;
import org.softuni.ruk.services.api.ClientService;
import org.softuni.ruk.services.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

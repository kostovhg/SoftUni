package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.ClientJSONImportDTO;
import org.softuni.ruk.model.entities.Client;

public interface ClientService {
    boolean create(ClientJSONImportDTO clientDto);

    Client findByFullName(String clientNames);
}

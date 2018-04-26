package org.softuni.ruk.services.api;

import org.softuni.ruk.model.dto.CardXMLImportDTO;

public interface CardService {
    boolean create(CardXMLImportDTO cardDto);
}

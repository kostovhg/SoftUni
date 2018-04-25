package app.retake.services.api;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;

public interface VetService {
    void create(VetXMLImportDTO dto);

    Vet getByName(String name);
}

package app.retake.services.api;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;

public interface AnimalAidService {
    void create(AnimalAidJSONImportDTO dto);

    AnimalAid getOneByName(String name);
}

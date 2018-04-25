package app.retake.services.api;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface AnimalService {

    void create(AnimalJSONImportDTO dto);

    List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber);

    Animal findByPassportSerialNumber(String number);
}

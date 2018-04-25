package app.retake.services.impl;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final VetService vetService;
    private final AnimalService animalService;
    private final AnimalAidService animalAidService;
    private final ModelParser modelParser;

    public ProcedureServiceImpl(
            ProcedureRepository procedureRepository,
            VetService vetService,
            AnimalService animalService,
            AnimalAidService animalAidService, ModelParser modelParser) {
        this.procedureRepository = procedureRepository;
        this.vetService = vetService;
        this.animalService = animalService;
        this.animalAidService = animalAidService;
        this.modelParser = modelParser;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {

        Animal animal = this.animalService.findByPassportSerialNumber(dto.getAnimal());
        Vet vet = this.vetService.getByName(dto.getVet());

        if (animal != null && vet != null) {
            Set<AnimalAid> animalAids = dto.getAnimalAids().stream()
                    .filter(animalAid -> this.animalAidService.getOneByName(animalAid.getName()) != null)
                    .map(animalAidDto -> this.animalAidService.getOneByName(animalAidDto.getName()))
                    .collect(Collectors.toSet());

            Procedure procedure = new Procedure();
            procedure.setAnimal(animal);
            procedure.setVet(vet);
            procedure.setDatePerformed(dto.getDate());
            procedure.setServices(animalAids);
            this.procedureRepository.saveAndFlush(procedure);
        } else  {
           //
        }
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        return null;
    }
}


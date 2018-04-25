package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.dto.PassportJSONImportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final ModelParser modelParser;

    @Autowired
    public AnimalServiceImpl(
            AnimalRepository animalRepository,
            PassportRepository passportRepository,
            ModelParser modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) {
        Passport passport = this.modelParser.convert(dto.getPassport(), Passport.class);
        Animal animal = this.modelParser.convert(dto, Animal.class);
        // Just ignore animals with same passport serial number
        if(passportRepository.findOne(passport.getSerialNumber()) == null){
            animal.setPassport(passport);
        } else {
            // or try to refresh the dog
            animal = animalRepository.findByPassportSerialNumber(dto.getPassport().getSerialNumber());
            animal.setName(dto.getName());
            animal.setAge(dto.getAge());
            animal.setType(dto.getType());
        }
        this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public Animal findByPassportSerialNumber(String number){
        return this.animalRepository.findByPassportSerialNumber(number);
    }
}

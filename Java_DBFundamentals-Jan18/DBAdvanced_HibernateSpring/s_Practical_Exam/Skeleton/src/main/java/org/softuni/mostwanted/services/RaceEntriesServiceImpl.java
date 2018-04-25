package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.entries.RaceEntriesXMLImportDto;
import org.softuni.mostwanted.entity.RaceEntry;
import org.softuni.mostwanted.entity.Racer;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.services.api.CarsService;
import org.softuni.mostwanted.services.api.RaceEntriesService;
import org.softuni.mostwanted.services.api.RacersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RaceEntriesServiceImpl implements RaceEntriesService {

    private final RaceEntryRepository raceEntryRepository;
    private final RacersService racersService;
    private final CarsService carsService;

    @Autowired
    public RaceEntriesServiceImpl(RaceEntryRepository raceEntryRepository, RacersService racersService, CarsService carsService) {
        this.raceEntryRepository = raceEntryRepository;
        this.racersService = racersService;
        this.carsService = carsService;
    }

    @Override
    public Integer getLastId() {
        return this.raceEntryRepository.findLastId();
    }

    @Override
    public boolean create(RaceEntriesXMLImportDto raceEntryDto) {
        try {
            RaceEntry raceEntry = new RaceEntry();
            Racer racer = racersService.findOneByName(raceEntryDto.getRacerName());
            raceEntry.setHasFinished(Boolean.valueOf(raceEntryDto.getHasFinished()));
            raceEntry.setFinishTime(Double.valueOf(raceEntryDto.getFinishTime()));
            raceEntry.setRacer(racer);
            raceEntry.setCar(carsService.findById(Integer.valueOf(raceEntryDto.getCarId())));
            racer.getEntries().add(raceEntry);
            if (!this.raceEntryRepository.findAll().contains(raceEntry)){
                this.raceEntryRepository.saveAndFlush(raceEntry);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public RaceEntry findById(Integer id) {
        return this.raceEntryRepository.findById(id);
    }
}

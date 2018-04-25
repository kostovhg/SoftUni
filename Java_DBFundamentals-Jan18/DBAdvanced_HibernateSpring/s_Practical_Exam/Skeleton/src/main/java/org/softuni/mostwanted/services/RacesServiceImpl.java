package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.entries.RaceEntryByIdDto;
import org.softuni.mostwanted.domain.binding.races.RaceXMLImportDto;
import org.softuni.mostwanted.entity.Race;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.DistrictsService;
import org.softuni.mostwanted.services.api.RaceEntriesService;
import org.softuni.mostwanted.services.api.RacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RacesServiceImpl implements RacesService {

    private final RaceRepository raceRepository;
    private final DistrictsService districtsService;
    private final RaceEntriesService raceEntriesService;

    @Autowired
    public RacesServiceImpl(RaceRepository raceRepository, DistrictsService districtsService, RaceEntriesService raceEntriesService) {
        this.raceRepository = raceRepository;
        this.districtsService = districtsService;
        this.raceEntriesService = raceEntriesService;
    }

    @Override
    public Integer getLastId() {
        return this.raceRepository.findByMaxId();
    }

    @Override
    public boolean create(RaceXMLImportDto raceDto) {
    /*
        PropertyMap<RaceXMLImportDto, Race> raceMap = null;
        if (this.modelParser.getMapper().getTypeMap(RaceXMLImportDto.class, Race.class) == null) {
            raceMap = new PropertyMap<RaceXMLImportDto, Race>() {
                @Override
                protected void configure() {
                    map(source.getLaps()).setLaps(0);
                    map(source.getDistrictName()).setDistrict(null);
                }
            };
            this.modelParser.getMapper().addMappings(raceMap);
        }*/

        try {
            Race race = new Race();
            race.setDistrict(districtsService.findByName(raceDto.getDistrictName()));
            race.setLaps(raceDto.getLaps());
            for (RaceEntryByIdDto raceEntryByIdDto : raceDto.getRaceEntries().getEntries()) {
                race.getEntries().add(raceEntriesService.findById(Integer.valueOf(raceEntryByIdDto.getEntryId())));
            }
            if (!this.raceRepository.findAll().contains(race)) {
                this.raceRepository.saveAndFlush(race);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

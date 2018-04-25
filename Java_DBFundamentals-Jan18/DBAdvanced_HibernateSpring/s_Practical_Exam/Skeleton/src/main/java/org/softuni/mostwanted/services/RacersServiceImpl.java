package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.racers.RacersJSONImportDto;
import org.softuni.mostwanted.domain.view.RacerEntryXMLExportDto;
import org.softuni.mostwanted.domain.view.RacerMostWantedWrapperXMLExportDto;
import org.softuni.mostwanted.domain.view.RacerXMLExportDto;
import org.softuni.mostwanted.domain.view.RacersWithCarsJSONExportDto;
import org.softuni.mostwanted.entity.RaceEntry;
import org.softuni.mostwanted.entity.Racer;
import org.softuni.mostwanted.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RacersService;
import org.softuni.mostwanted.services.api.TownsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacersServiceImpl implements RacersService {

    private final RacerRepository racerRepository;
    private final TownsService townsService;
    private final ModelParser modelParser;

    @Autowired
    public RacersServiceImpl(
            RacerRepository racerRepository,
            TownsService townsService,
            ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townsService = townsService;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(RacersJSONImportDto racerDto) {

        try {
            if (!this.racerRepository.existsByName(racerDto.getName())) {
                Racer racer = this.modelParser.convert(racerDto, Racer.class);
                Town town = this.townsService.getByName(racerDto.getHomeTown());
                racer.setHomeTown(town);
                town.getRacers().add(racer);
                this.racerRepository.saveAndFlush(racer);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Racer findOneByName(String racerName) {
        return this.racerRepository.findByName(racerName);
    }

    @Override
    public List<RacersWithCarsJSONExportDto> getRacersWithCar() {
        return this.racerRepository.findAll().stream()
                .map(r -> {
                    RacersWithCarsJSONExportDto rDto = new RacersWithCarsJSONExportDto();
                    rDto.setRacerName(r.getName());
                    rDto.setAge(r.getAge());
                    rDto.getCars().addAll(r.getCars().stream()
                            .map(c -> String.format("%s %s %d",
                                    c.getBrand(),
                                    c.getModel(),
                                    c.getYearOfProduction()))
                            .collect(Collectors.toList()));
                    return rDto;
                })
                .sorted().collect(Collectors.toList());
    }

    @Override
    public RacerMostWantedWrapperXMLExportDto getMostWanted() {

        Racer racer = this.racerRepository.findAll().stream()
                .sorted((r1, r2) -> Long.compare(r2.getEntries().size(), r1.getEntries().size()))
                .findFirst().get();

        RacerMostWantedWrapperXMLExportDto racerDto = new RacerMostWantedWrapperXMLExportDto();
        List<RacerEntryXMLExportDto> entriesList = racer.getEntries().stream()
                .sorted(Comparator.comparingDouble(RaceEntry::getFinishTime))
                .map(e ->
                    new RacerEntryXMLExportDto(e.getFinishTime().toString(),
                            String.format("%s %s @ %d",
                                    e.getCar().getBrand(),
                                    e.getCar().getModel(),
                                    e.getCar().getYearOfProduction()))
                )
                .collect(Collectors.toList());
        RacerXMLExportDto racerExportDto = new RacerXMLExportDto();
        racerExportDto.getEntries().addAll(entriesList);
        racerExportDto.setRacerName(racer.getName());
        racerDto.setRacer(racerExportDto);

        return racerDto;
    }
}

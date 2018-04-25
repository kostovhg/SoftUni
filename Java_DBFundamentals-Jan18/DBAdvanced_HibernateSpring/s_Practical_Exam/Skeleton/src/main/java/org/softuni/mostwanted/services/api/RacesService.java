package org.softuni.mostwanted.services.api;


import org.softuni.mostwanted.domain.binding.races.RaceXMLImportDto;

public interface RacesService {
    Integer getLastId();

    boolean create(RaceXMLImportDto raceDto);
}

package org.softuni.mostwanted.services.api;


import org.softuni.mostwanted.domain.binding.entries.RaceEntriesXMLImportDto;
import org.softuni.mostwanted.entity.RaceEntry;

public interface RaceEntriesService {
    Integer getLastId();

    boolean create(RaceEntriesXMLImportDto raceEntryDto);

    RaceEntry findById(Integer id);

}

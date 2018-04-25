package org.softuni.mostwanted.services.api;


import org.softuni.mostwanted.domain.binding.towns.TownJSONImportDto;
import org.softuni.mostwanted.domain.view.TownByRacersJSONExportDto;
import org.softuni.mostwanted.entity.Town;

import java.util.List;

public interface TownsService {
    boolean create(TownJSONImportDto townDto);

    Town getByName(String name);

    List<TownByRacersJSONExportDto> getAllByRacersCount();
}

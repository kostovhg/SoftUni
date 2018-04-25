package org.softuni.mostwanted.services.api;


import org.softuni.mostwanted.domain.binding.racers.RacersJSONImportDto;
import org.softuni.mostwanted.domain.view.RacerMostWantedWrapperXMLExportDto;
import org.softuni.mostwanted.domain.view.RacersWithCarsJSONExportDto;
import org.softuni.mostwanted.entity.Racer;

import java.util.List;

public interface RacersService {

    boolean create(RacersJSONImportDto racerDto);

    Racer findOneByName(String racerName);

    List<RacersWithCarsJSONExportDto> getRacersWithCar();

    RacerMostWantedWrapperXMLExportDto getMostWanted();
}

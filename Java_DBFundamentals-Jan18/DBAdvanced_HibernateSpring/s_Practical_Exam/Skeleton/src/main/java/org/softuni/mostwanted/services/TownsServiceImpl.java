package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.towns.TownJSONImportDto;
import org.softuni.mostwanted.domain.view.TownByRacersJSONExportDto;
import org.softuni.mostwanted.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TownsServiceImpl implements TownsService {

    private final TownRepository townRepository;
    private final ModelParser modelParser;

    @Autowired
    public TownsServiceImpl(TownRepository townRepository, ModelParser modelParser) {
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public boolean create(TownJSONImportDto townDto) {
        Town aTown = this.townRepository.getOneByName(townDto.getName());
        try {
            if (aTown == null) {
                this.townRepository.saveAndFlush(this.modelParser.convert(townDto, Town.class));
                return true;
            }
            return false;
        } catch (Exception e) {
            // log here;
            return false;
        }
    }

    @Override
    public Town getByName(String name) {
        return this.townRepository.getOneByName(name);
    }

    @Override
    public List<TownByRacersJSONExportDto> getAllByRacersCount(){
        return this.townRepository.findAll().stream()
                .filter(t -> t.getRacers().size() > 0)
                .map(t -> new TownByRacersJSONExportDto(t.getName(), t.getRacers().size()))
                .sorted((t1, t2) -> {
                  if(t1.getRacersCount().compareTo(t2.getRacersCount()) == 0) {
                      return t1.getName().compareTo(t2.getName());
                  } else {
                      return t2.getRacersCount().compareTo(t1.getRacersCount());
                  }
                })
                .collect(Collectors.toList());

    }
}

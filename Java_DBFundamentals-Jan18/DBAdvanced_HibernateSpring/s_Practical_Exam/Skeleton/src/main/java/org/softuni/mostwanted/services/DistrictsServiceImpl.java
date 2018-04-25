package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.binding.districts.DistrictJSONImportDto;
import org.softuni.mostwanted.entity.District;
import org.softuni.mostwanted.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.services.api.DistrictsService;
import org.softuni.mostwanted.services.api.TownsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictsServiceImpl implements DistrictsService {

    private DistrictRepository districtRepository;
    private TownsService townsService;
    private ModelParser modelParser;

    @Autowired
    public DistrictsServiceImpl(DistrictRepository districtRepository, TownsService townsService, ModelParser modelParser) {
        this.districtRepository = districtRepository;
        this.townsService = townsService;
        this.modelParser = modelParser;
    }


    @Override
    public boolean create(DistrictJSONImportDto districtDto) {
        try {
            if (!this.districtRepository
                    .existsByName(districtDto.getName())) {
                District district = this.modelParser.convert(districtDto, District.class);
                Town town = this.townsService.getByName(districtDto.getTownName());
                district.setTown(town);
                town.getDistricts().add(district);
                this.districtRepository.saveAndFlush(district);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            // log here;
            return false;
        }
    }

    @Override
    public District findByName(String districtName) {
        return this.districtRepository.findByName(districtName);
    }
}

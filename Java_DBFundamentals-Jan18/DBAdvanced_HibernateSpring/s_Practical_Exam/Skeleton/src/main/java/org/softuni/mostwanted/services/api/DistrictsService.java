package org.softuni.mostwanted.services.api;


import org.softuni.mostwanted.domain.binding.districts.DistrictJSONImportDto;
import org.softuni.mostwanted.entity.District;

public interface DistrictsService {
    boolean create(DistrictJSONImportDto districtDto);

    District findByName(String districtName);
}

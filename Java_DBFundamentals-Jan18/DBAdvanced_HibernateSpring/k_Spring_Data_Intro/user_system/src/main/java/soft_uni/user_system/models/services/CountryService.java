package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.townEntity.Country;

import java.util.List;

public interface CountryService {

    void saveCountryToDatabase(Country country);

    void saveCountryToDatabase(List<Country> country);

    Country getByName(String name);
}

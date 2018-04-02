package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import soft_uni.user_system.models.entities.townEntity.Country;
import soft_uni.user_system.models.repositories.CountryRepository;
import soft_uni.user_system.models.services.CountryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public CountryServiceImpl(CountryRepository userRepository) {
        this.countryRepository = userRepository;
    }

    @Override
    public void saveCountryToDatabase(Country country){
        this.countryRepository.saveAndFlush(country);
    }

    @Override
    public void saveCountryToDatabase(List<Country> country){
        this.countryRepository.saveAll(country);
    }

    @Override
    public Country getByName(String name){
        return this.countryRepository.findByName(name);
    }
}

package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import soft_uni.user_system.models.entities.townEntity.Town;
import soft_uni.user_system.models.repositories.TownRepository;
import soft_uni.user_system.models.services.TownService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void saveTownToDatabase(Town town){
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public void saveTownToDatabase(List<Town> town){
        this.townRepository.saveAll(town);
    }

    @Override
    public Town getByTownName(String townName){
        return this.townRepository.findByName(townName);
    }
}

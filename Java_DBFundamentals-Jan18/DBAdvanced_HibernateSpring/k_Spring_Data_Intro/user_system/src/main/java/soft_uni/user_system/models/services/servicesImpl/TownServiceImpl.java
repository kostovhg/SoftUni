package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.user_system.models.entities.townEntity.Town;
import soft_uni.user_system.models.repositories.TownRepository;
import soft_uni.user_system.models.services.TownService;

import javax.transaction.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void saveTownToDatabase(Town town){
        this.townRepository.save(town);
    }
}

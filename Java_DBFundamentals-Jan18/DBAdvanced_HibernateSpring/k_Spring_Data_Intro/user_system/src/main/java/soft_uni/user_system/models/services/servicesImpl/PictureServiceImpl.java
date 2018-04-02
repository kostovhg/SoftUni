package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import soft_uni.user_system.models.entities.albumEntity.Picture;
import soft_uni.user_system.models.repositories.PictureRepository;
import soft_uni.user_system.models.services.PictureService;

import javax.transaction.Transactional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void savePictureToDatabase(Picture pucture) {
        this.pictureRepository.save(pucture);
    }


}

package soft_uni.user_system.models.services.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.albumEntity.Album;
import soft_uni.user_system.models.repositories.AlbumRepository;
import soft_uni.user_system.models.services.AlbumService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    private Validator validator;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void saveAlbumToDatabase(Album album) {
        this.albumRepository.save(album);
    }

    @Override
    public List<Album> getAllByOwner(User owner){
        return this.albumRepository.getAllByOwner(owner);
    }
}

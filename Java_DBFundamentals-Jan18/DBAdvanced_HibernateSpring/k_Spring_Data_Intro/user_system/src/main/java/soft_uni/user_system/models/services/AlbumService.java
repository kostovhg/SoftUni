package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.User;
import soft_uni.user_system.models.entities.albumEntity.Album;

import java.util.List;

public interface AlbumService {

    void saveAlbumToDatabase(Album picture);

    List<Album> getAllByOwner(User owner);
}

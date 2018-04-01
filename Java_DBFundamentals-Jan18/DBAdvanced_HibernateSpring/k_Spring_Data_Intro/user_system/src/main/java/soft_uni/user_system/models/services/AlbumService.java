package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.albumEntity.Album;

public interface AlbumService {

    void saveAlbumToDatabase(Album picture);
}

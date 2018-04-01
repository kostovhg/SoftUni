package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.albumEntity.Picture;

public interface PictureService {

    void savePictureToDatabase(Picture picture);
}

package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.townEntity.Town;

public interface TownService {

    void saveTownToDatabase(Town town);
}

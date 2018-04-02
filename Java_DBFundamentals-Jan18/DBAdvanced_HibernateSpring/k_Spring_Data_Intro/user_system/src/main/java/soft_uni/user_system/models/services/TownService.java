package soft_uni.user_system.models.services;

import soft_uni.user_system.models.entities.townEntity.Town;

import java.util.List;

public interface TownService {

    void saveTownToDatabase(Town town);

    void saveTownToDatabase(List<Town> town);

    Town getByTownName(String townName);
}

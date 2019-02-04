package metube.service;

import metube.domain.models.services.TubeServiceModel;

import java.util.List;

public interface TubeService {


    void saveTube(TubeServiceModel tubeServiceModel);

    List<TubeServiceModel> getAllTubes();

    TubeServiceModel findTubeByName(String name);
}

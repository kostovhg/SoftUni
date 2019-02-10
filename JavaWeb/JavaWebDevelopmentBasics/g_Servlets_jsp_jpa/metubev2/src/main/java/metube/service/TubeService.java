package metube.service;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;

public interface TubeService {
    boolean upload(TubeServiceModel userServiceModel);

    TubeDetailsViewModel details(String tubeName);
}

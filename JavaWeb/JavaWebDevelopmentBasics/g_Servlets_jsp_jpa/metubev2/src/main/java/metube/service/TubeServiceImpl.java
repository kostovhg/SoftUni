package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.repository.TubeRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository userRepository, ModelMapper modelMapper) {
        this.tubeRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean upload(TubeServiceModel userServiceModel) {
        Tube tube = this.modelMapper.map(userServiceModel, Tube.class);

        try {
            this.tubeRepository.save(tube);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public TubeDetailsViewModel details(String tubeName) {

        Tube tube = this.tubeRepository.findByName(tubeName).orElse(null);

        if (tube == null){
            return null;
        }
        return modelMapper.map(tube, TubeDetailsViewModel.class);
    }
}

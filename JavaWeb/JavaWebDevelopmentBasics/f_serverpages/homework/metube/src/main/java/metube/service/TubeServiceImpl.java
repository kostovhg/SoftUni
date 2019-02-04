package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.services.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.utils.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel){
        this.tubeRepository.save(this.modelMapper.map(tubeServiceModel, Tube.class));
    }

    @Override
    public List<TubeServiceModel> getAllTubes(){
        return this.tubeRepository.findAll()
                .stream()
                .map(t ->
                        this.modelMapper
                                .map(t, TubeServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public TubeServiceModel findTubeByName(String name) {
        Tube tube = this.tubeRepository.getByName(name).orElse(null);

        if (tube != null){
            return this.modelMapper.map(tube, TubeServiceModel.class);
        } else {
            // TODO: intercept this empty object!!!
            return new TubeServiceModel();
        }
    }
}

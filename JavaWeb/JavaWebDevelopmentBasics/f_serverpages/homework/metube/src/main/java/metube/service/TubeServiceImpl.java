package metube.service;

import metube.domain.entities.Tube;
import metube.domain.models.services.TubeServiceModel;
import metube.repository.TubeRepository;
import metube.utils.ModelMapper;
import metube.utils.ValidationUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {

    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel){
        if(!this.validationUtil.isValid(tubeServiceModel)){
            throw new IllegalArgumentException();
        }
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

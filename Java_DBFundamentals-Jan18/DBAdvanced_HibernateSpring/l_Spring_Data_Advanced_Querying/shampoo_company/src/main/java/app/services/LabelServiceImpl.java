package app.services;

import app.model.labels.BasicLabel;
import app.repositories.BasicLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    private final BasicLabelRepository basicLabelRepository;

    @Autowired
    public LabelServiceImpl(BasicLabelRepository basicLabelRepository) {
        this.basicLabelRepository = basicLabelRepository;
    }

    @Override
    public BasicLabel findById(Long aLong) {
        return this.basicLabelRepository.findOne(aLong);
    }
}

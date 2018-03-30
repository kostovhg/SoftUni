package app.servicesImpl;

import app.model.Homework;
import app.repository.HomeworkRepository;
import app.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService<Homework, Long> {

    private HomeworkRepository homeworkRepository;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @Override
    public Homework findById(Long id) {
        return this.homeworkRepository.getOne(id);
    }

    @Override
    public void remove(Homework homework) {
        this.homeworkRepository.delete(homework);
    }

    @Override
    public List<Homework> findAll() {
        return this.homeworkRepository.findAll();
    }

    @Override
    public void save(Homework homework) {
        this.homeworkRepository.save(homework);
    }
}

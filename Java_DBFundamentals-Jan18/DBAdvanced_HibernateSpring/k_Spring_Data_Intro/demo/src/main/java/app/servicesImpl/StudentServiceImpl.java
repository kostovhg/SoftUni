package app.servicesImpl;

import app.model.Student;
import app.repository.StudentRepository;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService<Student, Long> {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long id) {
        return this.studentRepository.getOne(id);
    }

    @Override
    public void remove(Student student) {
        this.studentRepository.delete(student);
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

}

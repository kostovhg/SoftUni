package app.service;

import java.util.List;

public interface HomeworkService<Homework, Long> {

    Homework findById(Long id);

    void remove(Homework homework);

    List<Homework> findAll();

    void save(Homework homework);
}

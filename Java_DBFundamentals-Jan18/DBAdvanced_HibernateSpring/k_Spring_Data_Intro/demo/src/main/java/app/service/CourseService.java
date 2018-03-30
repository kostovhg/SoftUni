package app.service;

import java.util.List;

public interface CourseService<Course, Long> {

    Course findById(Long id);

    void remove(Course course);

    List<Course> findAll();

    void save(Course course);
}

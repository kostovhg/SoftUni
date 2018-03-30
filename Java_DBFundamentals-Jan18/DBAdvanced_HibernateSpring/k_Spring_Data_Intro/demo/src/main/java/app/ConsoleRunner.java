package app;

import app.model.Course;
import app.model.Homework;
import app.model.Student;
import app.service.CourseService;
import app.service.HomeworkService;
import app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    public ConsoleRunner(StudentService studentService, HomeworkService homeworkService, CourseService courseService){
        this.studentService = studentService;
        this.homeworkService = homeworkService;
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) throws Exception {

        Course course = new Course();
        course.setName("Java DB Advance");
        course.setDescription("Java DB frameworks");
        course.setRegistrationDate(LocalDate.of(2018, 6, 3));
        course.setEndDate(LocalDate.of(2018, 8, 22));
        course.setPrice(new BigDecimal("320.00"));

        Student student = new Student();
        student.setName("Ivan");
        student.setBirthDate(LocalDate.of(1989, 10, 11));
        student.setPhoneNumber("359326548");
        student.setRegistrationDate(LocalDate.of(2015, 6, 13));

        Set<Student> students = new HashSet<>();
        students.add(student);

        course.setStudents(students);

        Homework homework01 = new Homework();
        homework01.setContent("New Homework 01");
        homework01.setCourse(course);
        homework01.setStudent(student);
        homework01.setSubissionDate(LocalDate.of(2015, 6, 14));

        homeworkService.save(homework01);

        Set<Homework> homeworks = new HashSet<>();
        homeworks.add(homework01);

        course.setHomework(homeworks);
        student.setHomeworks(homeworks);


        courseService.save(course);
        studentService.save(student);

    }
}

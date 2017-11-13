package bg.softuni.exceptions;

import bg.softuni.models.Course;

public class KeyNotFoundException extends RuntimeException {

    public static final String NOT_ENROLL_IN_COURSE = "Not enroll in course %s";

    public KeyNotFoundException(Course course) {
        super(String.format(NOT_ENROLL_IN_COURSE, course.getName()));
    }

    public KeyNotFoundException(String message){
        super(message);
    }
}

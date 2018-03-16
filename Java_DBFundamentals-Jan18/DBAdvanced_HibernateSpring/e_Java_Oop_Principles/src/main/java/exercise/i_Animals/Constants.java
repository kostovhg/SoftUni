package exercise.i_Animals;

import java.util.regex.Pattern;

public class Constants {

    static final String INVALID_INPUT = "Invalid input!";
    static final String MALE = "Male";
    static final String FEMALE = "Female";
    static final String PGN_NAME = "name";
    static final String PGN_AGE = "age";
    static final String PGN_GENDER = "gender";

    private static final String PATTERN_STRING = "^(?<" + PGN_NAME + ">\\w+) (?<" + PGN_AGE +">\\d+)(?: (?<gender>" + FEMALE +"|" + MALE + "))?$";
    static final Pattern STRING_PATTERN = Pattern.compile(PATTERN_STRING);
}

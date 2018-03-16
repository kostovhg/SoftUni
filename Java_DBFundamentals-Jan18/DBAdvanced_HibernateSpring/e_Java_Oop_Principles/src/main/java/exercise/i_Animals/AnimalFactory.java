package exercise.i_Animals;

import java.util.regex.Matcher;

import static exercise.i_Animals.Constants.*;

public class AnimalFactory {

    public static Animal newAnimal(Classes type, String input) {
        Matcher m = STRING_PATTERN.matcher(input);
        if(!m.find()) throw new IllegalArgumentException();
        String name = m.group(PGN_NAME);
        String age = m.group(PGN_AGE);
        String gender = m.group(PGN_GENDER);
        switch (type) {
            case DOG:
                return new Dog(name, age, gender);
            case CAT:
                return new Cat(name, age, gender);
            case FROG:
                return new Frog(name, age, gender);
            case KITTEN:
                return new Kitten(name, age, FEMALE);
            case TOMCAT:
                return new Tomcat(name, age, MALE);
            default:
                throw new IllegalArgumentException();
        }
    }
}

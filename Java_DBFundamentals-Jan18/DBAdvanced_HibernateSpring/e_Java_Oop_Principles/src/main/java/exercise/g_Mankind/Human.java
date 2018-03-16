package exercise.g_Mankind;

import static exercise.g_Mankind.Constants.*;

abstract class Human {

    private String firstName;
    private String lastName;

    Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    private void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() < FIRST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            LENGTH,
                            AT_LEAST,
                            String.valueOf(FIRST_NAME_MIN_LEN),
                            FIRST_NAME
                    ));
        }
        if (Character.isLowerCase(firstName.charAt(0))){
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            UPPERCASE,
                            FIRST_NAME
                    )
            );
        }
        this.firstName = firstName.trim();
    }

    void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() < LAST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            LENGTH,
                            AT_LEAST,
                            String.valueOf(LAST_NAME_MIN_LEN),
                            LAST_NAME
                    ));
    }
        if (Character.isLowerCase(lastName.charAt(0))){
            throw new IllegalArgumentException(
                    getErrorMsg(
                            EXPECTED,
                            UPPERCASE,
                            LAST_NAME
                    ));
        }
        this.lastName = lastName.trim();
    }

    @Override
    public String toString() {
        return String.format(
                HUMAN_TO_STRING_FORMAT,
                this.firstName,
                this.lastName
        );
    }
}

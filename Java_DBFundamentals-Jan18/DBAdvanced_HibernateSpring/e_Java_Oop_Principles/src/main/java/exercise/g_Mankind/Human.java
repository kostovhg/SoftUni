package exercise.g_Mankind;

import static exercise.g_Mankind.Constants.*;

public abstract class Human {


    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    protected void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().length() < FIRST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, LENGTH, AT_LEAST, String.valueOf(FIRST_NAME_MIN_LEN), FIRST_NAME));
        }
        if (Character.isLowerCase(firstName.charAt(0))){
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, UPPERCASE, FIRST_NAME)
            );
        }
        this.firstName = firstName.trim();
    }

    protected void setLastName(String lastName) {
        if (lastName == null || lastName.trim().length() < LAST_NAME_MIN_LEN) {
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, LENGTH, AT_LEAST, String.valueOf(LAST_NAME_MIN_LEN), LAST_NAME));
    }
        if (Character.isLowerCase(lastName.charAt(0))){
            throw new IllegalArgumentException(
                    getErrorMsg(EXPECTED, UPPERCASE, LAST_NAME));
        }
        this.lastName = lastName.trim();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("First Name: ");
        sb.append(this.getFirstName());
        sb.append(System.lineSeparator());
        sb.append("Last Name: ");
        sb.append(this.getLastName());
        sb.append(System.lineSeparator());

        return sb.toString();
    }
}

package exercise.g_Mankind;

public class Constants {
    // formats for common messages
    public static final String INVALID_HUMAN_FIRST_CHAR = "Expected upper case letter!Argument: %s";
    public static final String INVALID_HUMAN_NAME_FORMAT = "Expected length at least %d symbols!Argument: %s";
    public static final String INVALID_WORKER_LAST_NAME_FORMAT = "Expected length more than %d symbols!Argument: %s";
    public static final String VALUE_MISMATCH_FORMAT = "Expected value mismatch!Argument: %s";

    // Standard values and names
    public static final int FIRST_NAME_MIN_LEN = 4;
    public static final int LAST_NAME_MIN_LEN = 3;
    public static final int MIN_FNUM_SYMBOLS = 5;
    public static final int MAX_FNUM_SYMBOLS = 10;
    public static final String INVALID_FNUM = "Invalid faculty number!";

    // Just error msgs
    public static final String INVALID_FIRST_NAME_LETTER = "Expected upper case letter!Argument: firstName";
    public static final String INVALID_FIRST_NAME_LENGTH = "Expected length at least 4 symbols!Argument: firstName";
    public static final String INVALID_LAST_NAME_FIRST_LETTER = "Expected upper case letter!Argument: lastName";
    public static final String INVALID_LAST_NAME_LENGTH = "Expected length at least 3 symbols!Argument: lastName";

    // Words
    public static final String EXPECTED = "Expected";
    public static final String UPPERCASE = "upper case";
    public static final String VALUE_MISMATCH = "value mismatch";
    public static final String LENGTH = "length";
    public static final String MORE_THAN = "more than";
    public static final String AT_LEAST = "at least";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";



    public static final String getErrorMsg(String... args) {
        StringBuilder sb = new StringBuilder();

        switch (args[0]) {
            case EXPECTED:
                sb.append(EXPECTED).append(" ");
                switch (args[1]) {
                    case UPPERCASE:
                        sb.append(UPPERCASE).append(" letter");
                        break;
                    case VALUE_MISMATCH:
                        sb.append(VALUE_MISMATCH);
                        break;
                    default: // "length"
                        sb.append(LENGTH).append(" ");
                        sb.append(args[2]).append(" ").append(args[3]).append(" symbols");
                }
                sb.append("!Argument: ").append(args[args.length - 1]);
                break;
            default:
                sb.append("Invalid faculty number!");
        }

        return sb.toString();
    }
}

/*
Parameter           | Constraint                            | Exception Message
Human first name    | Should start with a capital letter    | "Expected upper case letter!Argument: firstName"
Human first name    | Should be more than 4 symbols         | "Expected length at least 4 symbols!Argument: firstName&quot"
Human last name     | Should start with a capital letter    | "Expected upper case letter!Argument: lastName"
Human last name     | Should be more than 3 symbols         | "Expected length at least 3 symbols!Argument: lastName"
Faculty number      | Should be in range [5..10] symbols    | "Invalid faculty number!"
Worker last name    | Should be more than 3 symbols         | "Expected length more than 3 symbols!Argument: lastName"
Week salary         | Should be more than 10                | "Expected value mismatch!Argument: weekSalary"
Working hours       | Should be in the range [1..12]        | "Expected value mismatch!Argument: workHoursPerDay"
 */

package exercise.g_Mankind;

import java.util.regex.Pattern;

/**
 * The class <h3>Constants</h3> contains all parameters needed for validating the input
 * and a method for generating the error message according the problem description
 *
 * NOTE: Most of the modifiers are removed for this variant of package structure, which is not optimal.
 *
 * @autor   kostovhg@gmail.com
 * @version 0.1a
 * @since   0.0b
 */
class Constants {
    /**
     * Standard integer values for names length and bonds for intervals
     *
     * FIRST_NAME_MIN_LEN  Minimum number of symbols for firstName field from Human class
     * LAST_NAME_MIN_LEN   Minimum number of symbols for lastName field from Human class
     * MIN_FNUM_SYMBOLS    Minimum number of symbols for facultyNumber field from Student class
     * MAX_FNUM_SYMBOLS    Maximum number of symbols for facultyNumber field from Student class
     * MIN_SALARY          Minimum double value for weekSalary field from Worker class
     * MIN_DAILY_HOURS     Minimum double value for workHoursPerDay field in Worker class
     * MAX_DAILY_HOURS     Maximum double value for workHoursPerDay field in Worker class
     */
    static final int FIRST_NAME_MIN_LEN = 4;
    static final int LAST_NAME_MIN_LEN = 3;
    private static final int MIN_FNUM_SYMBOLS = 5;
    private static final int MAX_FNUM_SYMBOLS = 10;
    static final double MIN_SALARY = 10d;
    static final double MIN_DAILY_HOURS = 1d;
    static final double MAX_DAILY_HOURS = 12d;

    /**
     * Formats and patterns used in input validation or toString method in current package objects
     */
    static final String DELIMITER = "\\s+";
    static final String HUMAN_TO_STRING_FORMAT = "First Name: %s%nLast Name: %s";
    static final String STUDENT_TO_STRING_FORMAT = "%s%nFaculty number: %s%n";
    static final String WORKER_TO_STRING_FORMAT = "%s%nWeek Salary: %.2f%nHours per day: %.2f%nSalary per hour: %.2f";
    private static final String FNUM_PATTERN_STRING = String.format("^([a-zA-Z0-9]{%d,%d})$", MIN_FNUM_SYMBOLS, MAX_FNUM_SYMBOLS);
    static final Pattern FNUM_PATTERN = Pattern.compile(FNUM_PATTERN_STRING);


    /**
     * Often used words
     */
    static final String EXPECTED = "Expected";
    static final String UPPERCASE = "upper case";
    static final String VALUE_MISMATCH = "value mismatch";
    static final String LENGTH = "length";
    static final String MORE_THAN = "more than";
    static final String AT_LEAST = "at least";
    static final String FIRST_NAME = "firstName";
    static final String LAST_NAME = "lastName";
    static final String WEEK_SALARY = "weekSalary";
    static final String WORK_HOURS_PER_DAY = "workHoursPerDay";
    private static final String THE_INTERVAL = " ";
    private static final String STR_LETTER = "letter";
    private static final String STR_SYMBOLS = "symbols";
    private static final String STR_ARGUMENT = "!Argument:";

    /**
     * Loneliest exception message
     */
    static final String INVALID_FACULTY_NUMBER = "Invalid faculty number!";

    /**
     * A method for generating messages according the problem description:
     * Parameter           | Constraint                            | Exception Message
     * Human first name    | Should start with a capital letter    | "Expected upper case letter!Argument: firstName"
     * Human first name    | Should be more than 4 symbols         | "Expected length at least 4 symbols!Argument: firstName"
     * Human last name     | Should start with a capital letter    | "Expected upper case letter!Argument: lastName"
     * Human last name     | Should be more than 3 symbols         | "Expected length at least 3 symbols!Argument: lastName"
     * Faculty number      | Should be in range [5..10] symbols    | "Invalid faculty number!"
     * Worker last name    | Should be more than 3 symbols         | "Expected length more than 3 symbols!Argument: lastName"
     * Week salary         | Should be more than 10                | "Expected value mismatch!Argument: weekSalary"
     * Working hours       | Should be in the range [1..12]        | "Expected value mismatch!Argument: workHoursPerDay"
     *
     * The output messages are separated according the repetition pattern of each of their components:
     * ErrorMessage
     * ||__ (not used)Invalid faculty number! (one message not repeated elsewhere)
     * |___ (EXPECTED)Expected ...!Argument: ... (All other 7 messages)
     *      |||__ (VALUE_MISMATCH)value mismatch ( with different arguments - 2 messages)
     *      ||___ (UPPERCASE)upper case letter (with different arguments - 2 messages)
     *      |____ (LENGTH)length ... (All other 3 messages)
     *              ||___ (args[2] - AT_LEAST) at least %s symbols (two messages with different number of symbols)
     *              |____ (args[2] - MORE_THAN) more than %s symbols (one message)
     *
     * To create pattern of args
     *
     * @param args  Different count of parameters composed according above rules. The parameters are used not only
     *              to switch different cases but also to be included in the generation of output
     * @return      String
     */
    static String getErrorMsg(String... args) {
        StringBuilder sb = new StringBuilder();

        switch (args[0]) {
            case EXPECTED:
                // All messages starting with "Expected ..."
                sb.append(EXPECTED).append(THE_INTERVAL);
                switch (args[1]) {
                    // All messages starting with "Expected upper case letter..."
                    case UPPERCASE:
                        sb.append(UPPERCASE).append(THE_INTERVAL).append(STR_LETTER);
                        break;
                    case VALUE_MISMATCH:
                        // All messages starting with "Expected value mismatch..."
                        sb.append(VALUE_MISMATCH);
                        break;
                    default: // "length"
                        // All values starting with "Expected length (at least)/(more than) (value) symbols..."
                        sb.append(LENGTH).append(THE_INTERVAL)
                                .append(args[2]).append(THE_INTERVAL)
                                .append(args[3]).append(THE_INTERVAL)
                                .append(STR_SYMBOLS);
                }
                // All messages ending with "...!Argument: (argument)"
                sb.append(STR_ARGUMENT).append(THE_INTERVAL)
                        .append(args[args.length - 1]);
                break;
            default:
                // simple message
                sb.append(INVALID_FACULTY_NUMBER);
        }

        return sb.toString();
    }
}
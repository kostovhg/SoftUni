package soft_uni.user_system.annotations.customValidatorImpl;

import soft_uni.user_system.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPasswordValidator implements ConstraintValidator<Password, String> {

    /**
     * The StringBuilder will be as a container for different error messages
     */
    StringBuilder sb;
    private int max;
    private boolean digits;
    private boolean lowercase;
    private boolean uppercase;
    /**
     * Fields of annotation implementation
     */
    private int min;
    private boolean specialSymbol;

    /**
     * Initialize of fields - called only once on place where annotation is applied
     * Getting annotation parameters from annotation application
     * (default will be used where user does not provide one)
     *
     * @param constraintAnnotation Values of parameters given where annotation is applied
     */
    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.minLength();
        max = constraintAnnotation.maxLength();
        digits = constraintAnnotation.containsDigit();
        lowercase = constraintAnnotation.containsLowercase();
        uppercase = constraintAnnotation.containsUppercase();
        specialSymbol = constraintAnnotation.containsSpecialSymbol();
    }

    /**
     * isValid is called every time on new pass throw the field or method where annotation is applied
     *
     * @param password String - represent the value that will be evaluated
     * @param context  - Provides contextual data and operation when applying a given constraint
     *                 validator. At least one ConstraintViolation must be defined
     *                 (either the default one, of if the default ConstraintViolation is disabled,
     *                 a custom one).
     * @return
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        /**
         * Null the sb on each validation
         */
        sb = new StringBuilder();

        if (password.length() < min || password.length() > max) {
            sb.append(String.format(
                    "Length of password should be between %d and %d symbols%n", min, max));
        }
        if (lowercase && !password.matches(".*[a-z].*")) {
            sb.append("The password should contain at least one letter (a-z)").append(System.lineSeparator());
        }

        if (uppercase && !password.matches(".*[A-Z].*")) {
            sb.append("The password should contain at least one capital letter (A-Z)").append(System.lineSeparator());
        }
        if (digits && !password.matches(".*\\d.*")) {
            sb.append("The password should contain at least one digit (0-9)").append(System.lineSeparator());
        }
        if (specialSymbol && !password.matches(".*[!@#$%^&*()_+<>?].*")) {
            sb.append("The password should contain at least one special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)").append(System.lineSeparator());
        }

        if (!sb.toString().equals("")) {
            /**
             * Disables the default ConstraintViolation object generation
             * (which is using the message template declared on the constraint).
             * Useful to set a different violation message or generate
             * a ConstraintViolation based on a different property.
             */
            context.disableDefaultConstraintViolation();

            /**
             * Returns a constraint violation builder building a violation report allowing to
             * optionally associate it to a sub path. The violation message will be interpolated.
             */
            context.buildConstraintViolationWithTemplate(sb.toString().trim()).addConstraintViolation();
            return false;
        }

        return true;
    }
}

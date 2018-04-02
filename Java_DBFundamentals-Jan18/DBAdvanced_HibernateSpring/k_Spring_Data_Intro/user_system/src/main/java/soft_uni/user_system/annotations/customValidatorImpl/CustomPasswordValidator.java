package soft_uni.user_system.annotations.customValidatorImpl;

import soft_uni.user_system.annotations.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomPasswordValidator implements ConstraintValidator<Password, String> {

    private int min;
    private int max;
    private boolean digits;
    private boolean lowercase;
    private boolean uppercase;
    private boolean specialSymmbol;
    StringBuilder sb;
    private String message;

    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.minLength();
        max = constraintAnnotation.maxLength();
        digits = constraintAnnotation.containsDigit();
        lowercase = constraintAnnotation.containsLowercase();
        uppercase = constraintAnnotation.containsUppercase();
        specialSymmbol = constraintAnnotation.containsSpecialSymbol();
        message = constraintAnnotation.message();
        sb = new StringBuilder();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        sb = new StringBuilder();
        if (password.length() < min || password.length() > max) {
            sb.append(String.format(
                    "Length of password should be between %d and %d symbols%n", min, max));
        }
        if (lowercase && !password.matches(".*[a-z].*")) {
            sb.append("The password should contain at least one letter (a-z)\n");
        }

        if (uppercase && !password.matches(".*[A-Z].*")) {
            sb.append("The password should contain at least one capital letter (A-Z)\n");
        }
        if (digits && !password.matches(".*\\d.*")) {
            sb.append("The password should contain at least one digit (0-9)\n");
        }
        if (specialSymmbol && !password.matches(".*[!@#$%^&*()_+<>?].*")) {
            sb.append("The password should contain at least one special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)\n");
        }

        if( !sb.toString().equals("")){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( sb.toString() ).addConstraintViolation();
            return false;
        }

        return true;
    }
}

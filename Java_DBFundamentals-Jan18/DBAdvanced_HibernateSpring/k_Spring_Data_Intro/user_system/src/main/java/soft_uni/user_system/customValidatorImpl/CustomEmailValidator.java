package soft_uni.user_system.customValidatorImpl;

import soft_uni.user_system.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<Email, String> {

    private Pattern regexPattern = null;

    @Override
    public void initialize(Email constraintAnnotation) {
        this.regexPattern = Pattern.compile("^.{3,20}@.{3,20}\\..{2,4}$");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher m = regexPattern.matcher(value);
        return true;
    }
}

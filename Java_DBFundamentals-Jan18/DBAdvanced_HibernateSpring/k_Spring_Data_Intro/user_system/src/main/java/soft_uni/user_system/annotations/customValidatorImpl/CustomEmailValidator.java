package soft_uni.user_system.annotations.customValidatorImpl;

import soft_uni.user_system.annotations.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<Email, String> {

    private Pattern regexPattern;// = Pattern.compile("^(?<user>(?:[a-zA-Z0-9]+[_.-]*)+[a-zA-Z0-9]+)(?:@)(?<host>(?:[a-zA-Z-]+\\.)+\\w{2,})$");

    @Override
    public void initialize(Email constraintAnnotation) {
        regexPattern = Pattern.compile(constraintAnnotation.regex());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher m = regexPattern.matcher(value);
        return m.find();
    }
}

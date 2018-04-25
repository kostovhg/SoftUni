package app.retake.parser;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class ValidationUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public ValidationUtil() {
    }

    public static <T> boolean isValid(T t) {
        return t != null && validator.validate(t).size() == 0;
    }

    private static void setErrorMessage(final ConstraintValidatorContext context, final String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
    }

    public static <T> Set<String> getErrors(T t) {
        Set<ConstraintViolation<T>> errors = validator.validate(t);
        return errors.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    }

}

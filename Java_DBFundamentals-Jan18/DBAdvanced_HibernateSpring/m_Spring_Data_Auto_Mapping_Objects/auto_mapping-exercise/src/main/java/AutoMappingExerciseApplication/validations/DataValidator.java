package AutoMappingExerciseApplication.validations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public final class DataValidator {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public DataValidator() {
    }

    public static <T> boolean isValid(T t){
        return t != null && validator.validate(t).size() == 0;
    }

    public static <T> String getInvalidParameterMessage(T target){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<T>> constraints = factory.getValidator().validate(target);

        for (ConstraintViolation<T> constraint : constraints) {
            return constraint.getMessage();
        }

        return null;
    }
}

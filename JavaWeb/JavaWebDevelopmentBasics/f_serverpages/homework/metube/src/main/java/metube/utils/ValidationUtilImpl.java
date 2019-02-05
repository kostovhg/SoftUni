package metube.utils;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public<M> boolean isValid(M model){
        return this.validator.validate(model).size() == 0;
    }
}

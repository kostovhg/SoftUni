package com.softuni.javascriptajax.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class PastDateValidator implements ConstraintValidator<PastDate, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }
        Date today = new Date();
        return date.before(today);
    }
}

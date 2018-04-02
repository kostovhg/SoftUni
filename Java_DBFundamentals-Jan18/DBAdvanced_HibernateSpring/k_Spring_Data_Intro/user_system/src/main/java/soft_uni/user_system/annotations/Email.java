package soft_uni.user_system.annotations;

import soft_uni.user_system.annotations.customValidatorImpl.CustomEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Target({FIELD, METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
@Documented
public @interface Email {

    String message() default "Invalid email.";
    String regex() default "^(?<user>(?:[a-zA-Z0-9]+[_.-]*)+[a-zA-Z0-9]+)(?:@)(?<host>(?:[a-zA-Z-]+\\.)+\\w{2,})$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package soft_uni.user_system.annotations;

import soft_uni.user_system.annotations.customValidatorImpl.CustomEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, METHOD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomEmailValidator.class)
@Documented
public @interface Email {

    /**
     * Default message.
     *
     * @return String
     */
    String message() default "Invalid email.";

    /**
     * A regex expression that validate a email
     *
     * @return String
     */
    String regex() default "^(?<user>(?:[a-zA-Z0-9]+[_.-]*)+[a-zA-Z0-9]+)(?:@)(?<host>(?:[a-zA-Z-]+\\.)+\\w{2,})$";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

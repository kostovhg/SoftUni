package soft_uni.user_system.annotations;

import soft_uni.user_system.annotations.customValidatorImpl.CustomPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "message";

    int minLength();

    int maxLength();

    boolean containsDigit() default true;

    boolean containsLowercase() default true;

    boolean containsUppercase() default true;

    boolean containsSpecialSymbol() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

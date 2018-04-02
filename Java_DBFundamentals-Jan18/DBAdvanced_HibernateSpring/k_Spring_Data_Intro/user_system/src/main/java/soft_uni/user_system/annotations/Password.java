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

    /**
     * Message to be returned in case of violation
     *
     * @return
     */
    String message() default "message";

    /**
     * @return int
     * @Required Minimum password length
     */
    int minLength();

    /**
     * @return
     * @Required Maximum password length
     */
    int maxLength();


    /**
     * Set the requirements for password to contain at least 1 digit
     *
     * @return
     * @Default true
     */
    boolean containsDigit() default true;

    /**
     * Set the requirements for password to contain at least 1 lowercase letter
     * @Default true
     * @return
     */
    boolean containsLowercase() default true;

    /**
     * Set the requirements for password to contain at least 1 uppercase letter
     * @Default true
     * @return
     */
    boolean containsUppercase() default true;

    /**
     * Set the requirements for password to contain at least 1 special symbol
     * Special symbols are (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)
     * @Default true
     * @return
     */
    boolean containsSpecialSymbol() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

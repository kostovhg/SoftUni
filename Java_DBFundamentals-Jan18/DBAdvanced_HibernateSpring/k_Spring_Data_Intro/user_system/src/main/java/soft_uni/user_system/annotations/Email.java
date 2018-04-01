package soft_uni.user_system.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = ValidationEventLocator.class)
@Documented
public @interface Email {


}

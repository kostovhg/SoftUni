package n_InfernoInfinityRefactoring.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CustomAnnotation {

    String author() default "Pesho";

    int revision() default 3;

    String description() default "Used for Java OOP Advanced course - Enumerations and Annotations.";

    String[] reviewers() default {"Pesho", "Svetlio"};
}

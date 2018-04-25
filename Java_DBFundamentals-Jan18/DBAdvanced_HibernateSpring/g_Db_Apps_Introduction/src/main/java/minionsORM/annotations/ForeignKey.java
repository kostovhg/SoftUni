package minionsORM.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
    String name();
    String toTable();
    String toColumn() default "id";
    String constrainName();
    String onDelete() default "CASCADE";
    String onUpdate() default "CASCADE";
}


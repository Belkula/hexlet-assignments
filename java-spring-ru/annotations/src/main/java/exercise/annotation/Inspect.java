package exercise.annotation;

// BEGIN
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
// Аннотация доступна в рантайме
@Retention(RetentionPolicy.RUNTIME)
public @interface Inspect {
    // Можно добавить параметры для аннотации при необходимости
    String level() default "INFO";
}
// END

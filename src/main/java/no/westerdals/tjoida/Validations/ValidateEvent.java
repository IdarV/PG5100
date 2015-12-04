package no.westerdals.tjoida.Validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EventValidator.class)
@Documented
public @interface ValidateEvent {
    String message() default "type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

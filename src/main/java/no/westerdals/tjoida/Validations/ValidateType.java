package no.westerdals.tjoida.Validations;


import no.westerdals.tjoida.Models.Type;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Cyzla on 25.09.2015.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TypeValidator.class)
@Documented
public @interface ValidateType {
    String message() default "type is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package no.westerdals.tjoida.Validations;

import no.westerdals.tjoida.Models.Type;
import no.westerdals.tjoida.Models.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Cyzla on 25.09.2015.
 */
public class TypeValidator implements ConstraintValidator<ValidateType, User> {

    @Override
    public void initialize(ValidateType validateType) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        for(Type type : Type.values()){
            if(type.name().equals(user.getType().name().toUpperCase()))
            return true;
        }
        return false;
    }
}

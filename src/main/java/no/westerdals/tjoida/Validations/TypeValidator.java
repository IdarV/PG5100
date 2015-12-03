package no.westerdals.tjoida.Validations;

import no.westerdals.tjoida.Models.UserType;
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
        for(UserType userType : UserType.values()){
            if(userType.name().equals(user.getUserType().toUpperCase()))
            return true;
        }
        return false;
    }
}

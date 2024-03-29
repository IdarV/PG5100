package no.westerdals.tjoida.Validations;

import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.Models.UserType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TypeValidator implements ConstraintValidator<ValidateType, User> {

    @Override
    public void initialize(ValidateType validateType) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        for (UserType userType : UserType.values()) {
            if (userType.name().equals(user.getUserType().toString().toUpperCase()))
                return true;
        }
        return false;
    }
}

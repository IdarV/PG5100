package no.westerdals.tjoida.Validations;

import no.westerdals.tjoida.Models.Event;
import no.westerdals.tjoida.Models.EventType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EventValidator implements ConstraintValidator<ValidateEvent, Event> {

    @Override
    public void initialize(ValidateEvent validateEvent) {

    }

    @Override
    public boolean isValid(Event event, ConstraintValidatorContext constraintValidatorContext) {
        for (EventType eventType : EventType.values()) {
            if(eventType.name().equals(event.getEventType().toString().toUpperCase())){
                return true;
            }
        }
        return false;
    }
}

package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertFalse;

/**
 * Created by Cyzla on 22.10.2015.
 */
public class LocationValidationTest {
    ValidatorFactory validatorFactory;
    Validator validator;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testId() throws Exception {
        Location location = new Location();
        location.setBuilding("testBuilding");
        location.setRoom("testRoom");
        assertFalse(0 < location.getId());

    }
}

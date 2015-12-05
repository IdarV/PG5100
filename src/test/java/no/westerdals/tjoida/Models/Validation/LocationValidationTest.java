package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.Location;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class LocationValidationTest {
    private static final String STD_ROOM = "room";
    private static final String STD_BUILDING = "building";

    private ValidatorFactory validatorFactory;
    private Validator validator;
    private Location location;


    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        location = new Location();
    }

    @Test
    public void testEmpty() throws Exception {
        Set<ConstraintViolation<Location>> validate = validator.validate(location);
        assertTrue("Building and room cannot be null", 2 == validate.size());
    }

    @Test
    public void testFull() throws Exception {
        location.setRoom(STD_ROOM);
        location.setBuilding(STD_BUILDING);

        Set<ConstraintViolation<Location>> validate = validator.validate(location);
        assertTrue("Full location has no violations", validate.isEmpty());
    }

    @Test
    public void testNoRoom() throws Exception {
        location.setBuilding(STD_BUILDING);
        Set<ConstraintViolation<Location>> validate = validator.validate(location);
        assertTrue("Room cannot be null", 1 == validate.size());

    }

    @Test
    public void testNoBuilding() throws Exception {
        location.setRoom(STD_ROOM);
        Set<ConstraintViolation<Location>> validate = validator.validate(location);
        assertTrue("Building cannot be null", 1 == validate.size());
    }
}

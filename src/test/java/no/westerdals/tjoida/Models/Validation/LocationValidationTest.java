package no.westerdals.tjoida.Models.Validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testId() throws Exception {


    }
}

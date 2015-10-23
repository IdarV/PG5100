package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.Course;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Cyzla on 23.10.2015.
 */
public class CourseValidationTest {
    ValidatorFactory validatorFactory;
    Validator validator;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testNameValidation() throws Exception {
        Course course = new Course();
        Set<ConstraintViolation<Course>> validate = validator.validate(course);
        assertTrue("Without name, it should be unvalidated", 1 == validate.size());
        course.setName("some course");
        validate = validator.validate(course);
        assertTrue(0 == validate.size());
    }
}

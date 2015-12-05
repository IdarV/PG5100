package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class CourseValidationTest {
    ValidatorFactory validatorFactory;
    Validator validator;
    Course course;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        course = new Course();
    }

    @Test
    public void testNameValidation() throws Exception {
        Set<ConstraintViolation<Course>> validate = validator.validate(course);
        assertTrue("Without name, it should be unvalidated", 1 == validate.size());
        course.setName("some course");
        validate = validator.validate(course);
        assertTrue(0 == validate.size());
    }

    @Test
    public void testUsersValidation() throws Exception {
        course.setName("some course");
        Set<ConstraintViolation<Course>> validate = validator.validate(course);
        assertTrue("default validator has null users, which should not fail", 0 == validate.size());

        List<User> users = new ArrayList<>();
        course.setUsers(users);
        validate = validator.validate(course);
        assertTrue("empty list should be allowed", 0 == validate.size());

        for (int i = 0; i <= 100; i++) {
            users.add(new User());
        }

        course.setUsers(users);
        validate = validator.validate(course);
        assertTrue("list with > 100 nodes should not be allowed", 1 == validate.size());
    }
}

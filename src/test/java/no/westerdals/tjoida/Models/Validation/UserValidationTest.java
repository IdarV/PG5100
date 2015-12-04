package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.Models.UserType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.*;

import java.util.Set;

import static org.junit.Assert.*;

public class UserValidationTest {
    private final String STD_EMAIL = "email@email.com";
    private final int STD_ID = 0;
    private final String STD_PASSWORD = "mYP4SSW0RD!";
    private final UserType STD_TYPE = UserType.STUDENT;
    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void testSetFalseyId() throws Exception {
        User user = new User(-1, STD_EMAIL, STD_PASSWORD, STD_TYPE);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSetTruthyId() throws Exception {
        User user = new User(0, STD_EMAIL, STD_PASSWORD, STD_TYPE);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }

    @Test
    public void testSetFalseyEmail() throws Exception {
        String emails[] = {"notamail",
                "not_a_mail.com",
                "not_a_mail@not_a_dns",
                "not_a_mail@not_a_dns.waytoolongtopleveldomain"};

        User user;
        for (String email : emails) {
            user = new User(STD_ID, email, STD_PASSWORD, STD_TYPE);
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertEquals(1, violations.size());
        }
    }

    @Test
    public void testSetPassword() throws Exception {
        String emails[] = {"nice_email@host.com",
                "norwegian@oslo.no",
                "verylongbutstill.very.legal_email@somewhereintheworld.com"};

        User user;
        for (String email : emails) {
            user = new User(STD_ID, email, STD_PASSWORD, STD_TYPE);
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertEquals(0, violations.size());
        }
    }

    @Test
    public void testSetType() throws Exception {
        User user = new User(STD_ID, STD_EMAIL, STD_PASSWORD, STD_TYPE);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue("", violations.isEmpty());
    }


}
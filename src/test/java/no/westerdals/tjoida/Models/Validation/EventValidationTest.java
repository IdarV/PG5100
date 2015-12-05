package no.westerdals.tjoida.Models.Validation;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Event;
import no.westerdals.tjoida.Models.EventType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class EventValidationTest {
    private Date STD_START_TIME = new Date();
    private Date STD_END_TIME = new Date();
    private final static EventType STD_EVENT_TYPE = EventType.LECTURE;
    private final static String STD_TITLE = "myEventTitle";
    private final static String STD_DESCRIPTION = "myDescription";


    private ValidatorFactory validatorFactory;
    private Validator validator;
    private Event event;

    @Before
    public void setUp() throws Exception {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        Course course = new Course();
        course.setName("courseName");
        event = new Event(STD_EVENT_TYPE, STD_TITLE, STD_DESCRIPTION, course, STD_START_TIME, STD_END_TIME);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFullEvent() throws Exception {
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Fully made event should not have any violations", validate.isEmpty());
    }

    @Test
    public void testEmptyEvent() throws Exception {
        event = new Event();
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Empty event should break 3 @NotNull violations", 3 == validate.size());
    }

    @Test
    public void testEmptyStartDate() throws Exception {
        event.setStartTime(null);
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Event startDate cannot be null", 1 == validate.size());
    }

    @Test
    public void testEmptyEndDate() throws Exception {
        event.setEndTime(null);
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Event endDate cannot be null", 1 == validate.size());
    }

    @Test
    public void testEmptyType() throws Exception {
        event.setEventType(null);
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Event type cannot be null", 1 == validate.size());
    }

    @Test
    public void testTooLongDescription() throws Exception {
        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Event description can be under 100 chars long", validate.isEmpty());
        String longDescription = "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789" +
                "01234556789";
        event.setDescription(longDescription);
        validate = validator.validate(event);
        assertTrue("Event description cannot be over 100 chars long", 1 == validate.size());
    }

    @Test
    public void testTitleSize() throws Exception {
        String shortTitle = "tiny";
        String longTitle = "0123456789" + "0123456789" + "012345";
        event.setTitle(shortTitle);

        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        assertTrue("Event title cannot be under 5 characters long", 1 == validate.size());

        event.setTitle(longTitle);

        validate = validator.validate(event);
        assertTrue("Event title cannot be over 25 chars long", 1 == validate.size());
    }

    @Test
    public void testValidCourse() throws Exception {
        event.getCourse().setName(null);

        Set<ConstraintViolation<Event>> validate = validator.validate(event);
        validate = validator.validate(event);
        assertTrue("Course must be valid in a event", 1 == validate.size());

    }
}

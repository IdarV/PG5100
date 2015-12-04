package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Event;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import no.westerdals.tjoida.service.EventService.EventDAO;
import no.westerdals.tjoida.service.EventService.EventJPA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class EventJpaIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EventDAO persister;
    private CourseDAO coursePersister;
    private Event event;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        coursePersister = new CourseJPA(entityManager);
        persister = new EventJPA(entityManager);
        event = new Event();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testGet() throws Exception {
        Event event = persister.getEvent(100);
        assertNotNull(event);
        assertEquals("First event should have id 100", 100, event.getId());
    }

    @Test
    public void testGetEvents() throws Exception {
        List<Event> events = persister.getEvents();
        assertTrue("There is more than one event in init.sql", 1 < events.size());
    }

    @Test
    public void testPersist() throws Exception {
        event = getDefaultEvent();
        assertEquals("Before persisting id is 0", 0, event.getId());
        persister.persist(event);
        assertTrue("After persisting id is sat", 0 < event.getId());
    }

    @Test
    public void testDelete() throws Exception {
        event = getDefaultEvent();
        persister.persist(event);
        assertTrue("After persisting id is sat", 0 < event.getId());
        assertNotEquals("persister should contain event", persister.getEvent(event.getId()), null);
        persister.deleteEvent(event);
        assertEquals("after removal persister should not contain event", persister.getEvent(event.getId()), null);

    }

    private Event getDefaultEvent() {
        Event event = new Event();
        event.setTitle("eventTitle");
        event.setDescription("EventDescription");
        event.setCourse(coursePersister.getCourse(100));
        return event;
    }
}

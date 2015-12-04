package no.westerdals.tjoida.Models.Controller;


import no.westerdals.tjoida.Controller.EventController;
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

import static org.junit.Assert.*;

public class EventControllerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EventDAO persister;
    private CourseDAO coursePersister;
    private EventController eventController;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        persister = new EventJPA(entityManager);
        coursePersister = new CourseJPA(entityManager);
        eventController = new EventController(persister, coursePersister);
        eventController.init();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testInit() throws Exception {
        assertTrue("init should instantiate empty event", eventController.getEvent().getCourse() == null);
        assertTrue("init should instantiate empty event", eventController.getEvent().getDescription() == null);
        assertTrue("init should instantiate empty event", eventController.getEvent().getTitle() == null);
    }

    @Test
    public void testInitEvent() throws Exception {
        eventController.setSelectedID(100);
        eventController.initEvent();

        assertNotNull("eventcontroller should get event 100, and that should exist in database", eventController.getEvent());
    }

    @Test
    public void testPersistNewEvent() throws Exception {
        assertEquals("Before persist, event does not have ID", 0, eventController.getEvent().getId());
        eventController.persistNewEvent();
        assertTrue("Before persist, event does not have ID", 0 < eventController.getEvent().getId());
    }

    @Test
    public void testGetAll() throws Exception {
        assertTrue("getAll should return multiple results, it's populated in init.sql", 1 < eventController.getAll().size());
    }

    @Test
    public void testUpdateEvent() throws Exception {
        String newTitle = "newTitle";
        eventController.setSelectedID(100);
        eventController.initEvent();
        assertNotEquals(eventController.getEvent().getTitle(), newTitle);
        eventController.getEvent().setTitle(newTitle);
        eventController.updateEvent();
        assertEquals(eventController.getEvent().getTitle(), newTitle);
    }
}

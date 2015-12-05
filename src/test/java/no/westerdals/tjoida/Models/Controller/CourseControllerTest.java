package no.westerdals.tjoida.Models.Controller;

import no.westerdals.tjoida.Controller.CourseController;
import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import no.westerdals.tjoida.service.LocationService.LocationDAO;
import no.westerdals.tjoida.service.LocationService.LocationJPA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class CourseControllerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CourseDAO persister;
    private LocationDAO locationPersister;
    private CourseController courseController;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        persister = new CourseJPA(entityManager);
        locationPersister = new LocationJPA(entityManager);
        courseController = new CourseController(persister, locationPersister);
        courseController.init();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testGetFirstCourse() throws Exception {
        Course firstCourse = courseController.getFirst();
        assertEquals(100, firstCourse.getId());
    }

    @Test
    public void testInitCourse() {
        courseController.setSelectedID(101);
        assertNotEquals(101, courseController.getCourse().getId());
        courseController.initCourse();
        assertEquals(101, courseController.getSelectedID());
        Course initializedCourse = courseController.getCourse();
        assertEquals(101, initializedCourse.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Course> courses = courseController.getAll();
        assertTrue("There are multiple courses defined in init.sql", 1 < courses.size());
    }

    @Test
    public void testPersistNewCourse() throws Exception {
        Course course = courseController.getCourse();
        courseController.setSelectedLocationID(100);
        String name = "courseName";
        course.setName(name);

        assertEquals(0, course.getId());
        courseController.persistNewCourse();
        assertTrue(0 < course.getId());
    }

    @Test
    public void testUpdateExistingCourse() throws Exception {
        courseController.setCourse(persister.getCourses().get(0));
        String oldName = courseController.getCourse().getName();
        String newName = "newCourseNameInTest";
        courseController.getCourse().setName(newName);
        courseController.updateExistingCourse(courseController.getCourse().getId());
        assertNotEquals(oldName, persister.getCourse(courseController.getCourse().getId()).getName());
        assertEquals(newName, persister.getCourse(courseController.getCourse().getId()).getName());
    }
}

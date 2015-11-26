package no.westerdals.tjoida.Models.Controller;

import no.westerdals.tjoida.Controller.CourseController;
import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
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
    private CourseJPA courseJpa;
    private CourseController courseController;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Course");
        entityManager = entityManagerFactory.createEntityManager();
        courseJpa = new CourseJPA(entityManager);
        courseController = new CourseController(courseJpa);
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
    public void testInitCourse(){
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
        String name = "courseName";
        course.setName(name);

        assertEquals(0, course.getId());

        courseController.persistNewCourse();

        assertTrue(0 < course.getId());
    }

    @Test
    public void testUpdateExistingCourse() throws Exception {
        // TODO;

    }
}

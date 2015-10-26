package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CourseJpaIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CourseJPA courseJPA;
    private Course course;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Course");
        entityManager = entityManagerFactory.createEntityManager();
        courseJPA = new CourseJPA(entityManager);
        course = new Course();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testPersistUsers() throws Exception {
        Course course = new Course();

        User listUser = new User();
        listUser.setEmail("email@email.com");
        listUser.setType("Test");
        listUser.setPassword("JWOEHJWAKEN2323!212#");
        List<User> userList = new ArrayList<>();
        userList.add(listUser);
        course.setUsers(userList);
        course.setName("CourseTest");

        assertFalse(0 < course.getId());
        assertFalse(0 < course.getUsers().get(0).getId());
        courseJPA.persist(course);
        assertTrue(0 < course.getId());
        assertTrue(0 < course.getUsers().get(0).getId());
    }

    @Test
    public void testRemoveUser() throws Exception {
        Course course = new Course();
        Course existsInDatabase = entityManager.find(Course.class, course.getId());
        assertNull("course should not exist in database before persisted", existsInDatabase);
        courseJPA.persist(course);
        existsInDatabase = entityManager.find(Course.class, course.getId());
        assertNotNull("when persisted, course should exist", existsInDatabase);
        courseJPA.deleteCourse(course);
        existsInDatabase = entityManager.find(Course.class, course.getId());
        assertNull("after deletion, course should not exist in database", existsInDatabase);
    }
}

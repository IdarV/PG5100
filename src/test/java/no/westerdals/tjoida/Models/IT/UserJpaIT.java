package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.Models.UserType;
import no.westerdals.tjoida.service.UserService.JPAUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserJpaIT {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    private static final String DEFAULT_EMAIL = "test@testmail.com";

    JPAUserDao userJpa;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        userJpa = new JPAUserDao(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testId() throws Exception {
        User user = new User();
        user.setEmail("test@testmail.com");
        user.setPassword("wahej23S!JKLw");
        user.setUserType(UserType.TEACHER);

        assertEquals(0, user.getId());

        userJpa.persist(user);

        assertTrue(0 < user.getId());
    }

    @Test
    public void testCourses() throws Exception {
        User user = new User();
        user.setEmail(DEFAULT_EMAIL);

        Course course = new Course();
        course.setName("testCourse");
        List<Course> courses = new ArrayList<>();
        courses.add(course);

        user.setCourses(courses);
        assertFalse(0 < user.getId());
        userJpa.persist(user);
        assertTrue(0 < user.getId());

        List<Course> persistedCourses = userJpa.getUser(user.getId()).getCourses();
        assertNotNull(persistedCourses);
        System.out.println(persistedCourses.size());

        assertEquals(courses.get(0).getName(), persistedCourses.get(0).getName());

    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setEmail(DEFAULT_EMAIL);
        userJpa.persist(user);
        assertTrue(0 < user.getId());

        String newUserEmail = "newUserEmail@email.com";
        user.setEmail(newUserEmail);
        userJpa.update(user);
        User updatedUser = userJpa.getUser(user.getId());
        assertEquals(newUserEmail, updatedUser.getEmail());
    }
}

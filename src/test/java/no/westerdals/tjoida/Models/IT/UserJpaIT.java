package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import no.westerdals.tjoida.service.UserService.JPAUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cyzla on 15.10.2015.
 */
public class UserJpaIT {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    private static final String DEFAULT_EMAIL = "test@testmail.com";

    JPAUserDao userJpa;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
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
        user.setType("teacher");

        assertEquals(0, user.getId());

        userJpa.persist(user);

        assertTrue(0 < user.getId());
    }

    @Test
    public void testCourses() throws Exception{
        User user = new User();
        user.setEmail(DEFAULT_EMAIL);
        assertNotNull(userJpa.getCourses());

        CourseJPA courseJPA = new CourseJPA(entityManager);
        List<Course> courses = courseJPA.getCourses();

        System.out.println("listing corses");
        for(Course c : courses){
            System.out.println(c.getName());
        }
        System.out.println("done listing corses");

        user.setCourses(courses);
        userJpa.persist(user);

        assertNotNull(userJpa.getCourses());

        List<Course> persistedCourses = userJpa.getUser(user.getId()).getCourses();

        System.out.println("User email:" + userJpa.getUser(user.getId()).getEmail());
        System.out.println("listing users");
        for(Course c : persistedCourses){
            System.out.println(c.getName());
        }
        System.out.println("done listing users");
    }
}

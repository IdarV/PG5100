package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.User;
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

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Cyzla on 15.10.2015.
 */
public class UserJpaIT {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
//        entityManagerFactory.close();
//        entityManager.close();
    }

    @Test
    public void testId() throws Exception {
        User user = new User();
        user.setEmail("test@testmail.com");
        user.setPassword("wahej23S!JKLw");
        user.setType("teacher");

        assertEquals(0, user.getId());

        entityManager.persist(user);

        assertTrue(0 < user.getId());
    }
}

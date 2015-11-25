package no.westerdals.tjoida.Models.Controller;

import no.westerdals.tjoida.Controller.UserController;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.JPAUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Cyzla on 24.11.2015.
 */
public class UserControllerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private JPAUserDao userDao;
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new JPAUserDao(entityManager);
        userController = new UserController(userDao);
        userController.init();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testInit() throws Exception {
        userController.init();
        assertNotNull(userController.getUser());
    }

    @Test
    public void testGetFirstUser() throws Exception {
        User firstUser = userController.getFirst();
        assertEquals(100, firstUser.getId());
    }

    @Test
    public void testInitUser() throws Exception {
        userController.setSelectedID(101);
        userController.initUser();
        assertEquals(101, userController.getSelectedID());
        User initializedUser = userController.getUser();
        assertEquals(101, initializedUser.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<User> users = userController.getAll();
        assertTrue("We have more than one user in init.sql", 1 < users.size());
    }

    @Test
    public void testPersistNewUser() throws Exception {
        User user = userController.getUser();
        String email = "newuser@email.com";
        user.setEmail(email);
        user.setPassword("wahej23S!JKLw");
        user.setType("teacher");

        assertEquals("unpersisted user should not have an ID", 0, user.getId());

        userController.persistNewUser();

        assertTrue("persisted user should have gotten ID", 0 < user.getId());
        User persistedUser = userDao.getUser(1);
        assertEquals(user, persistedUser);
    }

    @Test
    public void testUpdateExistingUser() throws Exception {
        userController.init();
        User user = userController.getUser();
        String newMail = "newEmail@new.com";
        userController.persistNewUser();
        userController.getUser().setEmail(newMail);
        userController.updateExistingUser();
        //assertEquals(userController.getUserByID(user.getId()).getEmail(), newMail);

    }
}

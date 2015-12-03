package no.westerdals.tjoida.Models.Controller;

import no.westerdals.tjoida.Controller.UserControllerManaged;
import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseJPA;
import no.westerdals.tjoida.service.UserService.JPAUserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class UserControllerManagedTest {
    private final static String STD_EMAIL = "test@test.com";
    private final static String STD_PASSWORD = "HUI78913!hawke";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private JPAUserDao persister;
    private CourseJPA coursePersister;
    private UserControllerManaged userController;


    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
        persister = new JPAUserDao(entityManager);
        coursePersister = new CourseJPA(entityManager);
        userController = new UserControllerManaged();
        userController.setPersister(persister);
        userController.setCoursePersister(coursePersister);
        userController.init();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testExample() throws Exception {
        User i = userController.getUser();
        assertTrue(i.getId() < 1);
    }

    @Test
    public void testList() throws Exception {
        assertEquals("There should be 2 prepopulated users in the list because init() is run", 6, userController.getList().size());
    }

    @Test
    public void testAdd() throws Exception {
        User user = userController.getUser();
        assertFalse(userController.getList().contains(user));
        user.setEmail(STD_EMAIL);
        user.setPassword(STD_PASSWORD);
        userController.add();
    }

    @Test
    public void testEdit() throws Exception {
        User user = persister.getUser(100);
        assertNotNull("user is found and set from db", userController.getUser());

        assertNull("currentCourses is null if no user is set", userController.getCurrentCourses());
        assertNull("nonCurrentCourses is null if no user is set", userController.getNonCurrentCourses());
        assertFalse("edit is false before edit() is called", userController.isEdit());
        userController.edit(user);
        assertNotNull("currentCourses is not null after user is set", userController.getCurrentCourses());
        assertNotNull("nonCurrentCourses is null if no user is set", userController.getNonCurrentCourses());
        assertTrue("edit is true after edit() is called", userController.isEdit());
    }

    @Test
    public void testCurrentCourses() {
        User user = persister.getUser(100);
        List<Course> userCourses = user.getCourses();
        int nonCoursesSize = coursePersister.getCourses().size() - userCourses.size();
        userController.setUser(user);

        assertEquals("currentcourses is the same as a users' courses", userCourses.size(), userController.getCurrentCourses().size());
        assertEquals("noncurrentcourses is the count of total courses minus the count of user courses", nonCoursesSize, userController.getNonCurrentCourses().size());
    }

    @Test
    public void testRemoveUserFromCourse() throws Exception {
        User user = persister.getUser(100);
        assertNotNull("user should exist from init", user);
        assertEquals("this user is set to have 4 courses", 4, user.getCourses().size());
        userController.setUser(user);
        userController.removeUserFromCourse(103);
        user = persister.getUser(100);
        assertEquals("user should have 3 courses after removing one", 3, user.getCourses().size());
    }

    @Test
    public void testAddUserToCourse() throws Exception {
        User user = persister.getUser(103);
        assertNotNull(user);
        assertEquals("This user should only be in one course", 1, user.getCourses().size());
        assertEquals("This user should only be in course 101", user.getCourses().get(0).getId(), 101);

        userController.setUser(user);
        userController.addUserToCourse(100);
        user = persister.getUser(user.getId());
        assertEquals("This user should now be in two courses", 2, user.getCourses().size());
    }
}

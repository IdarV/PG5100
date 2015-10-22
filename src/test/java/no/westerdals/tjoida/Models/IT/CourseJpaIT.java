package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CourseJpaIT {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Course");
        entityManager = entityManagerFactory.createEntityManager();

    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testte() throws Exception {
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
        entityManager.persist(course);
        assertTrue(0 < course.getId());


    }
}

package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

/**
 * Created by Cyzla on 15.10.2015.
 */
public class LocationJpaIT {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("Location");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testId() throws Exception {
        Location location = new Location();
        location.setBuilding("ParadiseBuildning");
        location.setRoom("myRoom");

        entityManager.persist(location);
        assertTrue(0 < location.getId());
    }

    @Test
    public void testBuilding() throws Exception {


    }
}

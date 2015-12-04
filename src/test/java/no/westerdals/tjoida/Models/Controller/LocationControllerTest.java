package no.westerdals.tjoida.Models.Controller;

import no.westerdals.tjoida.Controller.LocationController;
import no.westerdals.tjoida.Models.Location;
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

public class LocationControllerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private LocationDAO persister;
    private LocationController locationController;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        persister = new LocationJPA(entityManager);
        locationController = new LocationController();
        locationController.setPersister(persister);
        locationController.init();
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testExample() throws Exception {
        assertTrue(1 > locationController.getLocation().getId());
    }

    @Test
    public void testInitLocation() throws Exception {
        assertNull(locationController.getLocation().getRoom());
        locationController.setSelectedID(100);
        locationController.initLocation();
        assertNotNull(locationController.getLocation().getRoom());
    }

    @Test
    public void testPersistNewLocation() throws Exception {
        assertFalse(0 < locationController.getLocation().getId());
        locationController.getLocation().setRoom("88");
        locationController.getLocation().setBuilding("Building");
        locationController.persistNewLocation();
        assertTrue(0 < locationController.getLocation().getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Location> locationList = locationController.getAll();
        assert(locationList.size() > 0);
    }
}

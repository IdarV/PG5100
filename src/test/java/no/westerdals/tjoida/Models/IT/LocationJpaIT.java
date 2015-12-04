package no.westerdals.tjoida.Models.IT;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.service.LocationService.LocationJPA;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LocationJpaIT {
    private static final String DEFAULT_ROOM = "Room 404";
    private static final String DEFAULT_BUILDING = "Building 1";

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private LocationJPA persister;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        persister = new LocationJPA(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testId() throws Exception {
        Location location = getDefaultLocation();

        assertFalse(0 < location.getId());
        persister.persist(location);
        assertTrue(0 < location.getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Location> locations = persister.getLocations();
        System.out.println("LOCATOPINS:;");
        locations.forEach(System.out::println);
        System.out.println("END");

        assertTrue("Locations should contain multiple locations", 1 < locations.size());

    }

    @Test
    public void testAddAndPersistWithCourses() throws Exception {
        Location location = getDefaultLocation();

        List<Course> courses = new ArrayList<>();
        Course course = new Course();
        course.setName("my_course");
        courses.add(course);
        location.setCourses(courses);

        assertFalse("Location should not persisted", 0 < location.getId());
        assertFalse("Location's courses is not persisted should not have a id", 0 < location.getCourses().get(0).getId());

        persister.persist(location);
        assertTrue("Location should be persisted", 0 < location.getId());
        assertTrue("Location's courses should also be persisted and have a id", 0 < location.getCourses().get(0).getId());
    }

    @Test
    public void testGetRooms() throws Exception {
        List<String> rooms = persister.getRooms();
        System.out.println("ROOM SIZE: " + rooms.size());
        assertTrue("There exists at least 4 rooms in init.sql", 3 < rooms.size());
    }

    private Location getDefaultLocation() {
        Location location = new Location();
        location.setBuilding(DEFAULT_BUILDING);
        location.setRoom(DEFAULT_ROOM);
        return location;
    }
}

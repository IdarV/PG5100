package no.westerdals.tjoida.Models.Basic;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.Models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Cyzla on 26.10.2015.
 */
public class CourseBasics {
    private Course course;

    @Before
    public void setUp() throws Exception {
        course = new Course();
    }

//    @Test
//    public void testCourseBasics() throws Exception {
//        assertNull(course.getLocation());
//        assertEquals(0, course.getId());
//        assertNull(course.getName());
//        assertNull(course.getUsers());
//    }

    @Test
    public void testAddUsers() {
        String email = "someuser@someuser.com";
        List<User> userList = new ArrayList<>();
        User newUser = new User();
        newUser.setEmail(email);
        userList.add(newUser);

        course.setUsers(userList);

        assertEquals("courses should contain users after set", email, course.getUsers().get(0).getEmail());
    }

    @Test
    public void testAddLocation() throws Exception {
        String room = "someRoom";
        Location location  = new Location();
        location.setRoom(room);
        course.setLocation(location);

        assertEquals("course should contain location after being set", room, course.getLocation().getRoom());
    }

    @Test
    public void testAddId() throws Exception {
        int id = 2;
        course.setId(id);

        assertEquals("course should have ID after ID is set", id, course.getId());
    }

    @Test
    public void testAddName() {
        String name = "courseName";
        course.setName(name);

        assertEquals("course should have name after name is set", name, course.getName());
    }
}

package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.LocationService.LocationDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class CourseController {
    private CourseDAO persister;
    private LocationDAO locationPersister;
    private Course course;
    private int selectedID;
    private int selectedLocationID = 0;

    public CourseController() {
    }

    @Inject
    public CourseController(CourseDAO persister, LocationDAO locationPersister) {
        this.persister = persister;
        this.locationPersister = locationPersister;
    }

    @PostConstruct
    public void init() {
        this.course = new Course();
    }

    public void initCourse() {
        this.course = persister.getCourse(selectedID);
        selectedLocationID = course.getLocation().getId();
    }

    public void persistNewCourse() {
        persister.persist(course);
        Location location = locationPersister.getLocation(selectedLocationID);

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nCourse = " + course);
        location.getCourses().add(course);
        locationPersister.update(location);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getFirst() {
        return persister.getCourses().get(0);
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public List<Course> getAll() {
        return persister.getCourses();
    }

    public int getUserSize() {
        return course.getUserSize();
    }

    public String updateExistingCourse(int id) {
        System.out.println();
        Course newCourse = persister.getCourse(id);
        System.out.println("got from db (newCourse): " + newCourse);
        if (course.getName() != null) {
            System.out.println("updating course name: " + course.getName());
            newCourse.setName(course.getName());
        }

        if (course.getLocation() != null) {
            System.out.println("Updating course location: " + course.getLocation());
            newCourse.setLocation(course.getLocation());
        }

        if (course.getUsers() != null && course.getUsers().size() > 0) {
            System.out.println("updating course users: " + course.getUsers());
            newCourse.setUsers(course.getUsers());
        }

        if (selectedLocationID != 0) {
            newCourse.setLocation(locationPersister.getLocation(selectedLocationID));
        }

        persister.update(newCourse);
        return "/course/course-index.xhtml?faces-redirect=true";
    }

    public int getSelectedLocationID() {
        return selectedLocationID;
    }

    public void setSelectedLocationID(int selectedLocationID) {
        this.selectedLocationID = selectedLocationID;
    }

    public String getLocation() {
        return course.getLocation().toString();
    }

    public List<Location> getLocations() {
        return locationPersister.getLocations();
    }


}

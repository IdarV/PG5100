package no.westerdals.tjoida.service.CourseService;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Location;

import java.util.List;


public interface CourseDAO {
    List getUsers();
    void persist(Course course);
    String getName();
    void deleteCourse(Course course);
    void close();
}

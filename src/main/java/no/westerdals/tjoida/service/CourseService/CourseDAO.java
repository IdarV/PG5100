package no.westerdals.tjoida.service.CourseService;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;

import java.util.List;


public interface CourseDAO {
    Course getCourse(int id);

    List<User> getUsers(Course course);

    void persist(Course course);

    void deleteCourse(Course course);

    void close();

    Course update(Course course);

    List<Course> getCourses();

    List<Course> getNonCurrentCourses(User user);

    void removeUser(User user);
}

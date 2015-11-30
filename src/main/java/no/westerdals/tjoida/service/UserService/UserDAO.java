package no.westerdals.tjoida.service.UserService;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;

import java.util.List;

public interface UserDAO {
    List names();

    User update(User user);

    List<User> getUsers();

    User getUser(int id);

    int deleteUser(int id);

    List<Course> getCourses();

    void close();

    void persist(User user);

    void removeFromCourse(int userID, int courseID);
}

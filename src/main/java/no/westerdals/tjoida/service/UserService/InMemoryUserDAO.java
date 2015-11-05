package no.westerdals.tjoida.service.UserService;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cyzla on 17.09.2015.
 */

public class InMemoryUserDAO implements UserDAO {

    private List<User> users = new ArrayList<>();

    public InMemoryUserDAO() {
        users.add(new User(1, "test1@local.com", "monkey", "Teacher"));
        users.add(new User(2, "test2@local.com", "mickael", "Student"));
        users.add(new User(3, "test3@local.com", "someone", "Student"));
        users.add(new User(4, "test4@local.com", "slim", "Student"));
        users.add(new User(5, "test5@local.com", "shady", "Student"));
    }

    @Override
    public List<String> names() {
        System.out.println("In Memory");
        return users.stream().map(User::getEmail).collect(Collectors.toList());
    }

    @Override
    // WOW this actually worked
    public User update(User user_new) {
        User updated_user = null;
        for (User user : users) {
            if (user.getId() == user_new.getId()) {
                user = user_new;
                updated_user = user;
            }
        }
        return updated_user;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        Course c = new Course();
        c.setName("c");
        courses.add(c);
        return courses;
    }

    @Override
    public int deleteUser(int id) {
        if (users.size() > 0) {
            users.remove(users.size() - 1);
            return 1;
        }
        return 0;
    }

    @Override
    public void close() {
        //
    }
}

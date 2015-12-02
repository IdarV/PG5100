package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Copy of
 * http://stackoverflow.com/questions/3180400/recommended-jsf-2-0-crud-frameworks
 * <p>
 * Read
 * http://www.tutorialspoint.com/jsf/jsf_managed_beans.htm
 */

@ManagedBean
@ViewScoped
public class UserControllerManaged implements Serializable {
    @EJB
    private UserDAO persister;

    @EJB
    private CourseDAO coursePersister;

    private List<User> list;
    private List<Course> currentCourses;
    private User user = new User();
    private boolean edit;

    public UserControllerManaged() {
    }

    @PostConstruct
    public void init() {
        list = persister.getUsers();

    }

    public void add() {
        user.setId(list.isEmpty() ? 1 : list.get(list.size() - 1).getId() + 1);
        list.add(user);
        user = new User();
    }

    public void edit(User user) {
        this.user = user;
        currentCourses = user.getCourses();
        edit = true;
    }

    public void save() {
        persister.update(user);
        user = new User();
        edit = false;
    }

    public void delete(User user) {
        list.remove(user);
    }

    public List<User> getList() {
        return list;
    }

    public User getUser() {
        return user;
    }

    public boolean isEdit() {
        return edit;
    }

    public List<Course> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(List<Course> currentCourses) {
        this.currentCourses = currentCourses;
    }

    public void removeUserFromCourse(int id) {
        Course course = coursePersister.getCourse(id);
        List<User> courseUsers = course.getUsers();

        User userToDelete = courseUsers.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);

        if (userToDelete != null) {

            courseUsers.remove(userToDelete);
            course.setUsers(courseUsers);

            coursePersister.update(course);
            user = persister.getUser(user.getId());
            currentCourses = user.getCourses();
        }
    }

    public UserDAO getPersister() {
        return persister;
    }

    public void setPersister(UserDAO persister) {
        this.persister = persister;
    }

    public CourseDAO getCoursePersister() {
        return coursePersister;
    }

    public void setCoursePersister(CourseDAO coursePersister) {
        this.coursePersister = coursePersister;
    }
}

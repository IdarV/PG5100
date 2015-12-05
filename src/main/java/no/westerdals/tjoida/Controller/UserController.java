package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.Models.UserType;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
public class UserController implements Serializable {
    @EJB
    private UserDAO persister;

    @EJB
    private CourseDAO coursePersister;

    private List<User> list;
    private List<Course> currentCourses;
    private List<Course> nonCurrentCourses;
    private User user = new User();
    private boolean edit;

    private int selectedID;
    private String lastPassword;
    private UserType currentUserType;

    public UserController() {
    }

    @PostConstruct
    public void init() {
        list = persister.getUsers();
    }

    public void initUser() {
        user = persister.getUser(selectedID);
        lastPassword = user.getPassword();
        currentCourses = user.getCourses();
        currentUserType = user.getUserType();
    }


    public void add() {
        user = persister.update(user);
        list.add(user);
        user = new User();
        user.setUserType(UserType.STUDENT);
        edit = false;
    }

    public void edit(User user) {
        this.user = user;
        currentCourses = persister.getUser(user.getId()).getCourses();
        nonCurrentCourses = coursePersister.getNonCurrentCourses(user);
        edit = true;
    }

    public void save() {
        user = persister.update(user);
        user = new User();
        user.setUserType(UserType.STUDENT);
        edit = false;
    }

    public void delete(User user) {
        persister.deleteUser(user.getId());
        list.remove(user);
    }

    public List<User> getList() {
        return list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        updateUserInfo();
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

    public List<Course> getNonCurrentCourses() {
        return nonCurrentCourses;
    }

    public void setNonCurrentCourses(List<Course> nonCurrentCourses) {
        this.nonCurrentCourses = nonCurrentCourses;
    }

    public void removeUserFromCourse(int courseId) {
        Course course = coursePersister.getCourse(courseId);
        List<User> courseUsers = course.getUsers();

        User userToDelete = courseUsers.stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);

        if (userToDelete != null) {

            courseUsers.remove(userToDelete);
            course.setUsers(courseUsers);
            user.getCourses().remove(course);
            coursePersister.update(course);

            updateUserInfo();
        }
    }

    public void addUserToCourse(int courseId) {
        Course course = coursePersister.getCourse(courseId);
        List<User> courseUsers = course.getUsers();
        courseUsers.add(user);
        course.setUsers(courseUsers);
        user.getCourses().add(course);
        coursePersister.update(course);

        updateUserInfo();
    }

    private void updateUserInfo() {
        if (user.getId() > 0) {
            currentCourses = persister.getUser(user.getId()).getCourses();
            nonCurrentCourses = coursePersister.getNonCurrentCourses(persister.getUser(user.getId()));
        }
    }

    public void setPersister(UserDAO persister) {
        this.persister = persister;
    }

    public void setCoursePersister(CourseDAO coursePersister) {
        this.coursePersister = coursePersister;
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public List<UserType> getUserTypes() {
        List<UserType> list = new ArrayList<>();
        list.add(UserType.STUDENT);
        list.add(UserType.TEACHER);
        return list;

    }
}

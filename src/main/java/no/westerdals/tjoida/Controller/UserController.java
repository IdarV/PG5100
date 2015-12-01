package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class UserController{
    private UserDAO persister;
    private User user;
    private int selectedID = 103;
    private String lastPassword;

    // ///

    public List<Course> getCurrentCourses() {

        System.out.println("(getcurrentCourse = " + currentCourse + ")");
        return currentCourses;
    }

    public void setCurrentCourses(List<Course> currentCourses) {

        System.out.println("(setcurrentCourse = " + currentCourse + ")");
        this.currentCourses = currentCourses;
    }

    private List<Course> currentCourses;

    // .

    private int currentCourse;

    public int getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(int currentCourse) {
        this.currentCourse = currentCourse;
    }
    // //



    @Inject
    public UserController(UserDAO persister) {
        this.persister = persister;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
    }

    public void initUser() {
        System.out.println("initUser()");
        user = persister.getUser(selectedID);
        lastPassword = user.getPassword();
    }

    public User getFirst() {
        return persister.getUsers().get(0);
    }

    public List<User> getAll() {
        return persister.getUsers();
    }

    public User getUser() {
        return user;
    }

    public List<Course> getUserCourses() {
        currentCourses =
                persister.getUser(selectedID).getCourses();
        return currentCourses;
    }

    public void setCurrentUserToSelectedId() {
        user = persister.getUser(selectedID);
    }

    public String persistNewUser() {
        persister.persist(user);
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateExistingUser(int id) {
        System.out.println("User in updateUser = " + user);
        User updateUser = persister.getUser(id);
        System.out.println("updateuser in updateUser = " + user);

        updateUser.setType(user.getType());
        updateUser.setEmail(user.getEmail());
        if (user.getPassword() != null) {

            updateUser.setPassword(user.getPassword());
        }
        System.out.println("Before: " + user.toString());
        System.out.println("After: " + updateUser.toString());
        persister.update(updateUser);

        return "/user/user-index.xhtml?faces-redirect=true";
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public void removeUserFromCourse(int courseID){
        persister.removeFromCourse(selectedID, courseID);
        System.out.println("REMOVEUSERFROMCOURSE withd SelectedID=" + selectedID + " and param = " + courseID) ;
        user = persister.getUser(selectedID);
    }
}

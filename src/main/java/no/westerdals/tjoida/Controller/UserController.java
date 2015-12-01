package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class UserController{
    private UserDAO persister;
    private User user;
    private int selectedID = 103;
    private String lastPassword;
    private List<Course> currentCourses;

    public List<Course> getCurrentCourses() {
        return currentCourses;
    }

    public void setCurrentCourses(List<Course> currentCourses) {
        this.currentCourses = currentCourses;
    }

    @Inject
    public UserController(UserDAO persister) {
        this.persister = persister;
    }

    @PostConstruct
    public void init() {
        this.user = new User();
        currentCourses = new ArrayList<>();
    }

    public void initUser() {
        System.out.println("initUser()");
        user = persister.getUser(selectedID);
        lastPassword = user.getPassword();
        currentCourses = user.getCourses();
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
        return  persister.getUser(selectedID).getCourses();
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

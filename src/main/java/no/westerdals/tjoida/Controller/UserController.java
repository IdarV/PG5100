package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class UserController {
    private UserDAO persister;
    private User user;
    private int selectedID;
    private String lastPassword;

    @Inject
    public UserController(UserDAO persister){
        this.persister = persister;
    }

    @PostConstruct
    public void init(){
        this.user = new User();
    }

    public void initUser(){
        user = persister.getUser(selectedID);
        lastPassword = user.getPassword();
    }

    public User getFirst(){
        return persister.getUsers().get(0);
    }

    public List<User> getAll(){
        return persister.getUsers();
    }

    public User getUser(){
        return user;
    }

    public List<Course> getUserCourses(){
        return user.getCourses();
    }

    public void setCurrentUserToSelectedId() {
        user =  persister.getUser(selectedID);
    }

    public String persistNewUser(){
        persister.persist(user);
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateExistingUser(){
//        System.out.println("got type " + type);
        System.out.println("User in updateUser = " + user);
        User updateUser = persister.getUser(selectedID);
        System.out.println("updateuser in updateUser = " + user);

        updateUser.setEmail(user.getEmail());
        updateUser.setType(user.getType());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
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
}

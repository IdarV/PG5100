package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.JPAUserDao;
import no.westerdals.tjoida.service.UserService.UserDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cyzla on 29.10.2015.
 */
@Model
public class UserController {
    private UserDAO persister;
    private User user;

    private int selectedID;

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

    public void setCurrentUserToSelectedId() {
        user =  persister.getUser(selectedID);
    }

    public String persistNewUser(){
        persister.persist(user);
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateExistingUser(){
        persister.update(user);

        return "/user/user-index.xhtml?faces-redirect=true";
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }
}

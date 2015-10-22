package no.westerdals.tjoida;

import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserService.UserDAO;
import no.westerdals.tjoida.service.UserService.UserQualifier;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Cyzla on 17.09.2015.
 */
public class MyService {

    @Inject @UserQualifier
    private UserDAO userDAO;

    public void printUserNames(){
        System.out.println(userDAO.names());
    }

    public List<User> getUsers(){
        return userDAO.getUsers();
    }

    public User getUser(int index) {
        return userDAO.getUser(index);
    }

    public User updateUser(User user){
        return userDAO.update(user);
    }

    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

    public void close(){
        userDAO.close();
    }

}

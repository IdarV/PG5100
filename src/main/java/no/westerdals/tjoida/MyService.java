package no.westerdals.tjoida;

import no.westerdals.tjoida.Models.User;
import no.westerdals.tjoida.service.UserDAO;
import no.westerdals.tjoida.service.UserQualifier;

import javax.inject.Inject;
import javax.inject.Qualifier;
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

    public int updateUser(int userId, String column, String value){
        return userDAO.update(userId, column, value);
    }

    public int deleteUser(int id) {
        return userDAO.deleteUser(id);
    }

}

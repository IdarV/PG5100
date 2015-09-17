package no.westerdals.tjoida.service;

import no.westerdals.tjoida.Models.User;

import java.util.List;

/**
 * Created by Cyzla on 17.09.2015.
 */
public interface UserDAO {
    List<String> names();
    int update(int userId, String column, String value);
    List<User> getUsers();

}

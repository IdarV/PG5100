package no.westerdals.tjoida.service;

import no.westerdals.tjoida.Models.User;

import java.util.List;

public interface UserDAO {
    List<String> names();
    int update(int userId, String column, String value);
    List<User> getUsers();
    User getUser(int id);
    int deleteUser(int id);
}

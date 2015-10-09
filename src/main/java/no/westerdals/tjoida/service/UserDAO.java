package no.westerdals.tjoida.service;

import no.westerdals.tjoida.Models.User;

import java.util.List;

public interface UserDAO {
    List names();
    User update(User user);
    List<User> getUsers();
    User getUser(int id);
    int deleteUser(int id);
    void close();
}

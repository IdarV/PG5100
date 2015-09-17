package no.westerdals.tjoida.service;

import no.westerdals.tjoida.Models.Type;
import no.westerdals.tjoida.Models.User;

import javax.enterprise.inject.Alternative;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cyzla on 17.09.2015.
 */
@Alternative
public class H2UserDao implements UserDAO {
    private static final String URL = "jdbc:h2:tcp://localhost:9092/~/test";
    private static final String SQL_SELECT_ALL = "SELECT id, email, name, type FROM USERS;";
    private String username, password;

    public H2UserDao() {
        this.username = "sa";
        this.password = "sa";
    }

    @Override
    public List<String> names() {
        List<User> users = new ArrayList<>();
        users = executeQuery(SQL_SELECT_ALL);
        return users.stream().map(User::getName).collect(Collectors.toList());
    }

    @Override
    public int update(int userId, String column, String value) {
        String sql = String.format("UPDATE USERS SET %s = '%s' WHERE ID=%d", column, value, userId);
        return executeUpdate(sql);
    }

    @Override
    public List<User> getUsers() {
        return executeQuery(SQL_SELECT_ALL);
    }

    private List<User> executeQuery(String sql) {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            Class.forName("org.h2.Driver");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), Type.valueOf(resultSet.getString(4).toUpperCase())));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    private int executeUpdate(String sql) {
        int result = 0;
        try (Connection connection = DriverManager.getConnection(URL, username, password)) {
            Class.forName("org.h2.Driver");
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}

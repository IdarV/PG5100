package no.westerdals.tjoida.service;

import javax.enterprise.inject.Alternative;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyzla on 17.09.2015.
 */
@Alternative
public class H2UserDao implements UserDAO{
    private String username, password;
    public H2UserDao() {
        this.username = "sa";
        this.password = "sa";
    }

    @Override
    public List<String> names()  {
        List<String> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost:9092/~/test", username, password)) {
            Class.forName("org.h2.Driver");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT name FROM USERS ;");
            while (resultSet.next()) {
                users.add(resultSet.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return users;
    }
}

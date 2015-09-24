package no.westerdals.tjoida.service;

import no.westerdals.tjoida.Models.Type;
import no.westerdals.tjoida.Models.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cyzla on 17.09.2015.
 */

public class InMemoryUserDAO implements UserDAO{

    private List<User> users = new ArrayList<>();

    public InMemoryUserDAO() {
        users.add(new User(1, "test1@local.com", "monkey", Type.TEACHER));
        users.add(new User(2, "test2@local.com", "mickael", Type.STUDENT));
        users.add(new User(3, "test3@local.com", "someone", Type.STUDENT));
        users.add(new User(4, "test4@local.com", "slim", Type.STUDENT));
        users.add(new User(5, "test5@local.com", "shady", Type.STUDENT));
    }

    @Override
    public List<String> names() {
        System.out.println("In Memory");
        return users.stream().map(User::getPassword).collect(Collectors.toList());
}

    @Override
    // WOW this actually worked
    public int update(int userId, String column, String value) {
        for (User user : users) {
            if (user.getId() == userId) {
                try {
                    Class[] paramTypes = new Class[1];
                    paramTypes[0]= column.getClass();
                    Method method = user.getClass().getMethod(("set" + Character.toUpperCase(column.charAt(0)) + column.substring(1)), paramTypes);
                    method.invoke(user, value);
                   // System.out.println(user.toString());
                    return 1;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public int deleteUser(int id) {
        if(users.size() > 0){
            users.remove(users.size()-1);
            return 1;
        }
        return 0;
    }
}

package no.westerdals.tjoida.service;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyzla on 17.09.2015.
 */
@Alternative
public class InMemoryUserDAO implements UserDAO{

    private List<String> users = new ArrayList<>();

    public InMemoryUserDAO() {
        users.add("Idar");
        users.add("Freija");
        users.add("Selma");
        users.add("Riley");
    }

    @Override
    public List<String> names() {
        System.out.println("In Memory");
        return users;
    }
}

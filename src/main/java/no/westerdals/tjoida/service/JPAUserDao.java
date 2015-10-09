package no.westerdals.tjoida.service;


import no.westerdals.tjoida.Models.User;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cyzla on 08.10.2015.
 */
@UserQualifier
public class JPAUserDao implements UserDAO{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public JPAUserDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<String> names() {
        //ArrayList<User> users = entityManager.
        return null;
    }

    @Override
    public int update(int userId, String column, String value) {
        return 0;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(int id) {
        User a=  entityManager.find(User.class, id);
        close();
        return a;
    }

    @Override
    public int deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        return entityManager.find(User.class, id) == null ? 1 : 0;
    }

    public void close(){
        entityManager.close();
        entityManagerFactory.close();
    }
}

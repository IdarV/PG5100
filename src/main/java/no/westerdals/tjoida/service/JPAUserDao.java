package no.westerdals.tjoida.service;


import no.westerdals.tjoida.Models.User;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cyzla on 08.10.2015.
 */
@UserQualifier
public class JPAUserDao implements UserDAO {
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    EntityManager entityManager;

    public JPAUserDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> names() {
        return entityManager.createQuery("SELECT 'email' FROM User").getResultList();
    }

    @Override
    /**
     * TODO: entetymanager.merge() ?
     */
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT e FROM User e").getResultList();
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public int deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        return entityManager.find(User.class, id) == null ? 1 : 0;
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

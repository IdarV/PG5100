package no.westerdals.tjoida.service.UserService;


import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class JPAUserDao implements UserDAO {
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext(name = "Egentreningprosjekt")
    EntityManager entityManager;

    public JPAUserDao() {
    }

    public JPAUserDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> names() {
        return entityManager.createQuery("SELECT 'email' FROM User").getResultList();
    }

    @Override
    public User update(User user) {
        System.out.println("updating user " + user);
        entityManager.merge(user);
        return user;
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
    @SuppressWarnings("unchecked")
    public List<Course> getCourses() {
        return entityManager.createQuery("SELECT courses FROM User").getResultList();
    }

    @Override
    public void persist(User user) {
        entityManager.persist(user);
    }

    @Override
    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }
}

package no.westerdals.tjoida.service.UserService;


import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Cyzla on 08.10.2015.
 */
//@UserQualifier
@Stateless
@LocalBean
public class JPAUserDao implements UserDAO {
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext(name = "User")
    EntityManager entityManager;

    public JPAUserDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("User");
        entityManager = entityManagerFactory.createEntityManager();
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
    @SuppressWarnings("unchecked")
    public List<Course> getCourses() {
        return entityManager.createQuery("SELECT courses FROM User").getResultList();
    }

    public void persist(User user){
        entityManager.persist(user);
    }

    @Override
    public void close() {
        entityManagerFactory.close();
        entityManager.close();
    }

//    @AroundInvoke
//    private Object intercept(InvocationContext ic) throws Exception {
//        entityManager.getTransaction().begin();
//        try {
//            return ic.proceed();
//        } finally {
//            entityManager.getTransaction().commit();
//        }
//    }
}

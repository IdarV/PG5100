package no.westerdals.tjoida.service.CourseService;

import no.westerdals.tjoida.Models.Location;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Cyzla on 22.10.2015.
 */

@CourseQualifier
public class CourseJPA implements CourseDAO{
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    EntityManager entityManager;

    public CourseJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Course");
        entityManager = entityManagerFactory.createEntityManager();

    }

    @Override
    public List getUsers() {
        return entityManager.createQuery("SELECT 'users' FROM Course").getResultList();
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Location update() {
        return null;
    }

    @Override
    public int deleteCourse() {
        return 0;
    }

    @Override
    public void close() {

    }

    @AroundInvoke
    private Object intercept(InvocationContext ic) throws Exception {
        entityManager.getTransaction().begin();
        try {
            return ic.proceed();
        } finally {
            entityManager.getTransaction().commit();
        }
    }
}

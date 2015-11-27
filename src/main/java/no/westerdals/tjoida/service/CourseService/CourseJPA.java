package no.westerdals.tjoida.service.CourseService;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.Models.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@LocalBean
@Stateless
public class CourseJPA implements CourseDAO{
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    EntityManager entityManager;

    public CourseJPA() {
    }

    public CourseJPA(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Course getCourse(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers(Course course) {
        return entityManager.createQuery("SELECT users from Course ").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Course> getCourses(){
        return entityManager.createQuery("SELECT e FROM Course e").getResultList();
    }

    @Override
    public void persist(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course update(Course course) {
        course = entityManager.merge(course);
        return course;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(course);
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

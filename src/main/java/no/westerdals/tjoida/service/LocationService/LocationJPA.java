package no.westerdals.tjoida.service.LocationService;

import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.Models.User;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Cyzla on 15.10.2015.
 */
@LocationQualifier
public class LocationJPA implements LocationDAO{
    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public LocationJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Location");
        entityManager = entityManagerFactory.createEntityManager();
    }


    @Override
    public List<String> getRooms() {
        return null;
    }

    @Override
    public List<String> getBuildings() {
        return null;
    }

    @Override
    public Location update(User user) {
        return null;
    }

    @Override
    public Location getLocation(int id) {
        return null;
    }

    @Override
    public int deleteLocation(int id) {
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

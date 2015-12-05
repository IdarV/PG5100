package no.westerdals.tjoida.service.LocationService;

import no.westerdals.tjoida.Models.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LocationJPA implements LocationDAO {
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext(name = "Egentreningprosjekt")
    EntityManager entityManager;

    public LocationJPA() {
    }


    public LocationJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Location> getLocations() {
        return entityManager.createQuery("SELECT e FROM Location e", Location.class).getResultList();
    }

    @Override
    public List<String> getRooms() {
        return entityManager.createQuery("SELECT room FROM Location", String.class).getResultList();
    }

    @Override
    public void persist(Location location) {
        entityManager.persist(location);
    }

    @Override
    public List<String> getBuildings() {
        return null;
    }

    @Override
    public Location update(Location location) {
        return entityManager.merge(location);
    }

    @Override
    public Location getLocation(int id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    public void deleteLocation(Location location) {
        entityManager.remove(location);
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

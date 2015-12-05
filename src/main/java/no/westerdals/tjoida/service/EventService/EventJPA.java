package no.westerdals.tjoida.service.EventService;


import no.westerdals.tjoida.Models.Event;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EventJPA implements EventDAO {
    EntityManagerFactory entityManagerFactory;
    @PersistenceContext(name = "Egentreningprosjekt")
    EntityManager entityManager;

    public EventJPA() {
    }

    public EventJPA(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Event getEvent(int id) {
        return entityManager.find(Event.class, id);
    }

    @Override
    public List<Event> getEvents() {
        return entityManager.createQuery("SELECT e FROM Event e", Event.class).getResultList();
    }

    @Override
    public void persist(Event event) {
        entityManager.persist(event);
    }

    @Override
    public void deleteEvent(Event event) {
        entityManager.remove(event);
    }

    @Override
    public Event update(Event event) {
        return entityManager.merge(event);
    }

    @Override
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}

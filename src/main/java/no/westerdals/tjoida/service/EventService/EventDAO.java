package no.westerdals.tjoida.service.EventService;


import no.westerdals.tjoida.Models.Event;

import java.util.List;

public interface EventDAO {
    Event getEvent(int id);

    List<Event> getEvents();

    void persist(Event event);

    void deleteEvent(Event event);

    void close();

    Event update(Event event);
}

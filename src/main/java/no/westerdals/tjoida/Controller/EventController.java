package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Event;
import no.westerdals.tjoida.service.EventService.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class EventController {
    private EventDAO persister;
    private Event event;
    private int selectedID;

    public EventController() {
    }

    @Inject
    public EventController(EventDAO persister){
        this.persister = persister;
    }

    @PostConstruct
    public void init(){
        event = new Event();
    }

    public void initEvent(){
        event = persister.getEvent(selectedID);
    }

    public void persistNewEvent(){
        persister.persist(event);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }
}

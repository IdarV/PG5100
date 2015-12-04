package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Course;
import no.westerdals.tjoida.Models.Event;
import no.westerdals.tjoida.Models.EventType;
import no.westerdals.tjoida.service.CourseService.CourseDAO;
import no.westerdals.tjoida.service.EventService.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class EventController {
    private EventDAO persister;
    private CourseDAO coursePersister;
    private Event event;
    private int selectedID = 100;
    private int selectedCourseID = 0;

    public EventController() {
    }

    @Inject
    public EventController(EventDAO persister, CourseDAO coursePersister){
        this.persister = persister;
        this.coursePersister = coursePersister;
    }

    @PostConstruct
    public void init(){
        event = new Event();
    }

    public void initEvent(){
        event = persister.getEvent(selectedID);
        selectedCourseID = event.getCourse().getId() == 0 ? 0 : event.getCourse().getId();
    }

    public void persistNewEvent(){
        persister.persist(event);
    }

    public Event getEvent() {
        return event;
    }

    public List<Event> getAll(){
        return persister.getEvents();
    }

    public void updateEvent(){
        Course selectedCourse = coursePersister.getCourse(selectedCourseID);
        event.setCourse(selectedCourse);
        event = persister.update(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<EventType> getEventTypes(){
        List<EventType> eventTypes = new ArrayList<>();
        eventTypes.add(EventType.LECTURE);
        eventTypes.add(EventType.PRACTICE);
        return eventTypes;
    }

    public List<Course> getCourses(){
        return coursePersister.getCourses();
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public int getSelectedCourseID() {
        return selectedCourseID;
    }

    public void setSelectedCourseID(int selectedCourseID) {
        this.selectedCourseID = selectedCourseID;
    }
}

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Model
public class EventController {
    private EventDAO persister;
    private CourseDAO coursePersister;
    private Event event;

    private int selectedID = 100;
    private int selectedCourseID = 0;
    private int selectedStartHour;
    private int selectedStartMinute;
    private int selectedEndHour;
    private int selectedEndMinute;
    private Date finalEndDate;
    private Date finalStartDate;

    public EventController() {
    }

    @Inject
    public EventController(EventDAO persister, CourseDAO coursePersister) {
        this.persister = persister;
        this.coursePersister = coursePersister;
    }

    @PostConstruct
    public void init() {
        finalStartDate = new Date();
        finalEndDate = new Date();
        selectedStartHour = selectedEndHour = 12;
        selectedStartMinute = selectedEndMinute = 30;
        event = new Event();
    }

    public void initEvent() {
        event = persister.getEvent(selectedID);
        finalStartDate = event.getStartTime();
        finalEndDate = event.getEndTime();

        selectedStartHour = getHourFromDate(finalStartDate);
        selectedEndHour = getHourFromDate(finalEndDate);
        selectedStartMinute = getMinuteFromDate(finalStartDate);
        selectedEndMinute = getMinuteFromDate(finalEndDate);

        selectedCourseID = event.getCourse().getId() == 0 ? 0 : event.getCourse().getId();
    }

    public void persistNewEvent() {
        parseDates();
        persister.persist(event);
    }

    public Event getEvent() {
        return event;
    }

    public List<Event> getAll() {
        return persister.getEvents();
    }

    public void updateEvent() {
        parseDates();
        Course selectedCourse = coursePersister.getCourse(selectedCourseID);

        event.setCourse(selectedCourse);
        event.setStartTime(finalStartDate);
        event.setEndTime(finalEndDate);
        event = persister.update(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<EventType> getEventTypes() {
        List<EventType> eventTypes = new ArrayList<>();
        eventTypes.add(EventType.LECTURE);
        eventTypes.add(EventType.PRACTICE);
        return eventTypes;
    }

    // Cheap solution of setting time
    public void parseDates() {
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();

        calendarStart.setTime(finalStartDate);
        calendarStart.set(Calendar.HOUR_OF_DAY, selectedStartHour);
        calendarStart.set(Calendar.MINUTE, selectedStartMinute);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);
        finalStartDate = calendarStart.getTime();

        calendarEnd.setTime(finalEndDate);
        calendarEnd.set(Calendar.HOUR_OF_DAY, selectedEndHour);
        calendarEnd.set(Calendar.MINUTE, selectedEndMinute);
        calendarEnd.set(Calendar.SECOND, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);
        finalEndDate = calendarEnd.getTime();
    }

    public List<Course> getCourses() {
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

    public int getSelectedStartHour() {
        return selectedStartHour;
    }

    public void setSelectedStartHour(int selectedStartHour) {
        this.selectedStartHour = selectedStartHour;
    }

    public int getSelectedStartMinute() {
        return selectedStartMinute;
    }

    public void setSelectedStartMinute(int selectedStartMinute) {
        this.selectedStartMinute = selectedStartMinute;
    }

    public int getSelectedEndHour() {
        return selectedEndHour;
    }

    public void setSelectedEndHour(int selectedEndHour) {
        this.selectedEndHour = selectedEndHour;
    }

    public int getSelectedEndMinute() {
        return selectedEndMinute;
    }

    public void setSelectedEndMinute(int selectedEndMinute) {
        this.selectedEndMinute = selectedEndMinute;
    }

    public Date getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(Date finalEndDate) {
        this.finalEndDate = finalEndDate;
    }

    public Date getFinalStartDate() {
        return finalStartDate;
    }

    public void setFinalStartDate(Date finalStartDate) {
        this.finalStartDate = finalStartDate;
    }

    private int getHourFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    private int getMinuteFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
}

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
import java.util.Date;
import java.util.List;

@Model
public class EventController {
    private EventDAO persister;
    private CourseDAO coursePersister;
    private Event event;

    private int selectedID = 100;
    private int selectedCourseID = 0;

    private String selectedStartDate;
    private String selectedEndDate;
    private String selectedStartHour;
    private String selectedStartMinute;
    private String selectedEndHour;
    private String selectedEndMinute;
    private String finalEndDate;
    private String finalStartDate;

    private List<String> months = new ArrayList<>();

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
        initMonths();
    }

    public void initEvent(){
        event = persister.getEvent(selectedID);
        selectedCourseID = event.getCourse().getId() == 0 ? 0 : event.getCourse().getId();
        initMonths();
    }

    public void persistNewEvent(){
        event.setStartTime(selectedStartDate);
        event.setEndTime(selectedEndDate);
        persister.persist(event);
    }

    public Event getEvent() {
        return event;
    }

    public List<Event> getAll(){
        return persister.getEvents();
    }

    public void updateEvent(){
        parseDates();
        Course selectedCourse = coursePersister.getCourse(selectedCourseID);
        event.setCourse(selectedCourse);
        event.setStartTime(selectedStartDate);
        event.setEndTime(selectedEndDate);
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

    public void parseDates(){
        String[] selectedStartArray = selectedStartDate.split("\\s+");
        selectedStartArray[3] = selectedStartHour + ":" + selectedStartMinute + ":00";

        selectedStartDate = "";
        for(String s : selectedStartArray){
            selectedStartDate += (s + " ");
        }

        String[] selectedEndArray = selectedEndDate.split("\\s+");
        selectedEndArray[3] = selectedEndHour + ":" + selectedEndMinute + ":00";

        selectedEndDate = "";
        for(String s : selectedEndArray){
            selectedEndDate += (s + " ");
        }
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

    public String getSelectedStartHour() {
        return selectedStartHour;
    }

    public void setSelectedStartHour(String selectedStartHour) {
        this.selectedStartHour = selectedStartHour;
    }

    public String getSelectedStartMinute() {
        return selectedStartMinute;
    }

    public void setSelectedStartMinute(String selectedStartMinute) {
        this.selectedStartMinute = selectedStartMinute;
    }

    public String getSelectedEndHour() {
        return selectedEndHour;
    }

    public void setSelectedEndHour(String selectedEndHour) {
        this.selectedEndHour = selectedEndHour;
    }

    public String getSelectedEndMinute() {
        return selectedEndMinute;
    }

    public void setSelectedEndMinute(String selectedEndMinute) {
        this.selectedEndMinute = selectedEndMinute;
    }

    public String getSelectedStartDate() {
        return selectedStartDate;
    }

    public void setSelectedStartDate(String selectedStartDate) {
        this.selectedStartDate = selectedStartDate;
    }

    public String getSelectedEndDate() {
        return selectedEndDate;
    }

    public void setSelectedEndDate(String selectedEndDate) {
        this.selectedEndDate = selectedEndDate;
    }

    public String getFinalEndDate() {
        return finalEndDate;
    }

    public void setFinalEndDate(String finalEndDate) {
        this.finalEndDate = finalEndDate;
    }

    public String getFinalStartDate() {
        return finalStartDate;
    }

    public void setFinalStartDate(String finalStartDate) {
        this.finalStartDate = finalStartDate;
    }

    public void initMonths(){
        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");
    }
}

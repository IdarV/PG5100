package no.westerdals.tjoida.Models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@SecondaryTable(name = "Event_Details")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull(message = "Event type cannot be empty")
    @Enumerated(EnumType.STRING)
    EventType eventType;

    @Size(min = 5, max = 25, message = "Title must be between 5 and 25 characters")
    private String title;

    @Size(max = 100, message = "Description cannot be more than 100 characters long")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COURSE")
    @Valid
    private Course course;

    @Column(table = "Event_Details")
    @NotNull(message = "Event must have a start date.")
    private Date startTime;

    @Column(table = "Event_Details")
    @NotNull(message = "Event must have a end date")
    private Date endTime;

    public Event() {
    }

    public Event(EventType eventType, String title, String description, Course course, Date startTime, Date endTime) {
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType type) {
        this.eventType = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

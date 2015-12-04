package no.westerdals.tjoida.Models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@SecondaryTable(name="Event_Details")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    EventType type;

    @Size(min = 5, max = 25)
    private String title;

    @Size(max = 100)
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_COURSE")
    @Valid
    private Course course;

    @Column(table = "Event_Details")
    @NotNull
    private String startTime;

    @Column(table = "Event_Details")
    @NotNull
    private String endTime;

    public Event() {
    }

    public Event(EventType type, String title, String description, Course course, String startTime, String endTime) {
        this.type = type;
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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

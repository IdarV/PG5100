package no.westerdals.tjoida.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    String room;
    @NotNull
    String building;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_LOCATION")
    List<Course> courses;

    public Location() {
    }

    public Location(String room, String building) {
        this.room = room;
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Location{" +
                "room='" + room + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}

package no.westerdals.tjoida.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    String room;
    @NotNull
    String building;

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

    @Override
    public String toString() {
        return "Location{" +
                "room='" + room + '\'' +
                ", building='" + building + '\'' +
                '}';
    }
}

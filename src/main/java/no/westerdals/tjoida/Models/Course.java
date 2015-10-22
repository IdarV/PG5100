package no.westerdals.tjoida.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Cyzla on 22.10.2015.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NotNull
    private String name;
    @OneToMany
    @JoinColumn
    private List<User> users;
    @OneToOne
    @JoinColumn
    private Location location;

    public Course() {
    }

    public Course(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Course{" +
                "users=" + users +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

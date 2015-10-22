package no.westerdals.tjoida.service.CourseService;

import no.westerdals.tjoida.Models.Location;

import java.util.List;


public interface CourseDAO {
    List getUsers();
    Location getLocation();
    String getName();
    Location update();
    int deleteCourse();
    void close();
}

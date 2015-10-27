package no.westerdals.tjoida.service.LocationService;

import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.Models.User;

import java.util.List;

/**
 * Created by Cyzla on 15.10.2015.
 */
public interface LocationDAO {

    List<String> getRooms();
    void persist(Location location);
    List<String> getBuildings();
    Location update(User user);
    Location getLocation(int id);
    int deleteLocation(int id);
    void close();
}

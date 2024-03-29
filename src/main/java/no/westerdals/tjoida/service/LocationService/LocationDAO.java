package no.westerdals.tjoida.service.LocationService;

import no.westerdals.tjoida.Models.Location;

import java.util.List;

/**
 * Created by Cyzla on 15.10.2015.
 */
public interface LocationDAO {

    List<String> getRooms();

    void persist(Location location);

    List<String> getBuildings();

    Location update(Location location);

    Location getLocation(int id);

    void deleteLocation(Location location);

    public List<Location> getLocations();

    void close();
}

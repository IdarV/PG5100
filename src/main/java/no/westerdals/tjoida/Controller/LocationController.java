package no.westerdals.tjoida.Controller;

import no.westerdals.tjoida.Models.Location;
import no.westerdals.tjoida.service.LocationService.LocationDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.List;

@Model
public class LocationController {
    private LocationDAO persister;
    private Location location;
    private int selectedID;

    public LocationController() {
    }

    @Inject
    public LocationController(LocationDAO persister) {
        this.persister = persister;
    }

    @PostConstruct
    public void init() {
        this.location = new Location();
    }

    public void initLocation() {
        this.location = persister.getLocation(selectedID);
    }

    public String persistNewLocation() {
        persister.persist(location);
        return "/location/location-index.xhtml?faces-redirect=true";
    }

    public String updateLocation() {
        persister.update(location);
        return "/location/location-index.xhtml?faces-redirect=true";
    }

    public List<Location> getAll() {
        return persister.getLocations();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(int selectedID) {
        this.selectedID = selectedID;
    }

    public void setPersister(LocationDAO persister) {
        this.persister = persister;
    }
}

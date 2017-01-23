package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.Location;

public interface LocationService {
	public ArrayList<Location> getAllLocations();
	public void deleteLocation(Short id);
	public void insertLocation(Location location);
	public void updateLocation(Location location);

	public Location selectLocationById(Short id);
}

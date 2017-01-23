package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.LocationMapper;
import net.iretailer.rest.model.Location;
import net.iretailer.rest.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Override
	public ArrayList<Location> getAllLocations() {
		return locationMapper.selectAll();
	}

	@Override
	public void deleteLocation(Short id) {
		locationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertLocation(Location location) {
		locationMapper.insertSelective(location);
	}

	@Override
	public void updateLocation(Location location) {
		locationMapper.updateByPrimaryKeySelective(location);
	}

	@Override
	public Location selectLocationById(Short id) {
		return locationMapper.selectByPrimaryKey(id);
	}

}

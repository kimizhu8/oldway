package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.Device;

public interface DeviceService {
	public void insertNewDevice(Device device);
	public void deleteDeviceById(Short id);
	public void updateDevice(Device device);
	public ArrayList<Device> getDevicesById(Short id);
	public ArrayList<Device> getAllDevice();
	public void deleteDeviceList(Short[] ids);
}
package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.DeviceZone;

public interface DeviceZoneService {
	public Integer getDeviceZoneCount(Short deviceId);
	public ArrayList<DeviceZone> getDeviceZoneById(Short deviceId);
	public void updateDeviceZoneById(DeviceZone deviceZone);
	public void deleteDeviceZoneByIds(Short[] ids);
	public ArrayList<DeviceZone> getDeviceZoneBySiteId(Short siteId);
	public void insertDevicezone(DeviceZone devicezone);
}

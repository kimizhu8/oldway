package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.DeviceZoneMapper;
import net.iretailer.rest.model.DeviceZone;
import net.iretailer.rest.service.DeviceZoneService;
@Service
public class DeviceZoneServiceImpl implements DeviceZoneService{

	@Autowired
	private DeviceZoneMapper devicezoneMapper;
	
	@Override
	public Integer getDeviceZoneCount(Short deviceId) {
		return devicezoneMapper.getDevicezoneCount(deviceId);
	}

	@Override
	public ArrayList<DeviceZone> getDeviceZoneById(Short deviceId) {
		return devicezoneMapper.getDevicezoneByDeviceId(deviceId);
	}

	@Override
	public void updateDeviceZoneById(DeviceZone deviceZone) {
		devicezoneMapper.updateByPrimaryKeySelective(deviceZone);
		
	}

	@Override
	public void deleteDeviceZoneByIds(Short[] ids) {
		devicezoneMapper.deleteDevicezones(ids);
		
	}

	@Override
	public ArrayList<DeviceZone> getDeviceZoneBySiteId(Short siteId) {
		return devicezoneMapper.getDeviceZoneBySiteId(siteId);
	}

	@Override
	public void insertDevicezone(DeviceZone devicezone) {
		devicezoneMapper.insertSelective(devicezone);
	}

}

package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.DeviceMapper;
import net.iretailer.rest.model.Device;
import net.iretailer.rest.service.DeviceService;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public void insertNewDevice(Device device) {
		deviceMapper.insertSelective(device);
		
	}

	@Override
	public void deleteDeviceById(Short id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDevice(Device device) {
		deviceMapper.updateByPrimaryKeySelective(device);
	}

	@Override
	public ArrayList<Device> getDevicesById(Short siteId) {
		return deviceMapper.getDeviceBySiteId(siteId);
	}

	@Override
	public ArrayList<Device> getAllDevice() {
		return deviceMapper.getAllDevice();
	}

	@Override
	public void deleteDeviceList(Short[] ids) {
		deviceMapper.deleteDeviceList(ids);
	}

}

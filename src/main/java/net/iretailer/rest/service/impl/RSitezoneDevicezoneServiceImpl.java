package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.model.RSitezoneDevicezone;
import net.iretailer.rest.service.RSitezoneDevicezoneService;
import net.iretailer.rest.dao.RSitezoneDevicezoneMapper;

@Service
public class RSitezoneDevicezoneServiceImpl implements RSitezoneDevicezoneService{

	@Autowired
	private RSitezoneDevicezoneMapper RSitezoneDevicezoneMapper;
	
	@Override
	public Integer getRelatedTimes(Short id) {
		return RSitezoneDevicezoneMapper.getRelatedTimes(id);
	}

	@Override
	public ArrayList<RSitezoneDevicezone> getAllSitezoneDevicezoneRels() {
		return RSitezoneDevicezoneMapper.selectAll();
	}

	@Override
	public void deleteReference(Short siteZoneId, Short devicezoneId) {
		RSitezoneDevicezoneMapper.deleteByRef(devicezoneId, siteZoneId);
	}

	@Override
	public void insertReference(Short siteZoneId, Short devicezoneId, Boolean reverse) {
		RSitezoneDevicezone record = new RSitezoneDevicezone();
		record.setFkSiteZoneId(siteZoneId);
		record.setFkDeviceZoneId(devicezoneId);
		record.setReversed(reverse);
		RSitezoneDevicezoneMapper.insert(record);
	}

	@Override
	public ArrayList<RSitezoneDevicezone> getSitezoneDevicezoneRelsByDevicezoneId(Short devicezoneId) {
		return RSitezoneDevicezoneMapper.selectByDevicezoneId(devicezoneId);
		
	}

	@Override
	public ArrayList<RSitezoneDevicezone> getSitezoneDevicezoneRelsBySitezoneId(Short sitezoneId) {
		return RSitezoneDevicezoneMapper.selectBySitezoneId(sitezoneId);
	}

}

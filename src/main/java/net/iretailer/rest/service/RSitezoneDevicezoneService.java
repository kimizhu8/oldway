package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.RSitezoneDevicezone;

public interface RSitezoneDevicezoneService {
	public Integer getRelatedTimes(Short id);
	public ArrayList<RSitezoneDevicezone> getAllSitezoneDevicezoneRels();
	public void deleteReference(Short siteZoneId,Short devicezoneId);
	public void insertReference(Short siteZoneId,Short devicezoneId,Boolean reverse);
	
	public ArrayList<RSitezoneDevicezone> getSitezoneDevicezoneRelsByDevicezoneId(Short devicezoneId);
	public ArrayList<RSitezoneDevicezone> getSitezoneDevicezoneRelsBySitezoneId(Short sitezoneId);
}
package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.SiteZoneMapper;
import net.iretailer.rest.model.SiteZone;
import net.iretailer.rest.service.SiteZoneService;
@Service
public class SiteZoneServiceImpl implements SiteZoneService{

	@Autowired
	private SiteZoneMapper siteZoneMapper;
	@Override
	public SiteZone getSiteZoneById(Short id) {
		return siteZoneMapper.selectByPrimaryKey(id);
	}

	@Override
	public ArrayList<SiteZone> getAllSiteZone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SiteZone> getSiteZoneBySiteId(Short siteId) {
		return siteZoneMapper.selectSiteZoneBySiteId(siteId);
	}

	@Override
	public void insertSiteZone(SiteZone siteZone) {
		siteZoneMapper.insertSelective(siteZone);
		
	}

	@Override
	public void updateSiteZone(SiteZone siteZone) {
		siteZoneMapper.updateByPrimaryKeySelective(siteZone);
	}

	@Override
	public void deleteSiteZones(Short[] ids) {
		siteZoneMapper.deleteSitezones(ids);
	}

}

package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.SiteZone;

public interface SiteZoneService {
	public SiteZone getSiteZoneById(Short id);
	public ArrayList<SiteZone> getAllSiteZone();
	public ArrayList<SiteZone> getSiteZoneBySiteId(Short siteId);
	public void insertSiteZone(SiteZone siteZone);
	public void updateSiteZone(SiteZone siteZone);
	public void deleteSiteZones(Short[] ids);
}

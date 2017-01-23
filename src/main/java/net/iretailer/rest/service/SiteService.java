package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.Site;

public interface SiteService {
	public Site testSelect(Short id);
	public ArrayList<Site> getRootSites();
	public ArrayList<Site> getSitesByParentSiteId(Short id);
	public Site getSiteBySiteId(Short id);
	
	public ArrayList<Site> getAllSite();
	public ArrayList<Site> getParentSite();
	public void addSite(Site site);
	public void updateSite(Site site);
	public void deleteSites(Short[] siteIds);
	
}

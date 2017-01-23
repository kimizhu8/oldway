package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.SiteTag;

public interface SiteTagService {
	public void insertSiteTag(SiteTag siteTag);
	public void deleteSiteTag(Short id);
	public ArrayList<SiteTag> getTagsBySiteId(Short siteId);
	public void updateSiteTag(SiteTag siteTag);
}

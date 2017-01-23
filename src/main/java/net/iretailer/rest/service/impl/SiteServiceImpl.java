package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.SiteMapper;
import net.iretailer.rest.model.Site;
import net.iretailer.rest.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Override
	public Site testSelect(Short id) {
		return siteMapper.selectByPrimaryKey(id);
	}

	@Override
	public ArrayList<Site> getRootSites() {
		return siteMapper.selectSitesByParentId((short) 0);
	}

	@Override
	public ArrayList<Site> getSitesByParentSiteId(Short id) {
		return siteMapper.selectSitesByParentId(id);
	}

	@Override
	public Site getSiteBySiteId(Short id) {
		return siteMapper.selectByPrimaryKey(id);
	}

	@Override
	public ArrayList<Site> getAllSite() {
		return siteMapper.selectAll();
	}

	@Override
	public void addSite(Site site) {
		siteMapper.insertSelective(site);	
	}

	@Override
	public void updateSite(Site site) {
		siteMapper.updateByPrimaryKeySelective(site);
	}

	@Override
	public void deleteSites(Short[] siteIds) {
		siteMapper.deleteSites(siteIds);
	}

	@Override
	public ArrayList<Site> getParentSite() {
		return siteMapper.selectParentSite();
	}
	
}

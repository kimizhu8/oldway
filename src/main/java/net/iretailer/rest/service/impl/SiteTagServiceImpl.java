package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.SiteTagMapper;
import net.iretailer.rest.model.SiteTag;
import net.iretailer.rest.service.SiteTagService;

@Service
public class SiteTagServiceImpl implements SiteTagService{

	@Autowired
	private SiteTagMapper siteTagMapper;
	
	@Override
	public void insertSiteTag(SiteTag siteTag) {
		siteTagMapper.insert(siteTag);
	}

	@Override
	public void deleteSiteTag(Short id) {
		siteTagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ArrayList<SiteTag> getTagsBySiteId(Short siteId) {
		return siteTagMapper.selectBySiteId(siteId);
	}

	@Override
	public void updateSiteTag(SiteTag siteTag) {
		siteTagMapper.updateByPrimaryKeySelective(siteTag);
	}

}

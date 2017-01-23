package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.HomePageMapper;
import net.iretailer.rest.model.HomePage;
import net.iretailer.rest.model.HomePageByLocation;
import net.iretailer.rest.model.HomePageByTag;
import net.iretailer.rest.model.HomePageByZonetype;
import net.iretailer.rest.service.HomePageService;

@Service
public class HomePageServiceImpl implements HomePageService{

	@Autowired
	private HomePageMapper homePageMapper;
	
	@Override
	public HomePage getHomePage(boolean findchild, Integer siteId, String startTime, String endTime, String type) {
		return homePageMapper.getHomePage(findchild, siteId, startTime, endTime, type,"f");
	}

	@Override
	public ArrayList<HomePageByZonetype> getHomePagebyZonetype(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,Integer zoneType) {
		return homePageMapper.getHomePageByZonetype(findchild, siteId, startTime, endTime, type,zoneType);
	}

	@Override
	public ArrayList<HomePage> getTotalData(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,String total) {
		return homePageMapper.getTotalData(findchild, siteId, startTime, endTime, type,total);
	}

	@Override
	public ArrayList<HomePageByTag> getHomePagebyTag(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,String tagType) {
		return homePageMapper.getHomePageByTag(findchild, siteId, startTime, endTime, type,tagType);
	}

	@Override
	public ArrayList<HomePageByLocation> getHomePageByLocation(boolean findchild, Integer siteId, String startTime,
			String endTime, String type, Integer locationType) {
		return homePageMapper.getHomePageByLocation(findchild, siteId, startTime, endTime, type, locationType);
	}

	@Override
	public ArrayList<HomePage> getTotalCompareData(boolean findchild, Integer siteId, String startTime,
			String endTime) {
		return homePageMapper.getTotalCompareData(findchild, siteId, startTime, endTime);
	}

	@Override
	public ArrayList<HomePageByZonetype> getTotalSitezoneData(boolean findchild, Integer siteId, String startTime,
			String endTime,Integer zoneType) {
		return homePageMapper.getTotalCompareDataSitezone(findchild, siteId, startTime, endTime, zoneType);
	}


}

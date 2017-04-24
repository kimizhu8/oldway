package net.iretailer.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

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
	public HomePage getHomePage(boolean findchild, Integer siteId, String startTime, String endTime, String type,Integer[] blockSite,String[] names) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId",siteId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("findchild", findchild);
		String acreage = homePageMapper.getAcreage(map);
		String trueStartTime = homePageMapper.getTrueStartTime(map);
		String trueEndTime = homePageMapper.getTrueEndTime(map);
		return homePageMapper.getHomePage(findchild, siteId, startTime, endTime, type,"f",acreage,trueStartTime,trueEndTime,blockSite);
	}

	@Override
	public ArrayList<HomePageByZonetype> getHomePagebyZonetype(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,Integer zoneType,Integer[] blockSite,String[] names) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId",siteId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("findchild", findchild);
		String acreage = homePageMapper.getAcreage(map);
		String trueStartTime = homePageMapper.getTrueStartTime(map);
		String trueEndTime = homePageMapper.getTrueEndTime(map);
		return homePageMapper.getHomePageByZonetype(findchild, siteId, startTime, endTime, type,zoneType,trueStartTime,trueEndTime,blockSite,names);
	}

	@Override
	public ArrayList<HomePage> getTotalData(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,String total,Integer[] blockSite,String[] names) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId",siteId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("findchild", findchild);
		String acreage = homePageMapper.getAcreage(map);
		String trueStartTime = homePageMapper.getTrueStartTime(map);
		String trueEndTime = homePageMapper.getTrueEndTime(map);
		return homePageMapper.getTotalData(findchild, siteId, startTime, endTime, type,total,acreage,trueStartTime,trueEndTime,blockSite,names);
	}

	@Override
	public ArrayList<HomePageByTag> getHomePagebyTag(boolean findchild, Integer siteId, String startTime, String endTime,
			String type,String tagType,Integer[] blockSite,String[] names) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId",siteId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("findchild", findchild);
		String acreage = homePageMapper.getAcreage(map);
		String trueStartTime = homePageMapper.getTrueStartTime(map);
		String trueEndTime = homePageMapper.getTrueEndTime(map);
		return homePageMapper.getHomePageByTag(findchild, siteId, startTime, endTime, type,tagType,acreage,trueStartTime,trueEndTime,blockSite,names);
	}

	@Override
	public ArrayList<HomePageByLocation> getHomePageByLocation(boolean findchild, Integer siteId, String startTime,
			String endTime, String type, Integer locationType,Integer[] blockSite,String[] names) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId",siteId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("findchild", findchild);
		String acreage = homePageMapper.getAcreage(map);
		String trueStartTime = homePageMapper.getTrueStartTime(map);
		String trueEndTime = homePageMapper.getTrueEndTime(map);
		return homePageMapper.getHomePageByLocation(findchild, siteId, startTime, endTime, type, locationType,acreage,trueStartTime,trueEndTime,blockSite,names);
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

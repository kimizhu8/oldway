package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.HomePage;
import net.iretailer.rest.model.HomePageByLocation;
import net.iretailer.rest.model.HomePageByTag;
import net.iretailer.rest.model.HomePageByZonetype;

public interface HomePageService {
	HomePage getHomePage(boolean findchild,Integer siteId,String startTime,String endTime,String type);
		
	ArrayList<HomePageByZonetype> getHomePagebyZonetype(boolean findchild,Integer siteId,String startTime,String endTime,String type,Integer zoneType);
	
	ArrayList<HomePage> getTotalData(boolean findchild,Integer siteId,String startTime,String endTime,String type,String total);
	
	ArrayList<HomePageByTag> getHomePagebyTag(boolean findchild,Integer siteId,String startTime,String endTime,String type,String tagType);
	
	ArrayList<HomePageByLocation> getHomePageByLocation(boolean findchild,Integer siteId,String startTime,String endTime,String type,Integer locationType);

	ArrayList<HomePage> getTotalCompareData(boolean findchild,Integer siteId,String startTime,String endTime);
	
	ArrayList<HomePageByZonetype> getTotalSitezoneData(boolean findchild,Integer siteId,String startTime,String endTime,Integer zoneType);
}

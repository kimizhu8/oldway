package net.iretailer.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.iretailer.rest.model.HomePage;
import net.iretailer.rest.model.HomePageByLocation;
import net.iretailer.rest.model.HomePageByTag;
import net.iretailer.rest.model.HomePageByZonetype;

public interface HomePageMapper {
	HomePage getHomePage(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("type") String type,@Param("total") String total,@Param("acreage") String acreage,@Param("true_start_time") String trueStartTime,@Param("true_end_time") String trueEndTime,@Param("blockSite") Integer[] blockSite);
	
	ArrayList<HomePageByZonetype> getHomePageByZonetype(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("type") String type,@Param("zoneType") Integer zoneType,@Param("true_start_time") String trueStartTime,@Param("true_end_time") String trueEndTime,@Param("blockSite") Integer[] blockSite,@Param("names") String[] name);
	
	ArrayList<HomePage> getTotalData(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("type") String type,@Param("total") String total,@Param("acreage") String acreage,@Param("true_start_time") String trueStartTime,@Param("true_end_time") String trueEndTime,@Param("blockSite") Integer[] blockSite,@Param("names") String[] name);

	ArrayList<HomePageByTag> getHomePageByTag(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("type") String type,@Param("tagType") String tagType,@Param("acreage") String acreage,@Param("true_start_time") String trueStartTime,@Param("true_end_time") String trueEndTime,@Param("blockSite") Integer[] blockSite,@Param("names") String[] name);
	
	ArrayList<HomePageByLocation> getHomePageByLocation(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("type") String type,@Param("locationType") Integer locationType,@Param("acreage") String acreage,@Param("true_start_time") String trueStartTime,@Param("true_end_time") String trueEndTime,@Param("blockSite") Integer[] blockSite,@Param("names") String[] name);

	ArrayList<HomePage> getTotalCompareData(@Param("findchild")boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime);

	ArrayList<HomePageByZonetype> getTotalCompareDataSitezone(@Param("findchild") boolean findchild,@Param("siteid") Integer siteId,@Param("starttime") String startTime,@Param("endtime") String endTime,@Param("zoneType") Integer zoneType);

    ArrayList<Map<String,Object>> getCollectSitezoneData(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getCollectData(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getCollectLocationData(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getCollectDataNoTime(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getCollectTagData(HashMap<String,Object> map);

    ArrayList<Map<String,Object>> getSitezoneDataNoTime(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getTagDataNoTime(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getLocationNoTime(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getWeather(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getSubject(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getHoliday(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> getPeriodSubjectAndHoliday(Map<String,Object> map);
    
    String getAcreage(HashMap<String,Object> map);
    
    String getTrueStartTime(HashMap<String,Object> map);
    
    String getTrueEndTime(HashMap<String,Object> map);
}

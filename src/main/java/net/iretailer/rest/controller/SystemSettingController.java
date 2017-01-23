package net.iretailer.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import net.iretailer.rest.dto.OpeningTimeDTO;
import net.iretailer.rest.model.Holiday;
import net.iretailer.rest.model.Location;
import net.iretailer.rest.model.OpeningTime;
import net.iretailer.rest.model.OperationLog;
import net.iretailer.rest.model.Subject;
import net.iretailer.rest.service.HolidayService;
import net.iretailer.rest.service.LocationService;
import net.iretailer.rest.service.OpeningTimeService;
import net.iretailer.rest.service.OperationLogService;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class SystemSettingController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private OpeningTimeService openingTimeService;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private OperationLogService logService;
	
	@Autowired
	private HolidayService holidayService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/newOpeningTime", method = RequestMethod.GET)
	public Map<String,Object> newOpeningTime() throws ParseException{
		if (!roleService.blockRole(request,52)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Short siteId = Short.parseShort(request.getParameter("siteId"));
		String displayName = request.getParameter("displayName");
		Date startDate = sdf.parse(request.getParameter("startDate"));
		Date endDate = sdf.parse(request.getParameter("endDate"));
		Date startTime = sdf1.parse(request.getParameter("startTime"));
		Date endTime = sdf1.parse(request.getParameter("endTime"));
		//calculate weekday
		String[] weekdays = request.getParameter("weekday").split(",");
		Long weekday = 0L;
		for (int i=0;i<weekdays.length;i++){
			Integer weekdaysInt = Integer.parseInt(weekdays[i]);
			weekday += Math.round(Math.pow(2,weekdaysInt-1));
		}
		Byte weekdayByte = Byte.parseByte(weekday.toString());
		
		Byte priority = Byte.parseByte(request.getParameter("priority"));
		OpeningTime openingTime = new OpeningTime();
		openingTime.setFkSiteId(siteId);
		openingTime.setDisplayName(displayName);
		openingTime.setStartDate(startDate);
		openingTime.setEndDate(endDate);
		openingTime.setStartTime(startTime);
		openingTime.setEndTime(endTime);
		openingTime.setWeekday(weekdayByte);
		openingTime.setPriority(priority);
		openingTimeService.insertOpeningTime(openingTime);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteOpeningTime", method = RequestMethod.GET)
	public Map<String,Object> deleteOpeningTime() throws ParseException{
		if (!roleService.blockRole(request,52)) return ReturnMapUtil.packData("恶意登录");
		Short id = Short.parseShort(request.getParameter("id"));
		openingTimeService.deleteOpeningTime(id);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateOpeningTime", method = RequestMethod.GET)
	public Map<String,Object> updateOpeningTime() throws ParseException{
		if (!roleService.blockRole(request,52)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Short id = Short.parseShort(request.getParameter("id"));
		Short siteId = Short.parseShort(request.getParameter("siteId"));
		String displayName = request.getParameter("displayName");
		Date startDate = sdf.parse(request.getParameter("startDate"));
		Date endDate = sdf.parse(request.getParameter("endDate"));
		Date startTime = sdf1.parse(request.getParameter("startTime"));
		Date endTime = sdf1.parse(request.getParameter("endTime"));
		//calculate weekday
		String[] weekdays = request.getParameter("weekday").split(",");
		Long weekday = 0L;
		for (int i=0;i<weekdays.length;i++){
			Integer weekdaysInt = Integer.parseInt(weekdays[i]);
			weekday += Math.round(Math.pow(2,weekdaysInt-1));
		}
		Byte weekdayByte = Byte.parseByte(weekday.toString());
		
		Byte priority = Byte.parseByte(request.getParameter("priority"));
		OpeningTime openingTime = new OpeningTime();
		openingTime.setId(id);
		openingTime.setFkSiteId(siteId);
		openingTime.setDisplayName(displayName);
		openingTime.setStartDate(startDate);
		openingTime.setEndDate(endDate);
		openingTime.setStartTime(startTime);
		openingTime.setEndTime(endTime);
		openingTime.setWeekday(weekdayByte);
		openingTime.setPriority(priority);
		openingTimeService.updateOpeningTime(openingTime);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllOpeningTime", method = RequestMethod.GET)
	public Map<String,Object> getAllOpeningTime() throws ParseException{


		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		List<OpeningTime> openingTimes = openingTimeService.getOpeningTime(siteId);
		List<OpeningTimeDTO> openingTimeDTOs = new ArrayList<OpeningTimeDTO>();
		if (openingTimes ==null) {
			return ReturnMapUtil.packData(null);
		}
		for (int i =0;i<openingTimes.size();i++){
			OpeningTimeDTO openingTimeDTO = new OpeningTimeDTO();
			openingTimeDTO.setDisplayName(openingTimes.get(i).getDisplayName());
			openingTimeDTO.setEndDate(sdf.format(openingTimes.get(i).getEndDate()));
			openingTimeDTO.setEndTime(sdf1.format(openingTimes.get(i).getEndTime()));
			openingTimeDTO.setFkSiteId(openingTimes.get(i).getFkSiteId());
			openingTimeDTO.setId(openingTimes.get(i).getId());
			openingTimeDTO.setPriority(openingTimes.get(i).getPriority());
			openingTimeDTO.setStartDate(sdf.format(openingTimes.get(i).getStartDate()));
			openingTimeDTO.setStartTime(sdf1.format(openingTimes.get(i).getStartTime()));
			String weekday = "";
			for (int j=0;j<7;j++){
				if ((openingTimes.get(i).getWeekday() & (Math.round(Math.pow(2,j))))!=0 ){
					weekday +=""+(j+1)+",";
				}
			}
			weekday = weekday.substring(0, weekday.length()-1);
			openingTimeDTO.setWeekday(weekday);
			openingTimeDTOs.add(openingTimeDTO);
		}
		return ReturnMapUtil.packData(openingTimeDTOs);
	}
	
	@RequestMapping(value="/newLocation", method = RequestMethod.GET)
	public Map<String,Object> newLocation() throws ParseException{
		if (!roleService.blockRole(request,65)) return ReturnMapUtil.packData("恶意登录");

		Short parentId = Short.parseShort(request.getParameter("parentId"));
		Byte type = Byte.parseByte(request.getParameter("type"));
		Boolean getweather = Boolean.parseBoolean(request.getParameter("getweather"));
		Short rank = Short.parseShort(request.getParameter("rank"));
		String displayName = request.getParameter("displayName");
		String name = request.getParameter("name");
		Location location = new Location();
		location.setParentId(parentId);
		location.setGetweather(getweather);
		location.setType(type);
		location.setRank(rank);
		location.setDisplayName(displayName);
		location.setName(name);
		locationService.insertLocation(location);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateLocation", method = RequestMethod.GET)
	public Map<String,Object> updateLocation() throws ParseException{
		if (!roleService.blockRole(request,65)) return ReturnMapUtil.packData("恶意登录");

		Short id = Short.parseShort(request.getParameter("id"));
		Byte type = Byte.parseByte(request.getParameter("type"));
		Boolean getweather = Boolean.parseBoolean(request.getParameter("getweather"));
		Short rank = Short.parseShort(request.getParameter("rank"));
		String displayName = request.getParameter("displayName");
		String name = request.getParameter("name");
		Location location = new Location();
		location.setId(id);
		location.setGetweather(getweather);
		location.setType(type);
		location.setRank(rank);
		location.setDisplayName(displayName);
		location.setName(name);
		locationService.updateLocation(location);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteLocation", method = RequestMethod.GET)
	public Map<String,Object> deleteLocation() throws ParseException{
		if (!roleService.blockRole(request,65)) return ReturnMapUtil.packData("恶意登录");
		Short id = Short.parseShort(request.getParameter("id"));
		locationService.deleteLocation(id);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getLocationByParentId", method = RequestMethod.GET)
	public Map<String,Object> getLocationByParentId() throws ParseException{
		ArrayList<Location> locations = locationService.getAllLocations();
		Short parentId = null;
		if (request.getParameter("parentId")==""){
			parentId = 0;
		} else {
			parentId = Short.parseShort(request.getParameter("parentId"));
		}

		Map<String,Object> result = new HashMap<String,Object>();
		for (int i=0;i<locations.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("id", locations.get(i).getId());
			map.put("parentId",locations.get(i).getParentId());
			String type = "";
			Byte Byte0 = Byte.parseByte("0");
			Byte Byte1 = Byte.parseByte("1");
			Byte Byte2 = Byte.parseByte("2");
			Byte Byte3 = Byte.parseByte("3");
			if (locations.get(i).getType().equals(Byte0)) type = "大区";
			if (locations.get(i).getType().equals(Byte1)) type = "省";
			if (locations.get(i).getType().equals(Byte2)) type = "市";
			if (locations.get(i).getType().equals(Byte3)) type = "区县";
			map.put("type", type);
			map.put("getweather", locations.get(i).getGetweather());
			map.put("rank", locations.get(i).getRank());
			map.put("name", locations.get(i).getName());
			map.put("displayName", locations.get(i).getDisplayName());
			if (map.get("id").equals(parentId)) {
				result = map;
				Map<String,Object> cloneMap = new HashMap<String,Object>();
				cloneMap.putAll(map);
				ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				result.put("children", list);
				list.add(cloneMap);
			}
			if (map.get("parentId").equals(parentId)){
				if (result.containsKey("children")){
					@SuppressWarnings("unchecked")
					ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) result.get("children");
					list.add(map);
				} else {
					ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					result.put("children", list);
					list.add(map);
				}
			}

		}
		if (result.containsKey("children")){
			return ReturnMapUtil.packData(result.get("children"));
		} else {
			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			list.add(result);
			return ReturnMapUtil.packData(list);
		}
	}
	
	
	@RequestMapping(value="/getAllLocation", method = RequestMethod.GET)
	public Map<String,Object> getAllLocation() throws ParseException{
		ArrayList<Location> locations = locationService.getAllLocations();
		Map<String,Object> result = new HashMap<String,Object>();
		Map<Short,Map<String,Object>> orderNumber = new HashMap<Short,Map<String,Object>>();
		orderNumber.put(Short.parseShort("0"), result);
		for (int i=0;i<locations.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", locations.get(i).getId());
			map.put("parentId",locations.get(i).getParentId());
			String type = "";
			Byte Byte0 = Byte.parseByte("0");
			Byte Byte1 = Byte.parseByte("1");
			Byte Byte2 = Byte.parseByte("2");
			Byte Byte3 = Byte.parseByte("3");
			if (locations.get(i).getType().equals(Byte0)) type = "大区";
			if (locations.get(i).getType().equals(Byte1)) type = "省";
			if (locations.get(i).getType().equals(Byte2)) type = "市";
			if (locations.get(i).getType().equals(Byte3)) type = "区县";
			map.put("type", type);
			map.put("getweather", locations.get(i).getGetweather());
			map.put("rank", locations.get(i).getRank());
			map.put("name", locations.get(i).getName());
			map.put("displayName", locations.get(i).getDisplayName());
			orderNumber.put(locations.get(i).getId(), map);
		}
		for (int i=0;i<locations.size();i++){
			Map<String,Object> map = orderNumber.get(locations.get(i).getId());
			Map<String,Object> fatherMap = orderNumber.get(locations.get(i).getParentId());
			if (fatherMap == null) {
				continue;
			}
			if (fatherMap.containsKey("children")){
				@SuppressWarnings("unchecked")
				ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) fatherMap.get("children");
				list.add(map);
			} else {
				ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				fatherMap.put("children", list);
				list.add(map);
			}
		}
		return ReturnMapUtil.packData(result.get("children"));
	}
	
	@RequestMapping(value="/newHoliday", method = RequestMethod.GET)
	public Map<String,Object> newHoliday() throws ParseException{
		if (!roleService.blockRole(request,62)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(request.getParameter("startDate"));
		Date endDate =sdf.parse(request.getParameter("endDate"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String workingDay = request.getParameter("workingDay");
		String description =request.getParameter("description");
		Holiday holiday = new Holiday();
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holiday.setName(name);
		holiday.setType(type);
		holiday.setWorkingDay(workingDay);
		holiday.setDescription(description);
		holidayService.insertHoliday(holiday);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateHoliday", method = RequestMethod.GET)
	public Map<String,Object> updateHoliday() throws ParseException{
		if (!roleService.blockRole(request,62)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Short id = Short.parseShort(request.getParameter("id"));
		Date startDate = sdf.parse(request.getParameter("startDate"));
		Date endDate =sdf.parse(request.getParameter("endDate"));
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String workingDay = request.getParameter("workingDay");
		String description =request.getParameter("description");
		Holiday holiday = new Holiday();
		holiday.setId(id);
		holiday.setStartDate(startDate);
		holiday.setEndDate(endDate);
		holiday.setName(name);
		holiday.setType(type);
		holiday.setWorkingDay(workingDay);
		holiday.setDescription(description);
		holidayService.updateHoliday(holiday);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteHoliday", method = RequestMethod.GET)
	public Map<String,Object> deleteHoliday() throws ParseException{
		if (!roleService.blockRole(request,62)) return ReturnMapUtil.packData("恶意登录");
		Short id = Short.parseShort(request.getParameter("id"));
		holidayService.deleteHoliday(id);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllHoliday", method = RequestMethod.GET)
	public Map<String,Object> getAllHoliday() throws ParseException{
		ArrayList<Holiday> holidays= holidayService.getAllHoliday();
		return ReturnMapUtil.packData(holidays);
	}
	
	@RequestMapping(value="/newSubject", method = RequestMethod.GET)
	public Map<String,Object> newSubject() throws ParseException{
		if (!roleService.blockRole(request,51)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = sdf.parse(request.getParameter("startTime"));
		Date endTime =sdf.parse(request.getParameter("endTime"));
		String name = request.getParameter("name");
		Short siteId = Short.parseShort(request.getParameter("siteId"));
		Subject subject = new Subject();
		subject.setEndTime(endTime);
		subject.setStartTime(startTime);
		subject.setName(name);
		subject.setSiteId(siteId);
		holidayService.insertSubject(subject);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateSubject", method = RequestMethod.GET)
	public Map<String,Object> updateSubject() throws ParseException{
		if (!roleService.blockRole(request,51)) return ReturnMapUtil.packData("恶意登录");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Short id = Short.parseShort(request.getParameter("id"));
		Date startTime = sdf.parse(request.getParameter("startTime"));
		Date endTime =sdf.parse(request.getParameter("endTime"));
		String name = request.getParameter("name");
		Short siteId = Short.parseShort(request.getParameter("siteId"));
		Subject subject = new Subject();
		subject.setId(id);
		subject.setEndTime(endTime);
		subject.setStartTime(startTime);
		subject.setName(name);
		subject.setSiteId(siteId);
		holidayService.updateSubject(subject);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteSubject", method = RequestMethod.GET)
	public Map<String,Object> deleteSubject() throws ParseException{
		if (!roleService.blockRole(request,51)) return ReturnMapUtil.packData("恶意登录");
		Short id = Short.parseShort(request.getParameter("id"));
		holidayService.deleteSubject(id);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllSubject", method = RequestMethod.GET)
	public Map<String,Object> getAllSubject() throws ParseException{
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		ArrayList<Subject> subject= holidayService.getAllSubject(siteId);
		return ReturnMapUtil.packData(subject);
	}
	@RequestMapping(value="/getLog", method = RequestMethod.GET)
	public Map<String,Object> getLog() throws ParseException{
		ArrayList<OperationLog> log= logService.getLog();
		Object jsonObject = JSON.toJSON(log);
		return ReturnMapUtil.packData(jsonObject);
	}
		
}

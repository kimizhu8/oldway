package net.iretailer.rest.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.iretailer.rest.dao.DeviceZoneMapper;
import net.iretailer.rest.dao.EmployeeNumberMapper;
import net.iretailer.rest.model.Device;
import net.iretailer.rest.model.DeviceZone;
import net.iretailer.rest.model.Location;
import net.iretailer.rest.model.RSitezoneDevicezone;
import net.iretailer.rest.model.Site;
import net.iretailer.rest.model.SiteTag;
import net.iretailer.rest.model.SiteZone;
import net.iretailer.rest.service.DeviceService;
import net.iretailer.rest.service.DeviceZoneService;
import net.iretailer.rest.service.LocationService;
import net.iretailer.rest.service.OperationLogService;
import net.iretailer.rest.service.RSitezoneDevicezoneService;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.SiteService;
import net.iretailer.rest.service.SiteTagService;
import net.iretailer.rest.service.SiteZoneService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class BusinessSettingController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DeviceZoneService deviceZoneService;
	
	@Autowired
	private DeviceZoneMapper deviceZoneMapper;
	
	@Autowired
	private RSitezoneDevicezoneService rSitezoneDevicezoneService;
	
	@Autowired
	private SiteZoneService siteZoneService;
	
	@Autowired
	private OperationLogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private SiteTagService siteTagService;
	
	@Autowired
	private EmployeeNumberMapper employeeNumberMapper;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/getAllEmployeeNum",method= RequestMethod.GET)
	public Map<String,Object> getAllEmployeeNumber(){
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Integer siteId = Integer.parseInt(request.getParameter("siteName"));
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("siteId", siteId);
		List<Map<String,Object>> result = employeeNumberMapper.selectAllEmployeeNum(map);
		for (int i=0;i<result.size();i++){
			if (!result.get(i).containsKey("description")){
				result.get(i).put("description", null);
			}

			Timestamp timeStamp = (Timestamp)result.get(i).get("date");
			result.get(i).put("date", sdfDate.format(timeStamp));
		}
		return ReturnMapUtil.packData(result);
	}
	
	@RequestMapping(value="/insertEmployeeNum",method= RequestMethod.GET)
	public Map<String,Object> insertEmployeeNum(){
		Long insertDate = Long.parseLong(request.getParameter("insertDate"));
		Integer number = Integer.parseInt(request.getParameter("number"));
		Integer siteId = Integer.parseInt(request.getParameter("siteName"));
		String desc = request.getParameter("desc");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",sdfDate.format(insertDate));
		map.put("number",number);
		map.put("desc", desc);
		map.put("siteId", siteId);
		employeeNumberMapper.insertEmployeeNumber(map);
		return ReturnMapUtil.packData(null);
	}
	@RequestMapping(value="/updateEmployeeNum",method= RequestMethod.GET)
	public Map<String,Object> updateEmployeeNumber(){
		Long insertDate = Long.parseLong(request.getParameter("insertDate"));
		Integer number = Integer.parseInt(request.getParameter("number"));
		Integer siteId = Integer.parseInt(request.getParameter("siteName"));
		Integer id = Integer.parseInt(request.getParameter("id"));
		String desc = request.getParameter("desc");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date",sdfDate.format(insertDate));
		map.put("number",number);
		map.put("desc", desc);
		map.put("siteId", siteId);
		map.put("id",id);
		employeeNumberMapper.updateEmployeeNumber(map);
		return ReturnMapUtil.packData(null);
	}
	@RequestMapping(value="/deleteEmployeeNumber",method= RequestMethod.GET)
	public Map<String,Object> deleteEmployeeNumber(){
		Integer id = Integer.parseInt(request.getParameter("id"));

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		employeeNumberMapper.deleteEmployeeNumber(map);
		return ReturnMapUtil.packData(null);
	}
	@RequestMapping(value="/test",method= RequestMethod.GET)
	public String test(@Param(value = "id") Short id){
		Site site = siteService.testSelect(id);
		return site.getDisplayName();
	}
	
	@RequestMapping(value="/newDevice", method = RequestMethod.POST)
	public Map<String,Object> newDevice(){
		if (!roleService.blockRole(request,42)) return ReturnMapUtil.packData("恶意登录");
		Device device = new Device();
		device.setFkSiteId(Short.parseShort(request.getParameter("siteName")));
		device.setDeviceType(request.getParameter("deviceType"));
		device.setName(request.getParameter("_name"));
		device.setDisplayName(request.getParameter("deviceName"));
		device.setCoordinate(request.getParameter("coordinate"));
		device.setDescription(request.getParameter("descritpion"));
		device.setDisabled(false);
		device.setLastUpdate(new Date());
		deviceService.insertNewDevice(device);
		logService.insertOperationLog(request.getRemoteAddr(), "newDevice", "device", "success", request.getParameterMap().toString());
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateDevice",method = RequestMethod.POST)
	public Map<String,Object> updateDevice(){
		if (!roleService.blockRole(request,42)) return ReturnMapUtil.packData("恶意登录");
		Device device = new Device();
		device.setId(Short.parseShort(request.getParameter("id")));
		device.setFkSiteId(Short.parseShort(request.getParameter("siteName")));
		device.setName(request.getParameter("_name"));
		device.setDisplayName(request.getParameter("deviceName"));
		device.setCoordinate(request.getParameter("coordinate"));
		device.setDescription(request.getParameter("description"));
		device.setDeviceType(request.getParameter("deviceType"));
		device.setLastUpdate(new Date());
		deviceService.updateDevice(device);
		logService.insertOperationLog(request.getRemoteAddr(), "updateDevice", "device", "success", request.getParameterMap().toString());
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteDevice",method = RequestMethod.POST)
	public Map<String,Object> deleteDevice(){
		if (!roleService.blockRole(request,42)) return ReturnMapUtil.packData("恶意登录");
		String ids = request.getParameter("ids");
		String[] idString = ids.split(",");
		Short[] idInt = new Short[idString.length];
		for (int i=0;i<idInt.length;i++){
			idInt[i] = Short.parseShort(idString[i]);
		}
		deviceService.deleteDeviceList(idInt);
		logService.insertOperationLog(request.getRemoteAddr(), "deleteDeviceZone", "device_zone", "success", request.getParameterMap().toString());
		return ReturnMapUtil.packData("0");
	}
	
	
	@RequestMapping(value="/getDeviceByName")
	public Map<String,Object> getAllDevices() {
		if (request.getParameter("siteName").equals("")){
			return ReturnMapUtil.packData("");
		}
		ArrayList<Device> devices = deviceService.getDevicesById(Short.parseShort(request.getParameter("siteName")));
		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (int i=0;i<devices.size();i++){
			Device device = devices.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deviceType", device.getDeviceType());
			map.put("id", device.getId());
			map.put("deviceName", device.getDisplayName());
			map.put("type", device.getDeviceType());
			map.put("siteName",device.getFkSiteId());
			map.put("coordinate", device.getCoordinate());
			map.put("description", device.getDescription());
			map.put("macAddress", device.getMacAddress());
			map.put("lastUpdate", device.getLastUpdate());
			map.put("_name", device.getName());
			map.put("ip", device.getIpAddress());
			Integer count = deviceZoneService.getDeviceZoneCount(device.getId());
			map.put("siteZoneCount", count);
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
	}
	@RequestMapping("/newDeviceZone")
	public Map<String,Object> newDeviceZone(HttpServletRequest request){
		Short deviceId = Short.parseShort(request.getParameter("deviceId"));
		String deviceName = request.getParameter("deviceZoneName");
		String description = request.getParameter("description");
		String coordinate = request.getParameter("coordinate");
		String name = request.getParameter("_name");
		Byte zoneType = Byte.parseByte(request.getParameter("deviceZoneType"));
		DeviceZone devicezone = new DeviceZone();
		devicezone.setName(name);
		devicezone.setFkDeviceId(deviceId);
		devicezone.setDeviceName(deviceName);
		devicezone.setDescription(description);
		devicezone.setCoordinate(coordinate);
		devicezone.setType(zoneType);
		devicezone.setDisabled(false);
		devicezone.setDisplayName(deviceName);
		deviceZoneService.insertDevicezone(devicezone);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping("/getDeviceZoneById")
	public Map<String,Object> getDeviceZoneById(){
		ArrayList<DeviceZone> devicezones = deviceZoneService.getDeviceZoneById(Short.parseShort(request.getParameter("deviceId")));
		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (int i=0;i<devicezones.size();i++){
			DeviceZone devicezone = devicezones.get(i);
			Integer relatedTimes = rSitezoneDevicezoneService.getRelatedTimes(devicezone.getId());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", devicezone.getId());
			map.put("devicezoneName", devicezone.getDisplayName());
			map.put("description",devicezone.getDescription());
			map.put("zoneType", devicezone.getType());
			map.put("coordinate", devicezone.getCoordinate());
			map.put("relatedTimes", relatedTimes);
			map.put("_name", devicezone.getName());
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
	}
	
	@RequestMapping("/getDeviceZoneBySiteId")
	public Map<String,Object> getDeviceZoneBySiteId(){
		ArrayList<DeviceZone> devicezones = deviceZoneService.getDeviceZoneBySiteId(Short.parseShort(request.getParameter("siteId")));
		return ReturnMapUtil.packData(devicezones);
	}
	
	
	@RequestMapping("/updateDeviceZone")
	public Map<String,Object> updateDeviceZone(){
		DeviceZone deviceZone = new DeviceZone();
		deviceZone.setId(Short.parseShort(request.getParameter("id")));
		deviceZone.setDisplayName(request.getParameter("deviceZoneName"));
		deviceZone.setFkDeviceId(Short.parseShort(request.getParameter("deviceId")));
		deviceZone.setName(request.getParameter("deviceZoneName"));
		deviceZone.setDescription(request.getParameter("description"));
		deviceZone.setCoordinate(request.getParameter("coordinate"));
		deviceZone.setType(Byte.parseByte(request.getParameter("deviceZoneType")));
		deviceZone.setName(request.getParameter("_name"));
		deviceZoneService.updateDeviceZoneById(deviceZone);
		logService.insertOperationLog(request.getRemoteAddr(), "updateDeviceZone", "device_zone", "success", request.getParameterMap().toString());
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping("/deleteDeviceZone")
	public Map<String,Object> deleteDeviceZone(){
		String ids = request.getParameter("ids");
		String[] idString = ids.split(",");
		Short[] idInt = new Short[idString.length];
		for (int i=0;i<idInt.length;i++){
			idInt[i] = Short.parseShort(idString[i]);
		}
		deviceZoneService.deleteDeviceZoneByIds(idInt);
		logService.insertOperationLog(request.getRemoteAddr(), "deleteDeviceZone", "device_zone", "success", request.getParameterMap().toString());
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping("/getRSiteZone")
	public Map<String,Object> getRSiteZone(){
		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Short siteZoneId = Short.parseShort(request.getParameter("siteZoneId"));
		Short isSite = Short.parseShort(request.getParameter("issite"));
		if (isSite == 1){
			return ReturnMapUtil.packData(resultList);
		}
		Short siteId = siteZoneService.getSiteZoneById(siteZoneId).getFkSiteId();
		if (siteService.testSelect(siteId).getParentId()!=0){
			siteId = siteService.testSelect(siteId).getParentId();
		}
		ArrayList<DeviceZone> deviceZones = deviceZoneMapper.getDeviceZoneBySiteIdTemp(siteId);
		ArrayList<RSitezoneDevicezone> rSitezoneDevicezone = rSitezoneDevicezoneService.getAllSitezoneDevicezoneRels();
		for (int i=0;i<deviceZones.size();i++){
			DeviceZone deviceZone = deviceZones.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deviceName", deviceZone.getDeviceName());
			map.put("deviceId", deviceZone.getFkDeviceId());
			map.put("deviceZoneId", deviceZone.getId());
			map.put("deviceZoneName", deviceZone.getName());
			map.put("zoneType", deviceZone.getType());
			Integer reverse = checkReference(rSitezoneDevicezone,deviceZone.getId(),siteZoneId);
			if (reverse>=0){
				map.put("rele", 1);
				map.put("reverse", reverse);
			} else {
				map.put("rele", 0);
				map.put("reverse", reverse);
			}
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
	}
	
	
	@RequestMapping("/getRDeviceZone")
	public Map<String,Object> getRDeviceZone(){
		Short devicezoneId = Short.parseShort(request.getParameter("deviceZoneId"));
		ArrayList<RSitezoneDevicezone> rSitezoneDevicezone = rSitezoneDevicezoneService.getSitezoneDevicezoneRelsByDevicezoneId(devicezoneId);
		ArrayList<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for (int i=0;i<rSitezoneDevicezone.size();i++){
			RSitezoneDevicezone origin = rSitezoneDevicezone.get(i);
			SiteZone siteZone = siteZoneService.getSiteZoneById(origin.getFkSiteZoneId());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("siteZoneId", origin.getFkSiteZoneId());
			String siteZoneName = siteZone.getDisplayName();
			String siteName = siteService.getSiteBySiteId(siteZone.getFkSiteId()).getDisplayName();
			map.put("siteName", siteName);
			map.put("siteZoneName", siteZoneName);
			map.put("zoneType", siteZone.getType());
			Integer reverse = checkReference(rSitezoneDevicezone,devicezoneId,origin.getFkSiteZoneId());
			if (reverse>=0){
				map.put("rele", 1);
				map.put("reverse", reverse);
			} else {
				map.put("rele", 0);
				map.put("reverse", reverse);
			}
			resultList.add(map);
		}
		return ReturnMapUtil.packData(resultList);
	}
	
    @RequestMapping("/updateRDeviceZone")
    public Map<String,Object> updateServiceDevices(HttpServletRequest request) {
    	Short siteZoneId = Short.parseShort(request.getParameter("siteZoneId"));
    	Short deviceZoneId = Short.parseShort(request.getParameter("deviceZoneId"));
    	Integer rele = Integer.parseInt(request.getParameter("rele"));
    	Byte reverse = Byte.parseByte(request.getParameter("reverse"));
    	Boolean booleanReverse = true;
    	if (reverse == -1) reverse=0;
    	if (reverse == 0) {
    		booleanReverse =false;
    	}
    	rSitezoneDevicezoneService.deleteReference(siteZoneId,deviceZoneId);
    	if (rele==1){
        	rSitezoneDevicezoneService.insertReference(siteZoneId, deviceZoneId, booleanReverse);
    	}
		logService.insertOperationLog(request.getRemoteAddr(), "updateRDeviceZone", "r_sitezone_devicezone", "success", request.getParameterMap().toString());
    	return ReturnMapUtil.packData("0");
    }
    @RequestMapping("/getSiteZoneByName")
    public Map<String,Object> getAllSitezones() {
    	ArrayList<SiteZone> siteZones= null;
        Short siteId = Short.parseShort(request.getParameter("siteName"));
        siteZones = siteZoneService.getSiteZoneBySiteId(siteId);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	for (int i=0;i<siteZones.size();i++){
    		Map<String,Object> map = new HashMap<String,Object>();
    		SiteZone siteZone = siteZones.get(i);
    		String name = siteService.getSiteBySiteId(siteZone.getFkSiteId()).getName();
    		map.put("id",siteZone.getId());
    		map.put("siteName", siteZone.getFkSiteId());
    		map.put("sitezoneName", siteZone.getDisplayName());
    		map.put("sitezoneType", siteZone.getType());
    		map.put("rank", siteZone.getRank());
    		map.put("coordinate", siteZone.getCoordinate());
    		map.put("description", siteZone.getDescription());
    		map.put("name", name);
    		list.add(map);
    	}
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	resultMap.put("siteZone", list);
    	Site site = siteService.getSiteBySiteId(siteId);
    	resultMap.put("name", site.getName());
    	ArrayList<Map<String,String>> siteZoneType = new ArrayList<Map<String,String>>();
    	String[] hint = {"主出入口","出入口","通道","楼层","区域","展示区","排队区","收银点","EAS通道","信息检测点","WeChatShake"};
    	for (int i =0;i<11;i++){
    		Map<String,String> map = new HashMap<String,String>();
    		map.put("name",i+"");
    		map.put("value", hint[i]);
    		siteZoneType.add(map);
    	}
    	resultMap.put("sitezoneType", siteZoneType);
    	return ReturnMapUtil.packData(resultMap);
    }
    
    @RequestMapping("/newSitezone")
    public Map<String,Object> newSiteZone(){
		if (!roleService.blockRole(request,41)) return ReturnMapUtil.packData("恶意登录");
    	SiteZone siteZone = new SiteZone();
    	siteZone.setFkSiteId(Short.parseShort(request.getParameter("siteName")));
    	siteZone.setDisplayName(request.getParameter("sitezoneName"));
    	siteZone.setType(Byte.parseByte(request.getParameter("sitezoneType")));
    	siteZone.setRank(Short.parseShort(request.getParameter("rank")));
    	siteZone.setCoordinate(request.getParameter("coordinate"));
    	siteZone.setDescription(request.getParameter("description"));
    	siteZone.setDisabled(true);
    	siteZoneService.insertSiteZone(siteZone);
		logService.insertOperationLog(request.getRemoteAddr(), "newSitezone", "site_zone", "success", request.getParameterMap().toString());
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping("/updateSitezone")
    public Map<String,Object> updateSitezone(){
		if (!roleService.blockRole(request,41)) return ReturnMapUtil.packData("恶意登录");
    	SiteZone siteZone = new SiteZone();
    	siteZone.setId(Short.parseShort(request.getParameter("id")));
    	siteZone.setDisplayName(request.getParameter("sitezoneName"));
    	siteZone.setFkSiteId(Short.parseShort(request.getParameter("siteName")));
    	siteZone.setType(Byte.parseByte(request.getParameter("sitezoneType")));
    	siteZone.setRank(Short.parseShort(request.getParameter("rank")));
    	siteZone.setCoordinate(request.getParameter("coordinate"));
    	siteZone.setDescription(request.getParameter("description"));
    	siteZoneService.updateSiteZone(siteZone);
		logService.insertOperationLog(request.getRemoteAddr(), "updateSitezone", "site_zone", "success", request.getParameterMap().toString());
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping("/deleteSitezone")
    public Map<String,Object> deleteSitezone(){
		if (!roleService.blockRole(request,41)) return ReturnMapUtil.packData("恶意登录");
    	String ids = request.getParameter("ids");
    	String[] idString = ids.split(",");
    	Short[] idShort = new Short[idString.length];
    	for (int i=0;i<idString.length;i++){
    		idShort[i] = Short.parseShort(idString[i]);
    	}
    	siteZoneService.deleteSiteZones(idShort);
		logService.insertOperationLog(request.getRemoteAddr(), "deleteSitezone", "site_zone", "success", request.getParameterMap().toString());
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping("/getAllSites")
    public Map<String,Object> getAllSites() {
    	Short siteName = Short.parseShort(request.getParameter("siteName"));
    	ArrayList<Site> siteList = null;
    	Map<String,Object> result = new HashMap<String,Object>();
    	
    	if (siteName.equals("")){
//        	result= siteService.getAllSites();
    	} else {
    		siteList= siteService.getSitesByParentSiteId(siteName); 
    	}
    	ArrayList<Location> locations = new ArrayList<Location>();
    	locations = locationService.getAllLocations();
    	ArrayList<Map<String,Object>> siteLocation = new ArrayList<Map<String,Object>>();
    	LinkedList<Map<String,Object>> siteLocation1 = new LinkedList<Map<String,Object>>();
    
    	for (int i=0;i<locations.size();i++){
    		Map<String,Object> mapLocation = new HashMap<String,Object>();
    		mapLocation.put("name", locations.get(i).getId());
    		String locationName = "";
    		for (int j=0;j<locations.get(i).getType();j++){
    			locationName +="　";
    		}
    		locationName += locations.get(i).getDisplayName();
    		mapLocation.put("value", locationName);
    		mapLocation.put("type",  locations.get(i).getType());
    		mapLocation.put("parentId", locations.get(i).getParentId());
    		siteLocation.add(mapLocation);
    	}
    	for (int i=0;i<4;i++){
    		for (int j=0;j<siteLocation.size();j++){
    			if (Integer.parseInt(siteLocation.get(j).get("type").toString())!=i) continue;
    			if (i==0) {
    				siteLocation1.add(0,siteLocation.get(j));
    				continue;
    			}
    			Integer position = 0;
    			for (int k=0;k<siteLocation1.size();k++){
    				if (siteLocation1.get(k).get("name").equals(siteLocation.get(j).get("parentId"))){
    					position = k;
    					break;
    				}
    			}
    			siteLocation1.add(position+1,siteLocation.get(j));
    		}
    	}
    	
    	result.put("siteLocation", siteLocation1);
		ArrayList<Map<String,Object>> siteTypeList = new ArrayList<Map<String,Object>>();
		Map<String,Object> shangpu = new HashMap<String,Object>();
		shangpu.put("name", "0");
		shangpu.put("value", "商铺");
		Map<String,Object> gouwu = new HashMap<String,Object>();
		gouwu.put("name", "1");
		gouwu.put("value", "购物中心");
		Map<String,Object> liansuo = new HashMap<String,Object>();
		liansuo.put("name", "2");
		liansuo.put("value", "零售连锁");
		Map<String,Object> zhanhui = new HashMap<String,Object>();
		zhanhui.put("name", "3");
		zhanhui.put("value", "展会");
		siteTypeList.add(shangpu);
		siteTypeList.add(gouwu);
		siteTypeList.add(liansuo);
		siteTypeList.add(zhanhui);
		result.put("siteType", siteTypeList);
		
    	ArrayList<Map<String,Object>> finalSiteList = new ArrayList<Map<String,Object>>();
    	for (int i=0;i<siteList.size();i++){
    		Site site =siteList.get(i);
    		Map<String,Object> siteMap = new HashMap<String,Object>();
    		Location location = locationService.selectLocationById(site.getFkLocationId());
    		ArrayList<SiteTag> siteTagList = siteTagService.getTagsBySiteId(site.getId());
    		Integer sitezoneCount = siteZoneService.getSiteZoneBySiteId(site.getId()).size();
    		String tag = "";
    		String locationName = location.getDisplayName();
    		Short locationId = location.getId();
    		siteMap.put("siteName", site.getId());
    		siteMap.put("parentSiteId", site.getParentId());
    		siteMap.put("name", site.getDisplayName());
    		siteMap.put("coordinate", site.getCoordinate());
    		siteMap.put("description", site.getDescription());
    		siteMap.put("address", site.getAddress());
    		siteMap.put("siteType", site.getType());
    		siteMap.put("rank", site.getRank());
    		siteMap.put("operationAcreage", site.getOperationAcreage());

    		siteMap.put("zoneCount",sitezoneCount);
    		siteMap.put("tagValues","");
    		if (siteTagList!=null&&siteTagList.size()!=0){
        		for (int j=0;j<siteTagList.size()-1;j++){
        			tag +=siteTagList.get(j).getType()+":"+siteTagList.get(j).getValue()+",";
        		}
        		tag+=siteTagList.get(siteTagList.size()-1).getType()+":"+siteTagList.get(siteTagList.size()-1).getValue();
    		}

    		
    		siteMap.put("tags", tag);
    		siteMap.put("locationName", locationName);
    		siteMap.put("locationId", locationId.toString());
    		siteMap.put("_name", site.getName());
    		finalSiteList.add(siteMap);
    	}
    	
    	result.put("sites", finalSiteList);
    	return ReturnMapUtil.packData(result);
    }
    
    @RequestMapping("/getAllParentSite")
    public Map<String,Object> getAllParentSite() {
    	ArrayList<Site> siteList = null;
    	Map<String,Object> result = new HashMap<String,Object>();
    	
    	siteList= siteService.getParentSite();
    	ArrayList<Location> locations = new ArrayList<Location>();
    	locations = locationService.getAllLocations();
    	ArrayList<Map<String,Object>> siteLocation = new ArrayList<Map<String,Object>>();
    	for (int i=0;i<locations.size();i++){
    		Map<String,Object> mapLocation = new HashMap<String,Object>();
    		mapLocation.put("name", locations.get(i).getId());
    		mapLocation.put("value", locations.get(i).getDisplayName());
    		siteLocation.add(mapLocation);
    	}
    	result.put("siteLocation", siteLocation);

		ArrayList<Map<String,Object>> siteTypeList = new ArrayList<Map<String,Object>>();
		Map<String,Object> shangpu = new HashMap<String,Object>();
		shangpu.put("name", "0");
		shangpu.put("value", "商铺");
		Map<String,Object> gouwu = new HashMap<String,Object>();
		gouwu.put("name", "1");
		gouwu.put("value", "购物中心");
		Map<String,Object> liansuo = new HashMap<String,Object>();
		liansuo.put("name", "2");
		liansuo.put("value", "零售连锁");
		Map<String,Object> zhanhui = new HashMap<String,Object>();
		zhanhui.put("name", "3");
		zhanhui.put("value", "展会");
		siteTypeList.add(shangpu);
		siteTypeList.add(gouwu);
		siteTypeList.add(liansuo);
		siteTypeList.add(zhanhui);
		result.put("siteType", siteTypeList);
		
    	ArrayList<Map<String,Object>> finalSiteList = new ArrayList<Map<String,Object>>();
    	for (int i=0;i<siteList.size();i++){
    		Site site =siteList.get(i);
    		Map<String,Object> siteMap = new HashMap<String,Object>();
    		String locationName = locationService.selectLocationById(site.getFkLocationId()).getDisplayName();
    		siteMap.put("siteName", site.getId());
    		siteMap.put("parentSiteId", site.getParentId());
    		siteMap.put("name", site.getDisplayName());
    		siteMap.put("coordinate", site.getCoordinate());
    		siteMap.put("description", site.getDescription());
    		siteMap.put("address", site.getAddress());
    		siteMap.put("siteType", site.getType());
    		siteMap.put("rank", site.getRank());
    		siteMap.put("operationAcreage", site.getOperationAcreage());
    		siteMap.put("zoneCount","");
    		siteMap.put("tagValues","");
    		siteMap.put("tags", "");
    		siteMap.put("locationName", locationName);
    		siteMap.put("_name", site.getName());
    		finalSiteList.add(siteMap);
    	}
    	
    	result.put("sites", finalSiteList);
    	return ReturnMapUtil.packData(result);
    }

    
    @RequestMapping(value="/newSite", method= RequestMethod.POST)
    public Map<String,Object> newSite(HttpServletRequest request) {
		if (!roleService.blockRole(request,40)) return ReturnMapUtil.packData("恶意登录");
    	Short locationId = Short.parseShort(request.getParameter("locationId"));
    	Short parentSiteId = 0;
    	if (!request.getParameter("parentSiteId").equals("/")){
    		parentSiteId = Short.parseShort(request.getParameter("parentSiteId"));
    	}
    	String name = request.getParameter("name");
    	Integer operationAcreage =Integer.parseInt(request.getParameter("operationAcreage"));
    	Short rank = Short.parseShort(request.getParameter("rank"));
    	String address = request.getParameter("address");
    	Byte siteType = Byte.parseByte(request.getParameter("siteType"));
    	String coordinate = request.getParameter("coordinate");
    	String description = request.getParameter("description");
    	String _name = request.getParameter("_name");
    	
    	Site site = new Site();
    	site.setFkLocationId(locationId);
    	site.setParentId(parentSiteId);
    	site.setDisplayName(name);
    	
    	site.setOperationAcreage(operationAcreage);
    	site.setRank(rank);
    	site.setAddress(address);
    	site.setType(siteType);
    	site.setCoordinate(coordinate);
    	site.setDescription(description);
    	site.setDisabled(true);
    	site.setName(_name);
    	siteService.addSite(site);
    	
    	return ReturnMapUtil.packData("0");
    }

    @RequestMapping(value="/updateSite", method= RequestMethod.POST)
    public Map<String,Object> updateSite() {
		if (!roleService.blockRole(request,40)) return ReturnMapUtil.packData("恶意登录");
    	Short siteId = Short.parseShort(request.getParameter("siteName"));
    	Short locationId = Short.parseShort(request.getParameter("locationId"));
    	Short parentSiteId = null;
    	if (!request.getParameter("parentSiteId").equals("/")){
    		parentSiteId = Short.parseShort(request.getParameter("parentSiteId"));
    	}
    	String name = request.getParameter("name");
    	Integer operationAcreage =Integer.parseInt(request.getParameter("operationAcreage"));
    	Short rank = Short.parseShort(request.getParameter("rank"));
    	String address = request.getParameter("address");
    	Byte siteType = Byte.parseByte(request.getParameter("siteType"));
    	String coordinate = request.getParameter("coordinate");
    	String description = request.getParameter("description");
    	String _name = request.getParameter("_name");
    	
    	Site site = new Site();
    	site.setId(siteId);
    	site.setFkLocationId(locationId);
    	site.setParentId(parentSiteId);
    	site.setDisplayName(name);
    	site.setOperationAcreage(operationAcreage);
    	site.setRank(rank);
    	site.setAddress(address);
    	site.setType(siteType);
    	site.setCoordinate(coordinate);
    	site.setDescription(description);
    	site.setName(_name);
    	
    	siteService.updateSite(site);
    	return ReturnMapUtil.packData("0");
    }

    @RequestMapping(value="/deleteSites", method= RequestMethod.POST)
    public Map<String,Object> deleteSites(HttpServletRequest request) {
		if (!roleService.blockRole(request,40)) return ReturnMapUtil.packData("恶意登录");
    	String siteNames = request.getParameter("siteNames");
    	String[] names = siteNames.split(",");
    	Short[] siteIds = new Short[names.length];
    	for (int i=0;i<names.length;i++){
    		siteIds[i] = Short.parseShort(names[i]);
    	}
    	siteService.deleteSites(siteIds);
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping(value="/newTag", method= RequestMethod.POST)
    public Map<String,Object> newTag(HttpServletRequest request) {
    	Short siteId = Short.parseShort(request.getParameter("siteName"));
    	String type = request.getParameter("type");
    	String value = request.getParameter("value");
    	SiteTag siteTag = new SiteTag();
    	siteTag.setFkSiteId(siteId);
    	siteTag.setType(type);
    	siteTag.setValue(value);
    	siteTagService.insertSiteTag(siteTag);
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping(value="/getAllTags", method= RequestMethod.POST)
    public Map<String,Object> getAllTags(HttpServletRequest request) {
    	Short siteId = Short.parseShort(request.getParameter("siteName"));
		ArrayList<SiteTag> tags = siteTagService.getTagsBySiteId(siteId);
    	return ReturnMapUtil.packData(tags);
    }
    
    @RequestMapping(value="/updateTag", method= RequestMethod.POST)
    public Map<String,Object> updateTag(HttpServletRequest request) {
    	Short id = Short.parseShort(request.getParameter("id"));
    	Short siteId = Short.parseShort(request.getParameter("siteName"));
    	String type = request.getParameter("type");
    	String value = request.getParameter("value");
    	SiteTag siteTag = new SiteTag();
    	siteTag.setId(id);
    	siteTag.setFkSiteId(siteId);
    	siteTag.setType(type);
    	siteTag.setValue(value);
    	siteTagService.updateSiteTag(siteTag);
    	return ReturnMapUtil.packData("0");
    }
    
    @RequestMapping(value="/deleteTag", method= RequestMethod.POST)
    public Map<String,Object> deleteTag(HttpServletRequest request) {
    	Short id = Short.parseShort(request.getParameter("id"));
    	siteTagService.deleteSiteTag(id);
    	return ReturnMapUtil.packData("0");
    }
    
    public Integer checkReference(ArrayList<RSitezoneDevicezone> rSitezoneDevicezone,Short deviceZoneId,Short siteZoneId){
    	for (int i=0;i<rSitezoneDevicezone.size();i++){
    		RSitezoneDevicezone row = rSitezoneDevicezone.get(i);
    		if (row.getFkDeviceZoneId().equals(deviceZoneId) && row.getFkSiteZoneId().equals(siteZoneId)){
    			if (row.getReversed()!=null && row.getReversed()==true){
    				return 1;
    			} else {
    				return 0;
    			}
    		}
    	}
    	return -1;
    }
}

package net.iretailer.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.iretailer.rest.dao.LocationMapper;
import net.iretailer.rest.dao.SiteMapper;
import net.iretailer.rest.dao.SiteTagMapper;
import net.iretailer.rest.dao.SiteZoneMapper;
import net.iretailer.rest.model.Device;
import net.iretailer.rest.model.Site;
import net.iretailer.rest.model.SiteZone;
import net.iretailer.rest.service.DeviceService;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.SiteService;
import net.iretailer.rest.service.SiteZoneService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class MenuSettingsController {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private SiteZoneService sitezoneService;
	
	@Autowired
	private SiteTagMapper siteTagmapper;
	
	@Autowired
	private SiteZoneMapper sitezoneMapper;
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Autowired
	private SiteTagMapper siteTagMapper;
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("/getSiteList")
	public Map<String,Object> getSiteList(){
		ArrayList<Site> rootSites = siteService.getRootSites();
		ArrayList<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for (int i=0;i<rootSites.size();i++){
			Site site = rootSites.get(i);
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("id", site.getId()+"");
			dataMap.put("name", site.getDisplayName());
			dataMap.put("siteProperty", site.getType().toString());
			ArrayList<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
			ArrayList<Site> childrenSites = siteService.getSitesByParentSiteId(site.getId());
			for (int j=0;j<childrenSites.size();j++){
				Site childrenSite = childrenSites.get(j);
				if(site.getId()==childrenSite.getId()) continue;
				Map<String,Object> childrenMap = new HashMap<String,Object>();
				childrenMap.put("id", childrenSite.getId()+"");
				childrenMap.put("name", childrenSite.getDisplayName());
				childrenMap.put("siteProperty", childrenSite.getType().toString());
				children.add(childrenMap);
			}
			dataMap.put("children", children);
			dataList.add(dataMap);
		}

	   	return ReturnMapUtil.packData(dataList);
	}
	
	@RequestMapping("/getAllTagType")
	public Map<String,Object> getAllTagType(){
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		ArrayList<String> list = siteTagmapper.selectAllTagType(siteId);
		return ReturnMapUtil.packData(list);
	}
	
	@RequestMapping("/selectAllSiteZoneName")
	public Map<String,Object> selectAllSiteZoneName(){
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		Integer sitezoneType = Integer.parseInt(request.getParameter("sitezoneType"));
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("siteId", siteId);
		map.put("sitezoneType", sitezoneType-2);
		map.put("names", roleService.blockSite(request));
		ArrayList<Map<String,Object>> list = sitezoneMapper.selectAllSiteZoneName(map);
		return ReturnMapUtil.packData(list);
	}
	
	@RequestMapping("/getClassifyInfo")
	public Map<String,Object> getClassifyInfo(){
		Integer filterId = Integer.parseInt(request.getParameter("filterId"));
		Integer cateId = Integer.parseInt(request.getParameter("cateId"));
		Integer siteId = Integer.parseInt(request.getParameter("siteId"));
		if (filterId == 0 ){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("siteId", siteId);
			map.put("names", roleService.blockSite(request));
			ArrayList<Map<String,Object>> list = siteMapper.selectAllSiteName(map);

			return ReturnMapUtil.packData(list);
		}
		if (filterId == 1){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("siteId", siteId);
			map.put("findSite", 0);
			map.put("names", roleService.blockSite(request));
			ArrayList<Map<String,Object>> list = siteMapper.selectAllSiteName(map);
			return ReturnMapUtil.packData(list);
		}
		if ((filterId == 2)||(filterId==3)||(filterId==4)||(filterId==5)||(filterId==6)){
			Integer sitezoneType = Integer.parseInt(request.getParameter("sitezoneType"));
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("siteId", siteId);
			map.put("sitezoneType", sitezoneType-2);
			map.put("names", roleService.blockSite(request));
			ArrayList<Map<String,Object>> list = sitezoneMapper.selectAllSiteZoneName(map);
			return ReturnMapUtil.packData(list);
		}
		if (filterId == 7){
			String tagType = request.getParameter("tagType");
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("siteId", siteId);
			map.put("tagType", tagType);
			map.put("names", roleService.blockSite(request));
			ArrayList<Map<String,Object>> list = siteTagMapper.selectTagValue(map);
			return ReturnMapUtil.packData(list);
		}
		if ((filterId==8||filterId==9||filterId==10||filterId==11)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("siteId", siteId);
			map.put("locationType", filterId-8);
			map.put("names", roleService.blockSite(request));
			ArrayList<Map<String,Object>> list = locationMapper.selectAllLocationName(map);
			return ReturnMapUtil.packData(list);
		}
		return ReturnMapUtil.packData(null);
	}
	
    @RequestMapping("/getDeviceList")
    public Map<String,Object> getDeviceList() {
    	ArrayList<Site> siteList = siteService.getAllSite();
		Map<String,Object> resultMap = new HashMap<String,Object>();
    	Map<Short,Map<String,Object>> orderNumber= new HashMap<Short,Map<String,Object>>();
    	orderNumber.put(Short.parseShort("0"), resultMap);
    	for (int i = 0;i<siteList.size();i++){
    		Site site = siteList.get(i);
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("id",site.getId().toString());
    		map.put("name", site.getDisplayName());
    		map.put("type", "0");
    		orderNumber.put(site.getId(), map);
    	}
    	for (int i = 0;i<siteList.size();i++){
    		Site site = siteList.get(i);
    		Map<String,Object> map = orderNumber.get(site.getId());
    		Short parentSiteId = site.getParentId();
    		Map<String,Object> parentSiteMap = orderNumber.get(parentSiteId);
    		if (parentSiteMap == null) continue;
    		if (parentSiteMap.containsKey("children")){
    			@SuppressWarnings("unchecked")
				ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) parentSiteMap.get("children");
    			list.add(map);
    		} else {
    			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    			parentSiteMap.put("children", list);
    			list.add(map);
    		}
    	}
    	ArrayList<Device> deviceList = deviceService.getAllDevice();
    	for (int i=0;i<deviceList.size();i++){
    		Device device = deviceList.get(i);
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("id", device.getId());
    		map.put("name", device.getDisplayName());
    		map.put("type", "1");
    		Map<String,Object> parentSiteMap = orderNumber.get(device.getFkSiteId());
    		if (parentSiteMap == null) continue;
    		if (parentSiteMap.containsKey("children")){
    			@SuppressWarnings("unchecked")
				ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) parentSiteMap.get("children");
    			list.add(map);
    		} else {
    			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    			parentSiteMap.put("children", list);
    			list.add(map);
    		}
    	}
    	return ReturnMapUtil.packData(resultMap.get("children"));
    }
    
    @RequestMapping("/getSiteZoneList")
    public Map<String,Object> getSiteZoneList() {
    	ArrayList<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		ArrayList<Site> rootSites = siteService.getRootSites();
    	for (int i=0;i<rootSites.size();i++){
    		Site site = rootSites.get(i);
    		Map<String,Object> map = new HashMap<String,Object>();
    		map.put("id", site.getId().toString());
    		map.put("name", site.getDisplayName());
    		map.put("type", "0");
    		map.put("isSite", 1);
    		ArrayList<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
    		map.put("children",children);
     		ArrayList<Site> childrenSites = siteService.getSitesByParentSiteId(site.getId());
     		for (int j=0;j<childrenSites.size();j++){
     			if (childrenSites.get(j).getId()==site.getId()){
     				childrenSites.remove(j);
     				break;
     			}
     		}
     		for (int j=0;j<childrenSites.size();j++){
     			Site childrenSite = childrenSites.get(j);
        		Map<String,Object> childrenMap = new HashMap<String,Object>();
        		childrenMap.put("id", childrenSite.getId());
        		childrenMap.put("name", childrenSite.getDisplayName());
        		childrenMap.put("type", childrenSite.getType());
        		childrenMap.put("isSite", 1);
     			ArrayList<Map<String,Object>> childrenChildren = new ArrayList<Map<String,Object>>();
     			ArrayList<SiteZone> childrenSitezones =sitezoneService.getSiteZoneBySiteId(childrenSite.getId());
     			for (int k=0;k<childrenSitezones.size();k++){
            		Map<String,Object> childrenSitezoneMap = new HashMap<String,Object>();
            		childrenSitezoneMap.put("id", childrenSitezones.get(k).getId());
            		childrenSitezoneMap.put("name", childrenSitezones.get(k).getDisplayName());
            		childrenSitezoneMap.put("type", childrenSitezones.get(k).getType());
            		childrenSitezoneMap.put("children", null);
            		childrenSitezoneMap.put("isSite", 0);
            		childrenChildren.add(childrenSitezoneMap);
     			}
     			childrenMap.put("children", childrenChildren);
     			children.add(childrenMap);
     		}
 			ArrayList<SiteZone> sitezones =sitezoneService.getSiteZoneBySiteId(site.getId());
 			for (int k=0;k<sitezones.size();k++){
        		Map<String,Object> sitezoneMap = new HashMap<String,Object>();
        		sitezoneMap.put("id", sitezones.get(k).getId());
        		sitezoneMap.put("name", sitezones.get(k).getDisplayName());
        		sitezoneMap.put("type", sitezones.get(k).getType());
        		sitezoneMap.put("children", null);
        		sitezoneMap.put("isSite", 0);
        		children.add(sitezoneMap);
 			}
 			result.add(map);
    	}
    	return ReturnMapUtil.packData(result);
    }
}

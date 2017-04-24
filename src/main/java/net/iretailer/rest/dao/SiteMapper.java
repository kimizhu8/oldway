package net.iretailer.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.iretailer.rest.model.Site;

public interface SiteMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
    
    ArrayList<Site> selectSitesByParentId(Short parentId); 
    
    ArrayList<Site> selectAll();
    
    int deleteSites(Short[] siteIds);
    
    ArrayList<Site> selectParentSite();
    
    ArrayList<Map<String,Object>> selectAllSiteName(HashMap<String,Object> map);
    
    ArrayList<Map<String,Object>> selectSiteInfo(HashMap<String,Integer> map);
}
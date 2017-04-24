package net.iretailer.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.iretailer.rest.model.SiteZone;

public interface SiteZoneMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SiteZone record);

    int insertSelective(SiteZone record);

    SiteZone selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SiteZone record);

    int updateByPrimaryKey(SiteZone record);
    
    ArrayList<SiteZone> selectSiteZoneBySiteId(Short siteId);
    
    int deleteSitezones(Short[] ids);
    
    ArrayList<Map<String,Object>> selectAllSiteZoneName(HashMap<String,Object> map);
}
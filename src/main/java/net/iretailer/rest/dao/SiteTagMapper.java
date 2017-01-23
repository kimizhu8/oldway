package net.iretailer.rest.dao;

import java.util.ArrayList;
import java.util.Map;

import net.iretailer.rest.model.SiteTag;

public interface SiteTagMapper {
    int deleteByPrimaryKey(Short id);

    int insert(SiteTag record);

    int insertSelective(SiteTag record);

    SiteTag selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(SiteTag record);

    int updateByPrimaryKey(SiteTag record);
    
    ArrayList<SiteTag> selectBySiteId(Short siteId);
    
    ArrayList<String> selectAllTagType(Integer siteId);
    
    ArrayList<Map<String,Object>> selectTagValue(Map<String,Object> map);
}
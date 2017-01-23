package net.iretailer.rest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.iretailer.rest.model.Location;

public interface LocationMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);
    
    ArrayList<Location> selectAll();
    
    ArrayList<Map<String,Object>> selectAllLocationName(HashMap<String,Integer> map);

}
package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.Holiday;

public interface HolidayMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Holiday record);

    int insertSelective(Holiday record);

    Holiday selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Holiday record);

    int updateByPrimaryKey(Holiday record);
    
    ArrayList<Holiday> selectAll();
}
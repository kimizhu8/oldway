package net.iretailer.rest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import net.iretailer.rest.model.RSitezoneDevicezone;

public interface RSitezoneDevicezoneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RSitezoneDevicezone record);

    int insertSelective(RSitezoneDevicezone record);

    RSitezoneDevicezone selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RSitezoneDevicezone record);

    int updateByPrimaryKey(RSitezoneDevicezone record);
    
    Integer getRelatedTimes(Short devicezoneId);
    
    ArrayList<RSitezoneDevicezone> selectByDevicezoneId(Short devicezoneId);
    
    ArrayList<RSitezoneDevicezone> selectBySitezoneId(Short sitezoneId);
    
    ArrayList<RSitezoneDevicezone> selectAll();
    
    int deleteByRef(@Param("devicezoneId")Short devicezoneId,@Param("sitezoneId")Short siteZoneId);
}
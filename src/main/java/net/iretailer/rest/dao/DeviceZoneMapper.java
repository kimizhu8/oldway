package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.DeviceZone;

public interface DeviceZoneMapper {
    int deleteByPrimaryKey(Short id);

    int insert(DeviceZone record);

    int insertSelective(DeviceZone record);

    DeviceZone selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(DeviceZone record);

    int updateByPrimaryKey(DeviceZone record);
    
    ArrayList<DeviceZone> getDeviceZoneBySiteId(Short siteId);
    
    Integer getDevicezoneCount(Short id);
    
    ArrayList<DeviceZone> getDevicezoneByDeviceId(Short deviceId);
    
    int deleteDevicezones(Short[] ids);
    
    ArrayList<DeviceZone> getDeviceZoneBySiteIdTemp(Short siteId);
}
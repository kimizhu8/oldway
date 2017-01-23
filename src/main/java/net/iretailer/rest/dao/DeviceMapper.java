package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
    
    ArrayList<Device> getDeviceBySiteId(Short siteId);
    
    ArrayList<Device> getAllDevice();
    
    int deleteDeviceList(Short[] ids);
}
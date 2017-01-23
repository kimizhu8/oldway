package net.iretailer.rest.dao;

import net.iretailer.rest.model.RHolidaySite;

public interface RHolidaySiteMapper {
    int deleteByPrimaryKey(Short id);

    int insert(RHolidaySite record);

    int insertSelective(RHolidaySite record);

    RHolidaySite selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(RHolidaySite record);

    int updateByPrimaryKey(RHolidaySite record);
}
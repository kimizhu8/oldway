package net.iretailer.rest.dao;

import net.iretailer.rest.model.RecordsPassby;

public interface RecordsPassbyMapper {
    int deleteByPrimaryKey(Integer fkRecordsId);

    int insert(RecordsPassby record);

    int insertSelective(RecordsPassby record);

    RecordsPassby selectByPrimaryKey(Integer fkRecordsId);

    int updateByPrimaryKeySelective(RecordsPassby record);

    int updateByPrimaryKey(RecordsPassby record);
}
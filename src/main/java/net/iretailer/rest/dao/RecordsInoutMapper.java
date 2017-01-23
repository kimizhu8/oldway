package net.iretailer.rest.dao;

import net.iretailer.rest.model.RecordsInout;

public interface RecordsInoutMapper {
    int deleteByPrimaryKey(Integer fkRecordsId);

    int insert(RecordsInout record);

    int insertSelective(RecordsInout record);

    RecordsInout selectByPrimaryKey(Integer fkRecordsId);

    int updateByPrimaryKeySelective(RecordsInout record);

    int updateByPrimaryKey(RecordsInout record);
}
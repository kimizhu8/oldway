package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.OperationLog;

public interface OperationLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);
    
    ArrayList<OperationLog> getAllLog();
}
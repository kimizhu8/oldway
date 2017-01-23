package net.iretailer.rest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import net.iretailer.rest.dto.SalesDataDTO;
import net.iretailer.rest.model.MainRecords;
import net.iretailer.rest.model.RecordsSales;

public interface RecordsSalesMapper {
    int deleteByPrimaryKey(Integer fkRecordsId);

    int insert(RecordsSales record);

    int insertSelective(RecordsSales record);

    RecordsSales selectByPrimaryKey(Integer fkRecordsId);

    int updateByPrimaryKeySelective(RecordsSales record);

    int updateByPrimaryKey(RecordsSales record);
    
    int insertMainRecords(MainRecords mainRecords);
    
    int deleteMainRecords(Integer id);
    
    int updateMainRecords(MainRecords mainRecords);
    
    ArrayList<SalesDataDTO> selectAll(@Param("page") Integer page,@Param("pageSize") Integer pageSize,@Param("date") String date,@Param("siteId") Integer siteId);
    
    Integer getSalesDataCount(@Param("siteId") Integer siteId,@Param("date") String date);
}
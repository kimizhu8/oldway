package net.iretailer.rest.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import net.iretailer.rest.model.Subject;

public interface SubjectMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
    
    ArrayList<Subject> selectAll(@Param("subject") Integer siteId);
}
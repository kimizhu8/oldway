package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    ArrayList<Role> selectAll();
    
    Role selectByUserId(Integer id);
}
package net.iretailer.rest.dao;

import java.util.ArrayList;

import net.iretailer.rest.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    ArrayList<User> selectAll();
    
    User selectByUserInfo(User user);
    
    User selectByUserIdAndPassword(User user);
    
    User selectByUserName(String userName);
    
    void updateByUserNameSelective(User user);
}
package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.UserMapper;
import net.iretailer.rest.model.User;
import net.iretailer.rest.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void insertUser(User user) {
		userMapper.insertSelective(user);
		
	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKey(user);
		
	}

	@Override
	public ArrayList<User> selectAllUser() {
		return userMapper.selectAll();
	}

	@Override
	public User login(User user) {
		return userMapper.selectByUserInfo(user);
	}

	@Override
	public void updateUserPassword(User user) {
		userMapper.updateByUserNameSelective(user);
	}

	@Override
	public User check(User user) {
		return userMapper.selectByUserIdAndPassword(user);
	}

}

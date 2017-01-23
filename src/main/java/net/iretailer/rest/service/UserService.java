package net.iretailer.rest.service;

import java.util.ArrayList;

import net.iretailer.rest.model.User;

public interface UserService {
	public void insertUser(User user);
	public void deleteUser(Integer id);
	public void updateUser(User user);
	public ArrayList<User> selectAllUser();
	
	public User login(User user);
	
	public User check(User user);
	
	public void updateUserPassword(User user);
	
}

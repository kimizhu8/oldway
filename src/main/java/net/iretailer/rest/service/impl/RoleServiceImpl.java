package net.iretailer.rest.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.iretailer.rest.dao.RoleMapper;
import net.iretailer.rest.dao.UserMapper;
import net.iretailer.rest.model.Role;
import net.iretailer.rest.model.User;
import net.iretailer.rest.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	@Override
	public void insertRole(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void deleteRole(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public ArrayList<Role> getAllRole() {
		return roleMapper.selectAll();
	}

	@Override
	public Role getRoleByUserId(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Role getRoleById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public Boolean blockRole(HttpServletRequest request, Integer siteId, Integer menuId) {
		String loginToken = request.getHeader("Authorization");
		String userName = loginToken.split("/")[0];
		
		User user =userMapper.selectByUserName(userName);
		Integer roleId = user.getRoleId();
		Role role = roleMapper.selectByPrimaryKey(roleId);
		String[] MenuList = role.getMenu().split(",");
		
		Boolean flag1 = false;
		for (int i=0;i<MenuList.length;i++){
			if (Integer.parseInt(MenuList[i])==menuId){
				flag1 = true;
			}
		}
		Boolean flag2 = false;
		String[] siteList = role.getRoleSite().split(",");
		for (int i=0;i<siteList.length;i++){
			if (Integer.parseInt(siteList[i])==siteId){
				flag2 = true;
			}
		}
		return flag1&&flag2;
	}

	@Override
	public Boolean blockRole(HttpServletRequest request, Integer menuId) {
		String loginToken = request.getHeader("Authorization");
		String userName = loginToken.split("/")[0];
		
		User user =userMapper.selectByUserName(userName);
		Integer roleId = user.getRoleId();
		Role role = roleMapper.selectByPrimaryKey(roleId);
		String[] MenuList = role.getMenu().split(",");
		
		Boolean flag1 = false;
		for (int i=0;i<MenuList.length;i++){
			if (Integer.parseInt(MenuList[i])==menuId){
				flag1 = true;
			}
		}
		return flag1;
	}
	@Override
	public Integer[] blockSite(HttpServletRequest request) {
		String loginToken = request.getHeader("Authorization");
		String userName = loginToken.split("/")[0];
		
		User user =userMapper.selectByUserName(userName);
		Integer roleId = user.getRoleId();
		Role role = roleMapper.selectByPrimaryKey(roleId);
		String[] siteList = role.getRoleSite().split(",");
		
		Integer[] result = new Integer[siteList.length];
		for (int i=0;i<siteList.length;i++){
			result[i] = Integer.parseInt(siteList[i]);
		}
		return result;
	}

}

package net.iretailer.rest.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import net.iretailer.rest.model.Role;

public interface RoleService {
	public void insertRole(Role role);
	public void deleteRole(Integer id);
	public void updateRole(Role role);
	public ArrayList<Role> getAllRole();
	
	public Role getRoleByUserId(Integer id);
	
	public Role getRoleById(Integer id);
	
	public Boolean blockRole(HttpServletRequest request,Integer siteId,Integer menuId);
	
	public Boolean blockRole(HttpServletRequest request,Integer menuId);
	
	public Integer[] blockSite(HttpServletRequest request);
}

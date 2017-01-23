package net.iretailer.rest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.iretailer.rest.filter.CommonFilter;
import net.iretailer.rest.model.Role;
import net.iretailer.rest.model.User;
import net.iretailer.rest.service.RoleService;
import net.iretailer.rest.service.UserService;
import net.iretailer.rest.util.ReturnMapUtil;

@RestController
public class UserController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/newUser", method = RequestMethod.GET)
	public Map<String,Object> newUser() throws ParseException{
		if (!roleService.blockRole(request,64)) return ReturnMapUtil.packData("恶意登录");
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String emailAddress = request.getParameter("emailAddress");
		String phoneNumber = request.getParameter("phoneNumber");
		String company = request.getParameter("company");
		String desc = request.getParameter("description");
		Boolean lock =Boolean.parseBoolean(request.getParameter("locked"));
		User user =new User();
		user.setCompany(company);
		user.setDescription(desc);
		user.setEmailAddress(emailAddress);
		user.setPassWord(password);
		user.setPhoneNumber(phoneNumber);
		user.setRoleId(roleId);
		user.setUserName(userName);
		user.setLocked(lock);
		userService.insertUser(user);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateUser", method = RequestMethod.GET)
	public Map<String,Object> updateUser() throws ParseException{
		if (!roleService.blockRole(request,64)) return ReturnMapUtil.packData("恶意登录");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		String userName = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String emailAddress = request.getParameter("emailAddress");
		String phoneNumber = request.getParameter("phoneNumber");
		String company = request.getParameter("company");
		String desc = request.getParameter("description");
		Boolean lock =Boolean.parseBoolean(request.getParameter("locked"));
		User user =new User();
		user.setUserId(userId);
		user.setCompany(company);
		user.setDescription(desc);
		user.setEmailAddress(emailAddress);
		user.setPassWord(password);
		user.setPhoneNumber(phoneNumber);
		user.setRoleId(roleId);
		user.setUserName(userName);
		user.setLocked(lock);
		userService.updateUser(user);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateUserPassword", method = RequestMethod.GET)
	public Map<String,Object> updateUserPassword() throws ParseException{
		
		String loginToken = request.getParameter("userId");
		String userName = loginToken.split("/")[0];
		String oldPassword = request.getParameter("passWord");
		User oldUser = new User();
		oldUser.setUserName(userName);
		oldUser.setPassWord(oldPassword);
		User finalUser = userService.login(oldUser);
		if (finalUser == null) return ReturnMapUtil.packData("1");
		String password = request.getParameter("newPassWord");
		User user = new User();
		user.setPassWord(password);
		user.setUserName(userName);
		userService.updateUserPassword(user);
		return ReturnMapUtil.packData("0");
	}
	@RequestMapping(value="/deleteUser", method = RequestMethod.GET)
	public Map<String,Object> deleteUser() throws ParseException{
		if (!roleService.blockRole(request,64)) return ReturnMapUtil.packData("恶意登录");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		userService.deleteUser(userId);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllUser", method = RequestMethod.GET)
	public Map<String,Object> getAllUser() throws ParseException{
		ArrayList<User> users = userService.selectAllUser();
		return ReturnMapUtil.packData(users);
	}
	
	@RequestMapping(value="/newRole", method = RequestMethod.GET)
	public Map<String,Object> newRole() throws ParseException{
		if (!roleService.blockRole(request,63)) return ReturnMapUtil.packData("恶意登录");
		Integer operationType = Integer.parseInt(request.getParameter("operationType"));
		String menu = request.getParameter("menu");
		String roleName = request.getParameter("roleName");
		String homeSettings = request.getParameter("homeSettings");
		String iconAddress = request.getParameter("iconAddress");
		String logoAddress = request.getParameter("logoAddress");
		String backgroundAddress = request.getParameter("backgroundAddress");
		String bottomText = request.getParameter("bottomText");
		String roleSite = request.getParameter("roleSite");
		Role role = new Role();
		role.setOperationType(operationType);
		role.setMenu(menu);
		role.setRoleName(roleName);
		role.setHomeSettings(homeSettings);
		role.setIconAddress(iconAddress);
		role.setLogoAddress(logoAddress);
		role.setBackgroundAddress(backgroundAddress);
		role.setBottomText(bottomText);
		role.setRoleSite(roleSite);
		roleService.insertRole(role);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/updateRole", method = RequestMethod.GET)
	public Map<String,Object> updateRole() throws ParseException{
		if (!roleService.blockRole(request,63)) return ReturnMapUtil.packData("恶意登录");
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		Integer operationType = Integer.parseInt(request.getParameter("operationType"));
		String menu = request.getParameter("menu");
		String roleName = request.getParameter("roleName");
		String homeSettings = request.getParameter("homeSettings");
		String iconAddress = request.getParameter("iconAddress");
		String logoAddress = request.getParameter("logoAddress");
		String backgroundAddress = request.getParameter("backgroundAddress");
		String bottomText = request.getParameter("bottomText");
		String roleSite = request.getParameter("roleSite");
		Role role = new Role();
		role.setRoleId(roleId);
		role.setOperationType(operationType);
		role.setMenu(menu);
		role.setRoleName(roleName);
		role.setHomeSettings(homeSettings);
		role.setIconAddress(iconAddress);
		role.setLogoAddress(logoAddress);
		role.setBackgroundAddress(backgroundAddress);
		role.setBottomText(bottomText);
		role.setRoleSite(roleSite);
		roleService.updateRole(role);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/deleteRole", method = RequestMethod.GET)
	public Map<String,Object> deleteRole() throws ParseException{
		if (!roleService.blockRole(request,63)) return ReturnMapUtil.packData("恶意登录");
		Integer roleId = Integer.parseInt(request.getParameter("roleId"));
		roleService.deleteRole(roleId);
		return ReturnMapUtil.packData("0");
	}
	
	@RequestMapping(value="/getAllRole", method = RequestMethod.GET)
	public Map<String,Object> getAllRole() throws ParseException{
		ArrayList<Role> roles = roleService.getAllRole();
		for (int i=0;i<roles.size();i++){
			if (roles.get(i).getRoleSite()!=null)
			roles.get(i).setRoleSiteArray(roles.get(i).getRoleSite().split(","));
		}
		return ReturnMapUtil.packData(roles);
	}
	
	@RequestMapping(value="/getRoleById", method = RequestMethod.GET)
	public Map<String,Object> getRoleById() throws ParseException{
		Role role = null;
		if (request.getParameter("id")=="") {
			role = roleService.getRoleById(0);
		} else {
			role = roleService.getRoleById(Integer.parseInt(request.getParameter("id")));
		}

		if (role.getRoleSite()!=null)
		role.setRoleSiteArray(role.getRoleSite().split(","));
		return ReturnMapUtil.packData(role);
	}
	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public Map<String,Object> login() throws ParseException{
		User user = new User();
		String userName = request.getParameter("userName");
		String password = request.getParameter("passWord"); 
		String uuid = request.getParameter("UUID");
		user.setUserName(userName);
		user.setPassWord(password);
		User finalUser = userService.login(user);
		if (finalUser == null) {
			return ReturnMapUtil.packData("1");
		} else {
			Integer roleId = finalUser.getRoleId();
			Role finalRole = roleService.getRoleByUserId(roleId);
			finalRole.setRoleSiteArray(finalRole.getRoleSite().split(","));
			UUID myUUID = UUID.randomUUID();
			String loginToken = "";
			loginToken +=userName + "/" + uuid + "/" + myUUID;
			CommonFilter.cacheManager.updateCache(loginToken, System.currentTimeMillis());
			finalRole.setUserName(loginToken);
			return ReturnMapUtil.packData(finalRole);
		}
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public Map<String,Object> logout() throws ParseException{
		return ReturnMapUtil.packData("1");
	}
}

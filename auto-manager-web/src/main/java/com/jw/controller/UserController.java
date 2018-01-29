package com.jw.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.bean.User;
import com.jw.common.AutoResult;
import com.jw.common.AutoTree;
import com.jw.common.JqgridResult;
import com.jw.service.RoleService;
import com.jw.service.UserService;
import com.jw.shiro.realm.CustomRealm;
import com.jw.util.DateUtil;
import com.jw.util.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private CustomRealm customRealm;
	
	@RequestMapping("/toIndex")
	@RequiresPermissions("user:list")
	public String toIndex() {
		
		return "user/userIndex";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("user:list")
	public @ResponseBody JqgridResult<User> list(User user) {
		JqgridResult<User> result;
		try {
			result = userService.list(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * 用户新增
	 * @param user 接收用户信息
	 * @return 响应结果
	 */
	@RequestMapping("/insert")
	@RequiresPermissions("user:insert")
	public @ResponseBody AutoResult insert(User user, String strUserBirthday) {
		Date userBirthday = null;
		
		if (strUserBirthday != null && !strUserBirthday.equals("")) {
			userBirthday = DateUtil.stringToDate(strUserBirthday, "yyyy-MM-dd");
			user.setUserBirthday(userBirthday);
		}
		
		// 设置密码
		user.setUserPwd(MD5Util.getMD5(user.getUserPwd(), user.getUserPhone()));
		user.setUserSalt(user.getUserPhone());
		user.setUserCreatedTime(new Date());
		// 设置默认头像
		user.setUserIcon("img/default.png");
		
		try {
			userService.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	/**
	 * 用户删除
	 * @param ids userId数组
	 * @return 响应结果
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("user:delete")
	public @ResponseBody AutoResult delete(String[] ids) {
		try {
			userService.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("删除失败");
		}
		return AutoResult.success();
	}
	
	/**
	 * 判断用户邮箱是否存在
	 * @return 
	 * 		true 存在
	 * 		false 不存在
	 */
	@RequestMapping("/getByEmail")
	public @ResponseBody Boolean getByEmail(User user) {
		User user2;
		try {
			user2 = userService.get(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// 邮箱不存在
		if (user2 == null) {
			return false;
		}
		// 邮箱存在
		return true;
	}
	
	/**
	 * 根据id查找用户
	 * @return 用户对象
	 */
	@RequestMapping("/getById")
	@RequiresPermissions("user:update")
	public @ResponseBody User getById(User user) {
		User user2;
		try {
			user2 = userService.get(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user2;
	}
	
	/**
	 * 修改用户信息
	 * @return 响应结果
	 */
	@RequestMapping("/update")
	@RequiresPermissions("user:update")
	public @ResponseBody AutoResult update(User user, String strUserBirthday, String strUserCreatedTime, String strUserLoginedTime) {
		Date userBirthday = null;
		Date userCreatedTime = null;
		Date userLoginedTime = null;
		
		if (strUserBirthday != null && !strUserBirthday.equals("")) {
			userBirthday = DateUtil.stringToDate(strUserBirthday, "yyyy-MM-dd");
		}
		if (strUserCreatedTime != null && !strUserCreatedTime.equals("")) {
			userCreatedTime = DateUtil.stringToDate(strUserCreatedTime, "yyyy-MM-dd HH:mm:ss");
		}
		if (strUserLoginedTime != null && !strUserLoginedTime.equals("")) {
			userLoginedTime = DateUtil.stringToDate(strUserLoginedTime, "yyyy-MM-dd HH:mm:ss");
		}
		
		user.setUserBirthday(userBirthday);
		user.setUserCreatedTime(userCreatedTime);
		user.setUserLoginedTime(userLoginedTime);
		
		try {
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}
	
	/**
	 * 根据用户id查询对应角色
	 * @param userId 用户id
	 * @return 角色tree
	 */
	@RequestMapping("/getRolesById")
	@RequiresPermissions("user:role")
	public @ResponseBody List<AutoTree> getRolesById(String userId) {
		List<AutoTree> list;
		try {
			list = userService.getRolesById(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * 用户添加角色
	 * @param ids 角色数组
	 * @param userId 用户id
	 * @return 返回结果
	 */
	@RequestMapping("/insertRole")
	@RequiresPermissions("user:role")
	public @ResponseBody AutoResult insertRole(String[] ids, String userId) {
		try {
			userService.insertRole(ids, userId);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("操作失败");
		}
		customRealm.clearCached();
		return AutoResult.success();
	}
	
}

package com.jw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.model.Role;
import com.jw.model.common.AutoResult;
import com.jw.model.common.AutoTree;
import com.jw.model.common.JqgridResult;
import com.jw.service.RoleService;
import com.jw.shiro.realm.CustomRealm;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private CustomRealm customRealm;
	
	@RequestMapping("/toIndex")
	@RequiresPermissions("role:list")
	public String toIndex() {
		
		return "role/roleIndex";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("role:list")
	public @ResponseBody JqgridResult<Role> list(Role role) {
		JqgridResult<Role> result = roleService.list(role);
		
		return result;
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("role:insert")
	public @ResponseBody AutoResult insert(Role role) {
		AutoResult autoResult = roleService.insert(role);
		
		return autoResult;
	}
	
	@RequestMapping("/getById")
	@RequiresPermissions("role:update")
	public @ResponseBody Role getById(Role role) {
		Role role2 = roleService.get(role);
		
		return role2;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("role:update")
	public @ResponseBody AutoResult update(Role role) {
		AutoResult autoResult = roleService.update(role);
		
		return autoResult;
	}
	
	/**
	 * 根据角色id查询对应权限
	 * @param roleId 角色id
	 * @return 权限
	 */
	@RequestMapping("/getPermissionsByRoleId")
	@RequiresPermissions("role:permission")
	public @ResponseBody List<AutoTree> getPermissionsByRoleId(String roleId) {
		List<AutoTree> list = roleService.getPermissionsByRoleId(roleId);
		
		return list;
	}
	
	/**
	 * 授权
	 * @param ids 权限id
	 * @param roleId 角色id
	 * @return 返回结果
	 */
	@RequestMapping("/insertPermission")
	@RequiresPermissions("role:permission")
	public @ResponseBody AutoResult insertPermission(String[] ids, String roleId) {
		AutoResult autoResult = roleService.insertPermission(ids, roleId);
		
		customRealm.clearCached();
		
		return autoResult;
	}
}

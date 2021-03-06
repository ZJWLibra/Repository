package com.jw.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.bean.Role;
import com.jw.common.AutoResult;
import com.jw.common.AutoTree;
import com.jw.common.JqgridResult;
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
		JqgridResult<Role> result;
		try {
			result = roleService.list(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("role:insert")
	public @ResponseBody AutoResult insert(Role role) {
		try {
			roleService.insert(role);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	@RequestMapping("/getById")
	@RequiresPermissions("role:update")
	public @ResponseBody Role getById(Role role) {
		Role role2;
		try {
			role2 = roleService.get(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return role2;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("role:update")
	public @ResponseBody AutoResult update(Role role) {
		try {
			roleService.update(role);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改");
		}
		return AutoResult.success();
	}
	
	/**
	 * 根据角色id查询对应权限
	 * @param roleId 角色id
	 * @return 权限
	 */
	@RequestMapping("/getPermissionsByRoleId")
	@RequiresPermissions("role:permission")
	public @ResponseBody List<AutoTree> getPermissionsByRoleId(String roleId) {
		List<AutoTree> list;
		try {
			list = roleService.getPermissionsByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		try {
			roleService.insertPermission(ids, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("授权失败");
		}
		customRealm.clearCached();
		return AutoResult.success();
	}
	
}

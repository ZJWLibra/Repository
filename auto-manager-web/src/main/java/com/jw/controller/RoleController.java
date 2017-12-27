package com.jw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jw.model.Role;
import com.jw.model.common.AutoResult;
import com.jw.model.common.AutoTree;
import com.jw.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/toIndex")
	@RequiresPermissions("role:list")
	public String toIndex() {
		
		return "role/roleIndex";
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("role:list")
	public @ResponseBody Map<String, Object> list(Role role) {
		// 总记录
		role.setRecords(roleService.count(role));
		// 总页数
		role.setTotal((role.getRecords() + role.getRows() - 1) / role.getRows());
		// 开始查询第几条数据
		role.setStartData((role.getPage() - 1) * role.getRows());
		
		List<Role> list = roleService.list(role);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("page", role.getPage());
		map.put("records", role.getRecords());
		map.put("total", role.getTotal());
		map.put("rows", list);
		
		return map;
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
		
		return autoResult;
	}
}

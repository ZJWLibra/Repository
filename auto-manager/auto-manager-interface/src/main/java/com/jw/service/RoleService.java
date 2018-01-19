package com.jw.service;

import java.util.List;

import com.jw.common.AutoResult;
import com.jw.common.AutoTree;
import com.jw.model.Role;

public interface RoleService extends BaseService<Role> {
	
	/**
	 * 根据角色id查询对应权限
	 * @param roleId 角色id
	 * @return 权限tree
	 */
	List<AutoTree> getPermissionsByRoleId(String roleId);
	
	/**
	 * 授权
	 * @param ids 权限id
	 * @param roleId 角色id
	 * @return 返回结果
	 */
	AutoResult insertPermission(String[] ids, String roleId);
}

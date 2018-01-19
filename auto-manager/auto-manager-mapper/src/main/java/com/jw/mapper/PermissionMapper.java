package com.jw.mapper;

import java.util.List;

import com.jw.model.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {
	
	/**
	 * 根据角色id查询对应权限
	 * @param roleId 角色id
	 * @return 权限
	 * @throws Exception
	 */
	List<Permission> getPermissionsByRoleId(String roleId) throws Exception;
	
	/**
	 * 根据用户id查询对应权限
	 * @param userId 用户id
	 * @return 权限
	 * @throws Exception
	 */
	List<Permission> getPermissionsByUserId(String userId) throws Exception;
}

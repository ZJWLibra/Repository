package com.jw.mapper;

import java.util.List;

import com.jw.model.Role;

public interface RoleMapper extends BaseMapper<Role> {
	
	/**
	 * 根据用户id查询对应角色
	 * @param userId 用户id
	 * @return 角色
	 * @throws Exception
	 */
	List<Role> getRolesByUserId(String userId) throws Exception;
}

package com.jw.service;

import java.util.List;

import com.jw.common.AutoResult;
import com.jw.common.AutoTree;
import com.jw.model.Permission;
import com.jw.model.User;

public interface UserService extends BaseService<User> {
	
	User getUserByName(String username);
	
	User getByEmail(User user);
	
	/**
	 * 根据用户id查询对应角色
	 * @param userId 用户id
	 * @return 角色集合
	 */
	List<AutoTree> getRolesById(String userId);
	
	/**
	 * 用户添加角色
	 * @param ids 角色id数组
	 * @param userId 用户id
	 * @return 返回结果
	 */
	AutoResult insertRole(String[] ids, String userId);
	
	/**
	 * 根据用户id查询对应权限
	 * @param userId 用户id
	 * @return 权限
	 */
	List<Permission> getPermissionsByUserId(String userId);
	
}

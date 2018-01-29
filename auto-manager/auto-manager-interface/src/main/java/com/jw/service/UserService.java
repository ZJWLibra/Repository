package com.jw.service;

import java.util.List;

import com.jw.bean.Permission;
import com.jw.bean.User;
import com.jw.common.AutoTree;

public interface UserService extends BaseService<User> {
	
	User getUserByName(String username) throws Exception;
	
	User getByEmail(User user) throws Exception;
	
	/**
	 * 根据用户id查询对应角色
	 * @param userId 用户id
	 * @return 角色集合
	 */
	List<AutoTree> getRolesById(String userId) throws Exception;
	
	/**
	 * 用户添加角色
	 * @param ids 角色id数组
	 * @param userId 用户id
	 * @return 返回结果
	 */
	void insertRole(String[] ids, String userId) throws Exception;
	
	/**
	 * 根据用户id查询对应权限
	 * @param userId 用户id
	 * @return 权限
	 */
	List<Permission> getPermissionsByUserId(String userId) throws Exception;
	
}

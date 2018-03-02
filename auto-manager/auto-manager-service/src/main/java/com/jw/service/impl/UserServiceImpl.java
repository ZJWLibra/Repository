package com.jw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.Permission;
import com.jw.bean.Role;
import com.jw.bean.User;
import com.jw.bean.UserRole;
import com.jw.bean.ext.UserExt;
import com.jw.common.AutoTree;
import com.jw.common.JqgridResult;
import com.jw.mapper.PermissionMapper;
import com.jw.mapper.RoleMapper;
import com.jw.mapper.UserMapper;
import com.jw.mapper.UserRoleMapper;
import com.jw.service.UserService;
import com.jw.util.ShiroUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserRoleMapper userRoleMapper;
	@Resource
	private PermissionMapper permissionMapper;
	
	@Value("${SUPER_ADMIN_USERID}")
	private String SUPER_ADMIN_USERID;

	@Override
	public void insert(User t) throws Exception {
		userMapper.insert(t);
	}
	
	@Override
	public void insertSuperAdmin(User user) throws Exception {
		user.setUserNickname("管理员");
		userMapper.insert(user);
		// 查询普通管理员信息
		Role role = new Role();
		role.setRoleName("systemOrdinaryAdmin");
		role = roleMapper.get(role);
		// 将要添加的管理员设置角色为普通管理员
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(role.getRoleId());
		userRole.setUrCreatedTime(new Date());
		userRoleMapper.insert(userRole);
	}
	
	@Override
	public void delete(String[] ids) throws Exception {
		User user = null;
		for (String id : ids) {
			user = new User();
			user.setUserId(id);
			userMapper.delete(user);
		}
	}

	@Override
	public void update(User t) throws Exception {
		userMapper.update(t);
	}

	@Override
	public JqgridResult<User> list(User t) throws Exception {
		// 添加默认排序规则
		if (t.getSidx() == null || t.getSidx().equals("")) {
			// 根据创建时间倒序
			t.setSidx("userCreatedTime");
			t.setSord("desc");
		}
		JqgridResult<User> result = new JqgridResult<>();
		Long count = userMapper.count(t);
		List<User> list = userMapper.list(t);
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public User get(User t) throws Exception {
		return userMapper.get(t);
	}

	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public User getUserByName(String username) throws Exception {
		User user = new User();
		user.setUserPhone(username);
		return userMapper.get(user);
	}

	/**
	 * 判断用户邮箱是否存在
	 */
	@Override
	public User getByEmail(User user) throws Exception {
		return userMapper.get(user);
	}
	
	/**
	 * 根据用户id查询对应角色
	 */
	@Override
	public List<AutoTree> getRolesById(String userId) throws Exception {
		// 所有角色
		List<Role> roleList = roleMapper.list(new Role());
		// 用户角色
		List<Role> userRoleList = roleMapper.getRolesByUserId(userId);
		List<AutoTree> autoTreeList = new ArrayList<>();
		autoTreeList.add(new AutoTree("1", "", "所有角色", Boolean.FALSE, Boolean.TRUE));
		// 将角色添加到tree中
		for (Role role : roleList) {
			AutoTree autoTree = new AutoTree();
			autoTree.setId(role.getRoleId());
			autoTree.setpId("1");
			autoTree.setName(role.getRoleDes());
			// 判断是否已拥有当前角色
			for (Role role2 : userRoleList) {
				if (role.getRoleId().equals(role2.getRoleId())) {
					autoTree.setChecked(Boolean.TRUE);
					autoTreeList.get(0).setChecked(true);
				}
			}
			autoTreeList.add(autoTree);
		}
		return autoTreeList;
	}

	/**
	 * 用户添加角色
	 */
	@Override
	public void insertRole(String[] ids, String userId) throws Exception {
		// 删除原有角色
		UserRole ur = new UserRole();
		ur.setUserId(userId);
		userRoleMapper.delete(ur);
		// 为用户重新添加角色
		UserRole userRole = null;
		for (String id : ids) {
			if (id.equals("1")) {
				continue;
			}
			userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(id);
			userRole.setUrCreatedTime(new Date());
			userRoleMapper.insert(userRole);
		}
	}

	/**
	 * 根据用户id查询对应权限
	 */
	@Override
	public List<Permission> getPermissionsByUserId(String userId) throws Exception {
		return permissionMapper.getPermissionsByUserId(userId);
	}

}

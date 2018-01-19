package com.jw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.common.AutoResult;
import com.jw.common.AutoTree;
import com.jw.common.JqgridResult;
import com.jw.mapper.PermissionMapper;
import com.jw.mapper.RoleMapper;
import com.jw.mapper.UserMapper;
import com.jw.mapper.UserRoleMapper;
import com.jw.model.Permission;
import com.jw.model.Role;
import com.jw.model.User;
import com.jw.model.UserRole;
import com.jw.service.UserService;

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

	@Override
	public AutoResult insert(User t) {
		try {
			userMapper.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("添加失败");
		}
		return AutoResult.ok();
	}
	
	@Override
	public AutoResult delete(String[] ids) {
		User user = null;
		
		for (String id : ids) {
			user = new User();
			user.setUserId(id);
			try {
				userMapper.delete(user);
			} catch (Exception e) {
				e.printStackTrace();
				return AutoResult.error("删除失败");
			}
		}
		
		return AutoResult.ok();
	}

	@Override
	public AutoResult update(User t) {
		try {
			userMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.ok();
	}

	@Override
	public JqgridResult<User> list(User t) {
		JqgridResult<User> result = new JqgridResult<>();
		Long count;
		List<User> list;
		// 添加默认排序规则
		if (t.getSidx() == null || t.getSidx().equals("")) {
			// 根据创建时间倒序
			t.setSidx("userCreatedTime");
			t.setSord("desc");
		}
		try {
			count = userMapper.count(t);
			list = userMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		
		return result;
	}

	@Override
	public User get(User t) {

		try {
			return userMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public User getUserByName(String username) {
		User user = new User();

		user.setUserPhone(username);

		try {
			return userMapper.get(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 判断用户邮箱是否存在
	 */
	@Override
	public User getByEmail(User user) {
		try {
			return userMapper.get(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据用户id查询对应角色
	 */
	@Override
	public List<AutoTree> getRolesById(String userId) {
		// 所有角色
		List<Role> roleList = null;
		
		try {
			roleList = roleMapper.list(new Role());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// 用户角色
		List<Role> userRoleList = null;
		
		try {
			userRoleList = roleMapper.getRolesByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
	public AutoResult insertRole(String[] ids, String userId) {
		// 删除原有角色
		UserRole ur = new UserRole();
		ur.setUserId(userId);
		try {
			userRoleMapper.delete(ur);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("添加失败");
		}
		
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
			try {
				userRoleMapper.insert(userRole);
			} catch (Exception e) {
				e.printStackTrace();
				return AutoResult.error("添加失败");
			}
		}
		
		return AutoResult.ok();
	}

	/**
	 * 根据用户id查询对应权限
	 */
	@Override
	public List<Permission> getPermissionsByUserId(String userId) {
		
		try {
			return permissionMapper.getPermissionsByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

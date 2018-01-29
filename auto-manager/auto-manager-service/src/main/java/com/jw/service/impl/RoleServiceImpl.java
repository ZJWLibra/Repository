package com.jw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.Permission;
import com.jw.bean.Role;
import com.jw.bean.RolePermission;
import com.jw.common.AutoTree;
import com.jw.common.JqgridResult;
import com.jw.mapper.PermissionMapper;
import com.jw.mapper.RoleMapper;
import com.jw.mapper.RolePermissionMapper;
import com.jw.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private PermissionMapper permissionMapper;
	@Resource
	private RolePermissionMapper rolePermissionMapper;
	
	@Override
	public void insert(Role t) throws Exception {
		roleMapper.insert(t);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		Role role = null;
		for (String id : ids) {
			role = new Role();
			role.setRoleId(id);
			roleMapper.delete(role);
		}
	}

	@Override
	public void update(Role t) throws Exception {
		roleMapper.update(t);
	}

	@Override
	public JqgridResult<Role> list(Role t) throws Exception {
		Long count = roleMapper.count(t);
		List<Role> list = roleMapper.list(t);
		JqgridResult<Role> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}
	
	@Override
	public Role get(Role t) throws Exception {
		return roleMapper.get(t);
	}

	/**
	 * 根据角色id查询对应权限
	 */
	@Override
	public List<AutoTree> getPermissionsByRoleId(String roleId) throws Exception {
		// 所有权限
		List<Permission> permissionList = permissionMapper.list(new Permission());
		// 角色拥有的权限
		List<Permission> rolePermissionList = permissionMapper.getPermissionsByRoleId(roleId);
		List<AutoTree> autoTreeList = new ArrayList<>();
		AutoTree autoTree = null;
		if (permissionList != null && permissionList.size() > 0) {
			for (Permission permission : permissionList) {
				autoTree = new AutoTree();
				autoTree.setId(permission.getPermissionId());
				autoTree.setName(permission.getPermissionName());
				autoTree.setpId(permission.getPermissionParentid());
				for (Permission userPermission : rolePermissionList) {
					// 已有权限默认选中
					if (permission.getPermissionId().equals(userPermission.getPermissionId())) {
						autoTree.setChecked(Boolean.TRUE);
					}
				}
				autoTreeList.add(autoTree);
			}
		}
		return autoTreeList;
	}

	/**
	 * 授权
	 */
	@Override
	public void insertPermission(String[] ids, String roleId) throws Exception {
		// 删除原有权限
		RolePermission rp = new RolePermission();
		rp.setRoleId(roleId);
		rolePermissionMapper.delete(rp);
		// 授权
		RolePermission rolePermission = null;
		for (String id : ids) {
			rolePermission = new RolePermission();
			rolePermission.setPermissionId(id);
			rolePermission.setRoleId(roleId);
			rolePermission.setRpCreatedTime(new Date());
			rolePermissionMapper.insert(rolePermission);
		}
	}

}

package com.jw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.mapper.PermissionMapper;
import com.jw.mapper.RoleMapper;
import com.jw.mapper.RolePermissionMapper;
import com.jw.model.Permission;
import com.jw.model.Role;
import com.jw.model.RolePermission;
import com.jw.model.common.AutoResult;
import com.jw.model.common.AutoTree;
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
	public AutoResult insert(Role t) {
		try {
			roleMapper.insert(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("添加失败");
		}
		
		return AutoResult.ok();
	}

	@Override
	public AutoResult delete(String[] ids) {
		Role role = null;
		
		for (String id : ids) {
			role = new Role();
			role.setRoleId(id);
			try {
				roleMapper.delete(role);
			} catch (Exception e) {
				e.printStackTrace();
				return AutoResult.error("删除失败");
			}
		}
		
		return AutoResult.ok();
	}

	@Override
	public AutoResult update(Role t) {
		try {
			roleMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		
		return AutoResult.ok();
	}

	@Override
	public List<Role> list(Role t) {
		try {
			return roleMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Role get(Role t) {
		try {
			return roleMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Integer count(Role t) {
		try {
			return roleMapper.count(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 根据角色id查询对应权限
	 */
	@Override
	public List<AutoTree> getPermissionsByRoleId(String roleId) {
		// 所有权限
		List<Permission> permissionList = null;
		try {
			permissionList = permissionMapper.list(new Permission());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		// 角色拥有的权限
		List<Permission> rolePermissionList = null;
		try {
			rolePermissionList = permissionMapper.getPermissionsByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
	public AutoResult insertPermission(String[] ids, String roleId) {
		// 删除原有权限
		RolePermission rp = new RolePermission();
		rp.setRoleId(roleId);
		try {
			rolePermissionMapper.delete(rp);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("授权失败");
		}
		
		// 授权
		RolePermission rolePermission = null;
		for (String id : ids) {
			rolePermission = new RolePermission();
			rolePermission.setPermissionId(id);
			rolePermission.setRoleId(roleId);
			rolePermission.setRpCreatedTime(new Date());
			try {
				rolePermissionMapper.insert(rolePermission);
			} catch (Exception e) {
				e.printStackTrace();
				AutoResult.error("授权失败");
			}
		}
		
		return AutoResult.ok();
	}

}

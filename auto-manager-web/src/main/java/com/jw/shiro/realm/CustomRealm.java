package com.jw.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.jw.bean.Permission;
import com.jw.bean.User;
import com.jw.bean.ext.UserExt;
import com.jw.service.UserService;

public class CustomRealm extends AuthorizingRealm {
	private UserService userService;

	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 取出身份信息
		String username = (String) token.getPrincipal();

		User user = null;
		try {
			user = userService.getUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			return null;
		}
		
		// 获取密码
		String password = user.getUserPwd();

		UserExt userExt = new UserExt();

		userExt.setUserId(user.getUserId());
		userExt.setUserName(user.getUserName());
		userExt.setUserPwd(user.getUserPwd());
		userExt.setUserIcon(user.getUserIcon());

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userExt, password,
				ByteSource.Util.bytes(user.getUserSalt()), this.getName());

		return simpleAuthenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserExt userExt = (UserExt) principals.getPrimaryPrincipal();

		List<Permission> permissionList;
		try {
			permissionList = userService.getPermissionsByUserId(userExt.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// 权限代码字符串
		List<String> percodeList = new ArrayList<>();

		if (!permissionList.isEmpty() && permissionList.size() > 0) {
			for (Permission permission : permissionList) {
				if (permission.getPermissionPercode() != null && !permission.getPermissionPercode().equals("")) {
					percodeList.add(permission.getPermissionPercode());
				}
			}
		}

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		simpleAuthorizationInfo.addStringPermissions(percodeList);

		return simpleAuthorizationInfo;
	}

	// 清除缓存
	public void clearCached() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

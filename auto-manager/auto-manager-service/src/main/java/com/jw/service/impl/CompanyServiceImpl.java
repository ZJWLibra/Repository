package com.jw.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.Company;
import com.jw.bean.Role;
import com.jw.bean.User;
import com.jw.bean.UserRole;
import com.jw.common.JqgridResult;
import com.jw.mapper.CompanyMapper;
import com.jw.mapper.RoleMapper;
import com.jw.mapper.UserMapper;
import com.jw.mapper.UserRoleMapper;
import com.jw.service.CompanyService;
import com.jw.util.MD5Util;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Resource
	private CompanyMapper companyMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Value("${FIRST_PASSWORD}")
	private String FIRST_PASSWORD;

	@Override
	public void insert(Company t) throws Exception {
		companyMapper.insert(t);
		
		// 添加公司董事长帐号
		User user = new User();
		user.setUserPhone(t.getCompanyPricipalPhone());
		user.setUserSalt(t.getCompanyPricipalPhone());
		user.setUserPwd(MD5Util.getMD5(FIRST_PASSWORD, user.getUserSalt()));
		user.setUserName(t.getCompanyPricipal());
		user.setUserIcon("/img/default.png");
		user.setCompanyId(t.getCompanyId());
		user.setUserCreatedTime(new Date());
		userMapper.insert(user);
		
		// 为董事长帐号添加角色
		Role role = new Role();
		role.setRoleName("companyAdmin");
		role = roleMapper.get(role);
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getUserId());
		userRole.setRoleId(role.getRoleId());
		userRole.setUrCreatedTime(new Date());
		userRoleMapper.insert(userRole);
	}

	@Override
	public void delete(String[] ids) throws Exception {
		
	}

	@Override
	public void update(Company t) throws Exception {
		companyMapper.update(t);
	}

	@Override
	public JqgridResult<Company> list(Company t) throws Exception {
		Long count = companyMapper.count(t);
		List<Company> list = companyMapper.list(t);
		JqgridResult<Company> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public Company get(Company t) throws Exception {
		return companyMapper.get(t);
	}

}

package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.Company;
import com.jw.common.JqgridResult;
import com.jw.mapper.CompanyMapper;
import com.jw.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public void insert(Company t) throws Exception {
		companyMapper.insert(t);
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

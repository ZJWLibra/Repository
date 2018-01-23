package com.jw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jw.bean.Company;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.mapper.CompanyMapper;
import com.jw.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public AutoResult insert(Company t) {
		try {
			companyMapper.insert(t);
		} catch (Exception e) {
			return AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}

	@Override
	public AutoResult delete(String[] ids) {
		return null;
	}

	@Override
	public AutoResult update(Company t) {
		try {
			companyMapper.update(t);
		} catch (Exception e) {
			e.printStackTrace();
			return AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}

	@Override
	public JqgridResult<Company> list(Company t) {
		Long count;
		List<Company> list;
		try {
			count = companyMapper.count(t);
			list = companyMapper.list(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		JqgridResult<Company> result = new JqgridResult<>();
		result.setPage(t.getPage());
		result.setRecords(count);
		result.setRows(list);
		result.setTotal(result.getTotal(t.getRows()));
		return result;
	}

	@Override
	public Company get(Company t) {
		try {
			return companyMapper.get(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

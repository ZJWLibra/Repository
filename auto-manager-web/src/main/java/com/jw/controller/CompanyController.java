package com.jw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.jw.bean.Company;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
import com.jw.service.CompanyService;
import com.jw.util.DateUtil;
import com.jw.util.OSSUtil;

/**
 * 公司信息
 * 
 * @author Zeng
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Resource
	private CompanyService companyService;
	
	@Value("${OSS_BUCKET_NAME}")
	private String OSS_BUCKET_NAME;
	@Value("${OSS_COMPANY}")
	private String OSS_COMPANY;
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "company/companyIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<Company> list(Company company) {
		JqgridResult<Company> result = companyService.list(company);
		
		return result;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		
		return "company/companyAdd";
	}
	
	@RequestMapping("/upload")
	public @ResponseBody String upload(HttpServletRequest request, String fileName) {
		MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
		// 根据文件名称获取文件对象
		CommonsMultipartFile cm = (CommonsMultipartFile) mh.getFile(fileName);
		
		// 获取文件扩展名
		String originalFilename = cm.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		// 获取文件新名称
		String newFileName = OSSUtil.getNewFileName() + suffix;
		String key = OSS_COMPANY + newFileName;
		
		InputStream is = null;
		try {
			is = cm.getInputStream();
			OSSClient ossClient = OSSUtil.getOSSClient();
			// 上传图片
			OSSUtil.uploadObject(ossClient, OSS_BUCKET_NAME, key, is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 文件的相对路径
		String relativePath = OSS_COMPANY + newFileName;
		String jsonStr = "{\"relativePath\" : \"" + relativePath + "\"}";
		return jsonStr;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(Company company, String strCompanyOpenDate) {
		if (strCompanyOpenDate != null && !strCompanyOpenDate.equals("")) {
			Date companyOpenDate = DateUtil.stringToDate(strCompanyOpenDate, "yyyy-MM-dd");
			company.setCompanyOpenDate(companyOpenDate);
		}
		company.setCreateTime(new Date());
		AutoResult result = companyService.insert(company);
		return result;
	}
	
	@RequestMapping("/toEdit/{id}")
	public String toEdit(@PathVariable String id, Model model) {
		Company company = new Company();
		company.setCompanyId(id);
		company = companyService.get(company);
		model.addAttribute("company", company);
		return "company/companyEdit";
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(Company company, String strCompanyOpenDate) {
		if (strCompanyOpenDate != null && !strCompanyOpenDate.equals("")) {
			company.setCompanyOpenDate(DateUtil.stringToDate(strCompanyOpenDate, "yyyy-MM-dd"));
		}
		AutoResult result = companyService.update(company);
		
		return result;
	}
	
}

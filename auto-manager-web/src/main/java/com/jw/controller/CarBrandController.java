package com.jw.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.jw.model.CarBrand;
import com.jw.model.common.AutoResult;
import com.jw.model.common.JqgridResult;
import com.jw.service.CarBrandService;
import com.jw.util.OSSUtil;

/**
 * 汽车品牌
 * @author Zeng
 *
 */
@Controller
@RequestMapping("/carBrand")
public class CarBrandController {
	
	@Resource
	private CarBrandService carBrandService;
	
	@Value("${OSS_BUCKET_NAME}")
	private String OSS_BUCKET_NAME;
	@Value("${OSS_CAR_BRAND}")
	private String OSS_CAR_BRAND;
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "carBrand/carBrandIndex";
	}
	
	@RequestMapping("/list")
	public @ResponseBody JqgridResult<CarBrand> list(CarBrand carBrand) {
		JqgridResult<CarBrand> result = carBrandService.list(carBrand);
		
		return result;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "carBrand/carBrandAdd";
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
		String key = OSS_CAR_BRAND + newFileName;
		
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
		String relativePath = OSS_CAR_BRAND + newFileName;
		String jsonStr = "{\"relativePath\" : \"" + relativePath + "\"}";
		return jsonStr;
	}
	
	@RequestMapping("/insert")
	public @ResponseBody AutoResult insert(CarBrand carBrand) {
		AutoResult result = carBrandService.insert(carBrand);
		
		return result;
	}
	
}

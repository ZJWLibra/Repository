package com.jw.controller;

import java.io.IOException;
import java.io.InputStream;

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
import com.jw.bean.CarBrand;
import com.jw.common.AutoResult;
import com.jw.common.JqgridResult;
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
		JqgridResult<CarBrand> result;
		try {
			result = carBrandService.list(carBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
		try {
			carBrandService.insert(carBrand);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("新增失败");
		}
		return AutoResult.success();
	}
	
	@RequestMapping("/toEdit/{brandId}")
	public String toEdit(@PathVariable String brandId, Model model) {
		CarBrand carBrand = new CarBrand();
		carBrand.setBrandId(brandId);
		try {
			carBrand = carBrandService.get(carBrand);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		model.addAttribute("carBrand", carBrand);
		return "carBrand/carBrandEdit";
	}
	
	@RequestMapping("/update")
	public @ResponseBody AutoResult update(CarBrand carBrand) {
		try {
			carBrandService.update(carBrand);
		} catch (Exception e) {
			e.printStackTrace();
			AutoResult.error("修改失败");
		}
		return AutoResult.success();
	}
	
}

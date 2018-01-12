package com.jw.util;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

/**
 * OSS存储
 * 
 * @author Zeng
 *
 */
public class OSSUtil {
	// endpoint以杭州为例，其它region请按实际情况填写
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
	private static String accessKeyId = "LTAI3L28OSbgVnYe";
	private static String accessKeySecret = "KZRIRdvt5UDzdI2UG2SBPXMLPYbxwR";
	
	/**
	 * 获取OSSClient
	 * @return
	 */
	public static OSSClient getOSSClient() {
		return new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}
	
	/**
	 * 上传文件
	 * @param client OSS连接
	 * @param bucketName 保存路径
	 * @param key 保存文件名称
	 * @param file
	 * @return 返回结果
	 */
	public static PutObjectResult uploadObject(OSSClient client, String bucketName, String key, File file) {
		PutObjectResult object = client.putObject(bucketName, key, file);
		
		return object;
	}
	
	/**
	 * 
	 * @param client OSS连接
	 * @param bucketName 保存路径
	 * @param key 保存文件名称
	 * @param input
	 * @return 返回结果
	 */
	public static PutObjectResult uploadObject(OSSClient client, String bucketName, String key, InputStream input) {
		PutObjectResult object = client.putObject(bucketName, key, input);
		
		return object;
	}
	
	/**
	 * 获取文件名
	 * @return
	 */
	public static String getNewFileName() {
		// 生成文件名
		String newFileName = DateUtil.dateToString(new Date(), "yyyyMMddHHmmssSSS");
		// 在文件名结尾处追加3个随机数
		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			newFileName += r.nextInt(10);
		}
		
		return newFileName;
	}
}

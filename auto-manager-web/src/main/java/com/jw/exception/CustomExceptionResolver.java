package com.jw.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author Zeng
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		ModelAndView mav = new ModelAndView();
		
		// 没有权限
		if (e instanceof UnauthorizedException) {
			mav.setViewName("error/403");
		} else {
			mav.setViewName("error/500");
			e.printStackTrace();
		}
		
		return mav;
	}
	
}

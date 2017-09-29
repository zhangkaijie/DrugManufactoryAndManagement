package com.drugManufactoryAndManagement.spring.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object object) throws Exception {
		
		String path=req.getRequestURI();
		if(path.endsWith("login.htm")){
			return true;
		}
		return false;
	}
	/**
	 * 在Controller处理方法执行之后，在被渲染之前 执行
	 * 关闭，释放，处理一些Controller执行中需要的资源
	 * */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 在渲染器渲染后被执行
	 * 处理一个请求后的一些清理
	 * */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	
	}

}

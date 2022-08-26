package com.asm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.asm.service.LoaiSanPhamService;


@Component
public class GlobalInterceptor  implements HandlerInterceptor{

	@Autowired
	LoaiSanPhamService loaiSPService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		request.setAttribute("cates", loaiSPService.findAll());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	

}

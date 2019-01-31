package com.douzon.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.UserVo;

public class LogoutAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		
		if(session != null && session.getAttribute("authuser") != null) {
			//logout 처리
			session.removeAttribute("authuser");
			session.invalidate();
		}
		//쿠키 없애기
		/*
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				
				response.addCookie(cookies[i]);
			}
		}
		*/
		
		WebUtils.redirect(request, response, request.getContextPath());
		
	}

}

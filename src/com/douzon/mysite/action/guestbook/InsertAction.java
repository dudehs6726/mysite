package com.douzon.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.GuestBookDao;
import com.douzon.mysite.repository.UserDao;
import com.douzon.mysite.vo.GuestBookVo;
import com.douzon.mysite.vo.UserVo;

public class InsertAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		new GuestBookDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook?a=list");

	}

}

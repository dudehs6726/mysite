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

public class DeleteAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(Long.valueOf(no));
		vo.setPassword(password);
		
		new GuestBookDao().delete(vo);
		
		WebUtils.redirect(request, response, request.getContextPath() + "/guestbook?a=list");

	}

}

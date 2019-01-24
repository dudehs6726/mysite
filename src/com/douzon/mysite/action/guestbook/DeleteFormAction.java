package com.douzon.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.vo.GuestBookVo;

public class DeleteFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String no = request.getParameter("no");
		
		GuestBookVo vo = new GuestBookVo();
		vo.setNo(Long.valueOf(no));
		
		WebUtils.forward(request, response, "WEB-INF/views/guestbook/deleteform.jsp");
	}

}

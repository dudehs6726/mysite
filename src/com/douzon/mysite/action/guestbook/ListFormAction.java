package com.douzon.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.GuestBookDao;
import com.douzon.mysite.vo.GuestBookVo;

public class ListFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		GuestBookDao dao = new GuestBookDao();
	   	List<GuestBookVo> list = dao.getList();
	   	
	   	//데이터를 request 범위에 저장
	   	request.setAttribute("list", list);
	   	
	   	//forwarding
		WebUtils.forward(request, response, "WEB-INF/views/guestbook/list.jsp");
	}

}

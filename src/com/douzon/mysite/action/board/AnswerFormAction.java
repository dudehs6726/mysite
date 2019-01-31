package com.douzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;
import com.douzon.mysite.vo.UserVo;

public class AnswerFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* 접근제어(보안) */
		UserVo authUser = null;
		HttpSession session = request.getSession();
		
		if(session != null) {
			authUser = (UserVo)session.getAttribute("authuser");
		}
		if(authUser == null) {
			WebUtils.redirect(request, response, request.getContextPath());
			return;
		}
		
		String no = request.getParameter("no");
		
		BoardVo vo = new BoardDao().get(Long.valueOf(no));
		request.setAttribute("vo", vo);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/answerform.jsp");
		
	}

}

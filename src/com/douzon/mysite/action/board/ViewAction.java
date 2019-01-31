package com.douzon.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String no = request.getParameter("no");
		
		//쿠키
		boolean isGet = false;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals(no)) {
					isGet = true;
				}
			}
			
			if(!isGet) {
				//조회 증가 쿼리 메소드
				new BoardDao().updateHit(Long.valueOf(no));
				Cookie ck = new Cookie(no, no);
				ck.setMaxAge(5*60);
				response.addCookie(ck);
			}
		}		
		
		BoardVo vo = new BoardDao().get(Long.valueOf(no));
		
		request.setAttribute("vo", vo);
		
		WebUtils.forward(request, response, "WEB-INF/views/board/view.jsp");
	}

}

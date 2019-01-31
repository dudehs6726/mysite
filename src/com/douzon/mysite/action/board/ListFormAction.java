package com.douzon.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzon.mvc.action.Action;
import com.douzon.mvc.util.WebUtils;
import com.douzon.mysite.repository.BoardDao;
import com.douzon.mysite.vo.BoardVo;

public class ListFormAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String page = request.getParameter("page");
		String kwd = request.getParameter("kwd");
		
		BoardVo vo = new BoardVo();
		
		if(page != null)
		{
			vo.setPage(Integer.parseInt(page));
		}else {
			vo.setPage(1);
		}
		
		
		BoardDao dao = new BoardDao();
	   	List<BoardVo> list = dao.getList(vo, kwd);
	   	
	   	//데이터를 request 범위에 저장
	   	request.setAttribute("list", list);
	   	
	    //page
	   	dao.getPageList(vo, kwd); //총 글수

	   	dao.makeBlock(vo, vo.getPage()); //선택한 페이지의 해당 리스트 1~5
	   	dao.makeLastPageNum(vo); //마지막 페이지 계산
	   	request.setAttribute("vo", vo);
	   	request.setAttribute("kwd", kwd);

	   	//forwarding
		WebUtils.forward(request, response, "WEB-INF/views/board/list.jsp");

	}

}
